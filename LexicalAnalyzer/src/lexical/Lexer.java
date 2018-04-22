package lexical;

import java.util.ArrayList;

import lexical.util.Consts;
import lexical.util.LexemeType;
import lexical.controller.FileController;
import lexical.controller.automaton.*;
import lexical.model.LexicalError;
import lexical.model.Token;

/**
 * Classe responsável pela etapa do analisados lexico.
 * 
 * @author Cássio Santos
 * @author Beatriz de Brito
 */
public class Lexer {

    /** Criacao das variaveis. */
    private ArrayList<Token> tokens = new ArrayList<Token>();
    private ArrayList<LexicalError> errors = new ArrayList<LexicalError>();
    private String string;

    public Lexer(String string) {
        this.string = string;
    }

    /**
     * Metodo responsavel pela analise lexica.
     */
    public void analyze() {
        tokens.clear();
        errors.clear();
        char[] charArray = this.string.toCharArray();
        Automaton automaton = new Automaton();

        ArrayList<Character> lexeme = new ArrayList<Character>();
        int row = 1;
        for (int column = 0; column < charArray.length;) {
            char charactere = charArray[column];
            if (charactere == '\n') {
                row++;
            }

            lexeme.add(new Character(charactere));
            automaton.next(charactere);

            if (automaton.inFinalState()) {
                Character charRemoved;
                // Manipulando ponteiro da Lista
                switch ((FinalStates) automaton.getActualState()) {
                case INDENTIFIER:
                case WHITE_SPACE:
                case ARI_1_SYMBOL:
                case REL_1_SYMBOL:
                case LOG_1_SYMBOL:
                case NUMBER:
                case ERROR_LOGICAL_OP:
                    charRemoved = lexeme.remove(lexeme.size() - 1);
                    if (charRemoved == '\n') {
                        row--;
                    }
                    column--;
                    break;
                case NUMBER_THAT_NEED_TO_REMOVE_DOT:
                    charRemoved = lexeme.remove(lexeme.size() - 1);
                    if (charRemoved == '\n') {
                        row--;
                    }
                    charRemoved = lexeme.remove(lexeme.size() - 1);
                    if (charRemoved == '\n') {
                        row--;
                    }
                    column -= 2;
                    break;
                case ERROR_STRING_NOT_CLOSED:
                    lexeme.remove(lexeme.size() - 1);
                    break;
                case STRING:
                case ERROR_INVALID_CHARACTER:
                case LINE_COMMENT:
                case BLOCK_COMMENT:
                case ARI_1_SYMBOL_WITHOUT_TRACEBACK:
                case ARI_2_SYMBOLS:
                case REL_2_SYMBOLS:
                case LOG_2_SYMBOLS:
                case DELIMITER:
                case ERROR_INVALID_CHARACTER_ON_STRING:
                case ERROR_BLOCK_COMMENT_NOT_CLOSED:
                case END_OF_FILE:
                    break;
                }

                String lexemeString = lexeme.stream().map(e -> e.toString()).reduce((acc, e) -> acc + e).get();

                // Tomando decisão quanto a Estado Final
                switch ((FinalStates) automaton.getActualState()) {
                case INDENTIFIER:
                case ARI_1_SYMBOL:
                case REL_1_SYMBOL:
                case LOG_1_SYMBOL:
                case NUMBER:
                case NUMBER_THAT_NEED_TO_REMOVE_DOT:
                case STRING:
                case ARI_1_SYMBOL_WITHOUT_TRACEBACK:
                case ARI_2_SYMBOLS:
                case REL_2_SYMBOLS:
                case LOG_2_SYMBOLS:
                case DELIMITER:
                    Token token = new Token(getTokenName(automaton.getActualState(), lexemeString), lexemeString, row,
                            column);
                    tokens.add(token);
                    break;
                case LINE_COMMENT:
                case BLOCK_COMMENT:
                case END_OF_FILE:
                case WHITE_SPACE:
                    break;
                case ERROR_INVALID_CHARACTER:
                case ERROR_INVALID_CHARACTER_ON_STRING:
                case ERROR_LOGICAL_OP:
                case ERROR_BLOCK_COMMENT_NOT_CLOSED:
                case ERROR_STRING_NOT_CLOSED:
                    LexicalError error = new LexicalError(row, lexemeString,
                            getErrorMessage(automaton.getActualState()));
                    errors.add(error);
                    break;
                }

                lexeme.clear();
                automaton.reset();
            }
            column += 1;
        }
    }

    /**
     * Junta os resultados obtidos do analisador léxico: tokens e erros.
     *  
     * @return String com o resultado completo.
     */
    public String createOutputData(ArrayList<Token> tokens, ArrayList<LexicalError> errors) {
        String results = "";
        if (!tokens.isEmpty()) {
            for (Token token : tokens) {
                results = results + token.toString() + System.lineSeparator();
            }
        }
        results = results + System.lineSeparator();
        if (errors.isEmpty()) {
            results = results + "Sucesso!";
        } else {
            for (LexicalError error : errors) {
                results = results + error.toString() + System.lineSeparator();
            }
        }
        return results;
    }
    
    public String createOutputData() {
        return createOutputData(this.tokens, this.errors);
    }

    /**
     * Verifica qual o token indicado para aquele lexema.
     * @param state estado a ser analisado
     * @param lexeme lexema a ser analisado
     * @return String com o nome do token correspondente
     */
    private String getTokenName(State state, String lexeme) {
        switch ((FinalStates) state) {
        case INDENTIFIER:
            if (LexemeType.iskeyword(lexeme)) {
                return Consts.KEY_WORD;
            }
            return Consts.IDENTIFIER;
        case ARI_1_SYMBOL:
        case ARI_1_SYMBOL_WITHOUT_TRACEBACK:
        case ARI_2_SYMBOLS:
            return Consts.ARITHMETIC_OPERATOR;
        case REL_1_SYMBOL:
        case REL_2_SYMBOLS:
            return Consts.RELATIONAL_OPERATOR;
        case LOG_1_SYMBOL:
        case LOG_2_SYMBOLS:
            return Consts.LOGICAL_OPERATOR;
        case NUMBER:
        case NUMBER_THAT_NEED_TO_REMOVE_DOT:
            return Consts.NUMBER;
        case STRING:
            return Consts.STRING;
        case DELIMITER:
            return Consts.DELIMITER;
        default:
            return "OUTRO";
        }
    }

    /**
     * Verifica qual o erro correspondende ao estado atual.
     * @param state estado a ser analisado
     * @return String com a mensagem de erro correspondente
     */
    private String getErrorMessage(State state) {
        switch ((FinalStates) state) {
        case ERROR_STRING_NOT_CLOSED:
            return Consts.ERROR_STRING_NOT_CLOSED;
        case ERROR_BLOCK_COMMENT_NOT_CLOSED:
            return Consts.ERROR_BLOCK_COMMENT_NOT_CLOSED;
        case ERROR_INVALID_CHARACTER:
            return Consts.ERROR_INVALID_CHARACTER;
        case ERROR_INVALID_CHARACTER_ON_STRING:
            return Consts.ERROR_INVALID_CHARACTER_ON_STRING;
        case ERROR_LOGICAL_OP:
            return Consts.ERROR_LOGICAL_OP;
        default:
            return "OUTRO ERRO";
        }
    }
}