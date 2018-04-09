package lexical.controller.automaton;

enum FinalStates implements FinalState {
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
    }
}