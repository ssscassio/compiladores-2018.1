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
    ARI_SLASH {
        @Override
        public State next(char character) {
            return ARI_SLASH;
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
}