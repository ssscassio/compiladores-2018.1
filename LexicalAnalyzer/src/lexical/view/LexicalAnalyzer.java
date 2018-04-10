package lexical.view;

import lexical.util.LexemeType;
import lexical.controller.FileController;
import lexical.controller.automaton.*;

public class LexicalAnalyzer {

    public static void main(String[] args) throws Exception {

        FileController.readFiles().forEach((inputFile, stringArray) -> {
            Automaton automaton = new Automaton();

            stringArray.forEach((string) -> {
                char[] charArray = string.toCharArray();
                for (int firstIndex = 0, column = 0; column <= charArray.length;) {
                    char charactere;
                    if (column != charArray.length) {
                        charactere = charArray[column];
                        automaton.next(charactere);
                        System.err.println("(" + charactere + ") " + column + ":" + charArray.length + " "
                                + automaton.getActualState());
                    } else {
                        automaton.next('\n');
                        System.err.println("( Fim de linha ) " + column + ":" + charArray.length + " "
                                + automaton.getActualState());
                        column++;
                    }

                    if (automaton.inFinalState()) {
                        // Estado final identificado
                        if (firstIndex == column && column != charArray.length) {
                            System.out.println("<<" + automaton.getActualState() + "," + charArray[column] + ">>");
                        } else if (column < charArray.length) {
                            System.out.println("<<" + automaton.getActualState() + ","
                                    + string.substring(firstIndex, column) + ">>");

                        }

                        // // Tomando decisão quanto a Estado Final
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

                        // Manipulando ponteiro da Lista
                        switch ((FinalStates) automaton.getActualState()) {
                        case INDENTIFIER:
                        case WHITE_SPACE:
                        case ARI_1_SYMBOL:
                        case REL_1_SYMBOL:
                        case LOG_1_SYMBOL:
                        case NUMBER:
                            column--;
                            break;
                        case NUMBER_THAT_NEED_TO_REMOVE_DOT:
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

                        automaton.reset();
                        firstIndex = column + 1;

                    }
                    column += 1;
                }
            });

        });
    }
}