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
                    }

                    if (automaton.inFinalState()) {
                        // Estado final identificado
                        System.out.println(string.substring(firstIndex, column) + " " + automaton.getActualState());
                        switch ((FinalStates) automaton.getActualState()) {
                        case INDENTIFIER: // Encontrou um identificador
                            // TODO: Trabalhar com o identificador encontrado
                            firstIndex = column;
                            column = column - 1;
                            automaton.reset();
                            break;
                        case WHITE_SPACE: // Encontrou caracteres de espaço
                            firstIndex = column;
                            column = column - 1;
                            automaton.reset();
                            break;
                        case INVALID_CHARACTER:
                            // TODO: Disparar erro
                            firstIndex = column;
                            automaton.reset();
                            break;
                        }
                    }
                    column += 1;
                }
            });

        });
    }
}