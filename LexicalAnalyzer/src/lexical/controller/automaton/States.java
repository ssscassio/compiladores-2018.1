package lexical.controller.automaton;

import lexical.util.LexemeType;

public enum States implements State {
    STATE_INITIAL {
        @Override
        public State next(char character) {
            if (LexemeType.isSpace(character)) {
                return STATE_1;
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