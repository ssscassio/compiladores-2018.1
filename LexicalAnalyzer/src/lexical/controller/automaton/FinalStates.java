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
        public State next(char character) {
            return INDENTIFIER;
        }
    }
}