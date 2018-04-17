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
            } else if (character == '\0') {
                return FinalStates.END_OF_FILE;
            }
            return FinalStates.ERROR_INVALID_CHARACTER;
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
        @Override
        public State next(char character) {
            if (character == '\n') {
                return FinalStates.LINE_COMMENT;
            }
            return STATE_4;
        }
    },
    STATE_6 {
        @Override
        public State next(char character) {
            if (character == '*') {
                return STATE_7;
            } else if (character == '\0') {
                return FinalStates.ERROR_BLOCK_COMMENT_NOT_CLOSED;
            }
            return STATE_6;
        }
    },
    STATE_7 {
        @Override
        public State next(char character) {
            if (character == '/') {
                return FinalStates.BLOCK_COMMENT;
            } else if (character == '*') {
                return STATE_7;
            } else if (character == '\0') {
                return FinalStates.ERROR_BLOCK_COMMENT_NOT_CLOSED;
            }
            return STATE_6;
        }
    },
    STATE_10 {
        @Override
        public State next(char character) {
            if (character == '\n') {
                return FinalStates.ERROR_STRING_NOT_CLOSED;
            } else if (character == '\\') {
                return STATE_12;
            } else if (character == '\"') {
                return FinalStates.STRING;
            } else if (LexemeType.isDigit(character) || LexemeType.isLetter(character)
                    || LexemeType.isSymbol(character)) {
                return STATE_10;
            }
            return FinalStates.ERROR_INVALID_CHARACTER_ON_STRING;
        }
    },
    STATE_12 {
        @Override
        public State next(char character) {
            if (character == '\n') {
                return FinalStates.ERROR_STRING_NOT_CLOSED;
            } else if (character == '\\') {
                return STATE_12;
            } else if (character == '\"' || LexemeType.isDigit(character) || LexemeType.isLetter(character)
                    || LexemeType.isSymbol(character)) {
                return STATE_10;
            }
            return FinalStates.ERROR_INVALID_CHARACTER_ON_STRING;
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
                return FinalStates.ARI_2_SYMBOLS;
            }
            return FinalStates.ARI_1_SYMBOL;
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
            return FinalStates.ARI_1_SYMBOL;
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
            return FinalStates.NUMBER;
        }
    },
    STATE_17 {
        @Override
        public State next(char character) {
            if (LexemeType.isDigit(character)) {
                return STATE_18;
            }
            return FinalStates.NUMBER_THAT_NEED_TO_REMOVE_DOT;
        }
    },
    STATE_18 {
        @Override
        public State next(char character) {
            if (LexemeType.isDigit(character)) {
                return STATE_18;
            }
            return FinalStates.NUMBER;
        }
    },
    STATE_23 {
        @Override
        public State next(char character) {
            if (character == '=') {
                return FinalStates.REL_2_SYMBOLS;
            }
            return FinalStates.LOG_1_SYMBOL;
        }
    },
    STATE_27 {
        @Override
        public State next(char character) {
            if (character == '=') {
                return FinalStates.REL_2_SYMBOLS;
            }
            return FinalStates.REL_1_SYMBOL;
        }
    },
    STATE_36 {
        @Override
        public State next(char character) {
            if (character == '&') {
                return FinalStates.LOG_2_SYMBOLS;
            }
            return FinalStates.ERROR;
        }
    },
    STATE_38 {
        @Override
        public State next(char character) {
            if (character == '|') {
                return FinalStates.LOG_2_SYMBOLS;
            }
            return FinalStates.ERROR;
        }
    },
    STATE_40 {
        @Override
        public State next(char character) {
            if (character == '+') {
                return FinalStates.ARI_2_SYMBOLS;
            }
            return FinalStates.ARI_1_SYMBOL;
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