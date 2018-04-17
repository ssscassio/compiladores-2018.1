package lexical.view;

import lexical.util.Consts;
import lexical.util.LexemeType;

import java.util.ArrayList;

import lexical.controller.FileController;
import lexical.controller.automaton.*;
import lexical.model.Token;

public class LexicalAnalyzer {
    private static ArrayList<Token> tokens = new ArrayList<Token>();

    public static void main(String[] args) throws Exception {

        FileController.readFilesToString().forEach((inputFile, string) -> {
            char[] charArray = string.toCharArray();
            Automaton automaton = new Automaton();

            ArrayList<Character> lexeme = new ArrayList<Character>();
            int row = 0;
            for (int column = 0; column < charArray.length;) {
                char charactere = charArray[column];
                if (charactere == '\n') {
                    row++;
                }

                lexeme.add(new Character(charactere));
                automaton.next(charactere);

                if (automaton.inFinalState()) {
                    // Manipulando ponteiro da Lista
                    switch ((FinalStates) automaton.getActualState()) {
                    case INDENTIFIER:
                    case WHITE_SPACE:
                    case ARI_1_SYMBOL:
                    case REL_1_SYMBOL:
                    case LOG_1_SYMBOL:
                    case NUMBER:
                        lexeme.remove(lexeme.size() - 1);
                        column--;
                        break;
                    case NUMBER_THAT_NEED_TO_REMOVE_DOT:
                        lexeme.remove(lexeme.size() - 1);
                        lexeme.remove(lexeme.size() - 1);
                        column -= 2;
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
                    case ERROR_LOGICAL_OP:
                    case ERROR_BLOCK_COMMENT_NOT_CLOSED:
                    case ERROR_STRING_NOT_CLOSED:
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
                        Token token = new Token(getTokenName(automaton.getActualState(), lexemeString), lexemeString,
                                row, column);
                        tokens.add(token);
                        break;
                    case LINE_COMMENT:
                    case BLOCK_COMMENT:
                    case END_OF_FILE:
                        break;
                    case WHITE_SPACE:
                    case ERROR_INVALID_CHARACTER:
                    case ERROR_INVALID_CHARACTER_ON_STRING:
                    case ERROR_LOGICAL_OP:
                    case ERROR_BLOCK_COMMENT_NOT_CLOSED:
                    case ERROR_STRING_NOT_CLOSED:
                        break;
                    }

                    lexeme.clear();
                    automaton.reset();
                }
                column += 1;

            }
            System.out.println("ARQUIVO: " + inputFile.replaceFirst("entrada", "saida"));
            tokens.forEach((token) -> {
                System.out.println(token.toString());
            });
            tokens.clear();
        });
    }

    private static String getTokenName(State state, String lexeme) {
        switch ((FinalStates) state) {
        case INDENTIFIER:
            if (LexemeType.iskeyword(lexeme)) {
                return Consts.KEY_WORD;
            }
            return Consts.IDENTIFIER;

        case ARI_1_SYMBOL:
            return Consts.ARITHMETIC_OPERATOR;

        case REL_1_SYMBOL:
            return Consts.RELATIONAL_OPERATOR;

        case LOG_1_SYMBOL:
            return Consts.LOGICAL_OPERATOR;

        case NUMBER:
            return Consts.NUMBER;
        
        case NUMBER_THAT_NEED_TO_REMOVE_DOT:
            return Consts.NUMBER;
        
        case STRING:
            return Consts.STRING;

        case ARI_1_SYMBOL_WITHOUT_TRACEBACK:
            return Consts.ARITHMETIC_OPERATOR;
        
        case ARI_2_SYMBOLS:
            return Consts.ARITHMETIC_OPERATOR;

        case REL_2_SYMBOLS:
            return Consts.RELATIONAL_OPERATOR;

        case LOG_2_SYMBOLS:
            return Consts.LOGICAL_OPERATOR;
            
        case DELIMITER:
            return Consts.DELIMITER;
            
        default:
            return "OUTRO";
        }
    }
}