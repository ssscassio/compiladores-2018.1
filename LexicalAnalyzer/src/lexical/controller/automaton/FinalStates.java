package lexical.controller.automaton;

/**
 * Enum das classes dos possíveis estados finais do autômato na qual são implementadas
 * as transições de cada estado baseado no caractere sendo processado.
 * 
 * @see FinalState
 * @author Cássio Santos
 * @author Beatriz de Brito
 */
public enum FinalStates implements FinalState {
    WHITE_SPACE {
        @Override
        public State next(char character) {
            return WHITE_SPACE;
        }
    },
    ERROR_INVALID_CHARACTER {
        @Override
        public State next(char character) {
            return ERROR_INVALID_CHARACTER;
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
    ERROR_INVALID_CHARACTER_ON_STRING {
        @Override
        public State next(char character) {
            return ERROR_INVALID_CHARACTER_ON_STRING;
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
    ERROR_LOGICAL_OP {
        @Override
        public State next(char character) {
            return ERROR_LOGICAL_OP;
        }
    },
    ERROR_BLOCK_COMMENT_NOT_CLOSED {
        @Override
        public State next(char character) {
            return ERROR_BLOCK_COMMENT_NOT_CLOSED;
        }
    },
    ERROR_STRING_NOT_CLOSED {
        @Override
        public State next(char character) {
            return ERROR_STRING_NOT_CLOSED;
        }
    },
    END_OF_FILE {
        @Override
        public State next(char character) {
            return END_OF_FILE;
        }
    },

}