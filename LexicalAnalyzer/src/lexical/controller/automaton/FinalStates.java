package lexical.controller.automaton;

public enum FinalStates implements FinalState {
    WHITE_SPACE {
        @Override
        public State next(char character) {
            return WHITE_SPACE;
        }
    },
    INVALID_CHARACTER {
        @Override
        public State next(char character) {
            return INVALID_CHARACTER;
        }
    },
    INDENTIFIER {
        @Override
        public State next(char character) {
            return INDENTIFIER;
        }
    },
    LINE_COMMENT {
        @Override
        public State next(char character) {
            return LINE_COMMENT;
        }
    },
    BLOCK_COMMENT {
        @Override
        public State next(char character) {
            return BLOCK_COMMENT;
        }
    },
    STRING {
        @Override
        public State next(char character) {
            return STRING;
        }
    },
    INVALID_CHARACTER_ON_STRING {
        @Override
        public State next(char character) {
            return INVALID_CHARACTER_ON_STRING;
        }
    },
    ARI_1_SYMBOL {
        @Override
        public State next(char character) {
            return ARI_1_SYMBOL;
        }
    },
    ARI_2_SYMBOLS {
        @Override
        public State next(char character) {
            return ARI_2_SYMBOLS;
        }
    },
    ARI_1_SYMBOL_WITHOUT_TRACEBACK {
        @Override
        public State next(char character) {
            return ARI_1_SYMBOL_WITHOUT_TRACEBACK;
        }
    },
    REL_1_SYMBOL {
        @Override
        public State next(char character) {
            return REL_1_SYMBOL;
        }
    },
    REL_2_SYMBOLS {
        @Override
        public State next(char character) {
            return REL_2_SYMBOLS;
        }
    },
    LOG_1_SYMBOL {
        @Override
        public State next(char character) {
            return LOG_1_SYMBOL;
        }
    },
    LOG_2_SYMBOLS {
        @Override
        public State next(char character) {
            return LOG_2_SYMBOLS;
        }
    },
    NUMBER {
        @Override
        public State next(char character) {
            return NUMBER;
        }
    },
    NUMBER_THAT_NEED_TO_REMOVE_DOT {
        @Override
        public State next(char character) {
            return NUMBER_THAT_NEED_TO_REMOVE_DOT;
        }
    },
    DELIMITER {
        @Override
        public State next(char character) {
            return DELIMITER;
        }
    },
    ERROR {
        @Override
        public State next(char character) {
            return ERROR;
        }
    }
}