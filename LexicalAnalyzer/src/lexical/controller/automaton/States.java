package lexical.controller.automaton;

import lexical.util.LexemeType;

enum States implements State {
    STATE_INITIAL {
        @Override
        public State next(char character) {
            if (LexemeType.isSpace(Character.toString(character))) {
                return STATE_1;
            }
            return FinalStates.INVALID_CHARACTER;
        }
    },
    STATE_1 {
        @Override
        public State next(char character) {
            if (LexemeType.isSpace(character)) {
                return STATE_1;
            } else if (LexemeType.belongsToLanguage(character)) {
                return FinalStates.WHITE_SPACE;
            }
            return FinalStates.INVALID_CHARACTER;
        }
    };

    public abstract State next(char character);
}