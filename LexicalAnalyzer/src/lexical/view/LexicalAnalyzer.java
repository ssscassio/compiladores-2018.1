package lexical.view;

import lexical.util.LexemeType;

import java.util.ArrayList;

import lexical.controller.FileController;
import lexical.controller.automaton.*;

public class LexicalAnalyzer {

    public static void main(String[] args) throws Exception {

        FileController.readFilesToString().forEach((inputFile, string) -> {
            char[] charArray = string.toCharArray();
            Automaton automaton = new Automaton();

            ArrayList<Character> lexeme = new ArrayList<Character>();
            for (int column = 0; column < charArray.length;) {

                char charactere = charArray[column];
                lexeme.add(new Character(charactere));
                automaton.next(charactere);
                System.err.println(
                        "(" + charactere + ") " + column + ":" + charArray.length + " " + automaton.getActualState());
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
                    case INVALID_CHARACTER:
                    case LINE_COMMENT:
                    case BLOCK_COMMENT:
                    case ARI_1_SYMBOL_WITHOUT_TRACEBACK:
                    case ARI_2_SYMBOLS:
                    case REL_2_SYMBOLS:
                    case LOG_2_SYMBOLS:
                    case DELIMITER:
                    case INVALID_CHARACTER_ON_STRING:
                    case ERROR:
                        break;
                    }

                    String lexemeString = lexeme.stream().map(e -> e.toString()).reduce((acc, e) -> acc + e).get();

                    // Tomando decisão quanto a Estado Final
                    // switch ((FinalStates) automaton.getActualState()) {
                    // case INDENTIFIER: // Encontrou um identificador
                    //     // TODO: Trabalhar com o identificador encontrado
                    //     break;
                    // case INVALID_CHARACTER:
                    //     // TODO: Disparar erro
                    //     break;
                    // case WHITE_SPACE:
                    // case BLOCK_COMMENT:
                    // case LINE_COMMENT:
                    //     break;
                    // }

                    lexeme.clear();
                    automaton.reset();
                }
                column += 1;

            }

        });
    }
}