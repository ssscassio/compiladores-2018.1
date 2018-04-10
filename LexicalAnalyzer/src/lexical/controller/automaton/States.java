package lexical.controller.automaton;

import lexical.util.LexemeType;

public enum States implements State {
    STATE_INITIAL {
        @Override
        public State next(char character) {
            if (LexemeType.isSpace(character)) {
                return STATE_1;
            } else if (character == '/') {
                return STATE_3;
            } else if (character == '\"') {
                return STATE_10;
            } else if (character == '-') {
                return STATE_13;
            } else if (LexemeType.isDigit(character)) {
                return STATE_15;
            } else if (character == '!') {
                return STATE_23;
            } else if (character == '=' || character == '<' || character == '>') {
                return STATE_27;
            } else if (character == '&') {
                return STATE_36;
            } else if (character == '|') {
                return STATE_38;
            } else if (character == '+') {
                return STATE_40;
            } else if (character == '*') {
                return FinalStates.ARI_1_SYMBOL_WITHOUT_TRACEBACK;
            } else if (LexemeType.isDelimiter(character)) {
                return FinalStates.DELIMITER;
            } else if (LexemeType.isLetter(character)) {
                return STATE_43;
            }
            return FinalStates.INVALID_CHARACTER;
        }
    },
    STATE_1 {
        @Override
        public State next(char character) {
            if (LexemeType.isSpace(character)) {
                return STATE_1;
            }
            return FinalStates.WHITE_SPACE;
        }
    },
    STATE_3 {
        @Override
        public State next(char character) {
            if (character == '/') {
                return STATE_4;
            } else if (character == '*') {
                return STATE_6;
            }
            return FinalStates.ARI_1_SYMBOL;
        }
    },
    STATE_4 {
        // TODO: Não mudar ponteiro no estado Final
        @Override
        public State next(char character) {
            if (character == '\n') {
                return FinalStates.LINE_COMMENT;
            }
            return STATE_4;
        }
    },
    STATE_6 {
        // TODO: Verificar caso de chegar no fim do arquivo estando no estado 6 ou 7
        @Override
        public State next(char character) {
            if (character == '*') {
                return STATE_7;
            }
            return STATE_6;
        }
    },
    STATE_7 {
        // TODO: Verificar caso de chegar no fim do arquivo estando no estado 6 ou 7
        // TODO: Não mudar ponteiro no estado Final
        @Override
        public State next(char character) {
            if (character == '/') {
                return FinalStates.BLOCK_COMMENT;
            } else if (character == '*') {
                return STATE_7;
            }
            return STATE_6;
        }
    },
    STATE_10 {
        // TODO: Verificar caso de chegar no fim da linha estando no estado 10 ou 12
        // TODO: Não mudar ponteiro no estado Final
        @Override
        public State next(char character) {
            if (character == '\\') {
                return STATE_12;
            } else if (character == '\"') {
                return FinalStates.STRING;
            } else if (LexemeType.isDigit(character) || LexemeType.isLetter(character)
                    || LexemeType.isSymbol(character)) {
                return STATE_10;
            }
            return FinalState.INVALID_CHARACTER_ON_STRING;
        }
    },
    STATE_12 {
        // TODO: Verificar caso de chegar no fim da linha estando no estado 10 ou 12
        @Override
        public State next(char character) {
            if (character == '\\') {
                return STATE_12;
            } else if (character == '\"' || LexemeType.isDigit(character) || LexemeType.isLetter(character)
                    || LexemeType.isSymbol(character)) {
                return STATE_10;
            }
            return FinalState.INVALID_CHARACTER_ON_STRING;
        }
    },
    STATE_13 {
        @Override
        public State next(char character) {
            if (LexemeType.isSpace(character)) {
                return STATE_14;
            } else if (LexemeType.isDigit(character)) {
                return STATE_15;
            } else if (character == '-') {
                return FinalState.ARI_2_SYMBOLS;
            }
            return FinalState.ARI_1_SYMBOL;
        }
    },
    STATE_14 {
        // TODO: Tomar cuidado na main quando encontrar um caractere MINUS
        // Deve fazer o ponteiro resetar para o indice imediatamente próximo ao do '-'
        @Override
        public State next(char character) {
            if (LexemeType.isSpace(character)) {
                return STATE_14;
            } else if (LexemeType.isDigit(character)) {
                return STATE_15;
            }
            return FinalState.ARI_1_SYMBOL;
        }
    },
    STATE_15 {
        @Override
        public State next(char character) {
            if (LexemeType.isDigit(character)) {
                return STATE_15;
            } else if (character == '.') {
                return STATE_17;
            }
            return FinalState.NUMBER;
        }
    },
    STATE_17 {
        // TODO: No estado final NUMBER_WITH_DOT lembrar de retornar 2 caracteres
        // ao invés de apenas 1 como nos outros estados finais
        @Override
        public State next(char character) {
            if (LexemeType.isDigit(character)) {
                return STATE_18;
            }
            return FinalState.NUMBER_THAT_NEED_TO_REMOVE_DOT;
        }
    },
    STATE_18 {
        @Override
        public State next(char character) {
            if (LexemeType.isDigit(character)) {
                return STATE_18;
            }
            return FinalState.NUMBER;
        }
    },
    STATE_23 {
        @Override
        public State next(char character) {
            if (character == '=') {
                return FinalState.REL_2_SYMBOLS;
            }
            return FinalState.LOG_1_SYMBOL;
        }
    },
    STATE_27 {
        @Override
        public State next(char character) {
            if (character == '=') {
                return FinalState.REL_2_SYMBOLS;
            }
            return FinalState.REL_1_SYMBOL;
        }
    },
    STATE_36 {
        @Override
        public State next(char character) {
            if (character == '&') {
                return FinalState.LOG_2_SYMBOLS;
            }
            return FinalState.ERROR;
        }
    },
    STATE_38 {
        @Override
        public State next(char character) {
            if (character == '|') {
                return FinalState.LOG_2_SYMBOLS;
            }
            return FinalState.ERROR;
        }
    },
    STATE_40 {
        @Override
        public State next(char character) {
            if (character == '+') {
                return FinalState.ARI_2_SYMBOLS;
            }
            return FinalState.ARI_1_SYMBOLS;
        }
    },
    STATE_43 {
        @Override
        public State next(char character) {
            if (LexemeType.isLetterDigitOrUnderscore(character)) {
                return STATE_43;
            }
            return FinalStates.INDENTIFIER;
        }
    };

    public abstract State next(char character);
}