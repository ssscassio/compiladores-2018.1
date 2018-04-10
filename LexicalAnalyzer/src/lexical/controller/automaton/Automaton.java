package lexical.controller.automaton;

public class Automaton {

    private State actualState;

    public Automaton(State initialState) {
        this.actualState = initialState;
    }

    public Automaton() {
        this.actualState = States.STATE_INITIAL;
    }

    public void next(char character) {
        this.actualState = actualState.next(character);
    }

    /**
     * @return O estado atual do Automato
     */
    public State getActualState() {
        return this.actualState;
    }

    public boolean inFinalState() {
        return this.actualState instanceof FinalState;
    }

    /**
     * Volta ao estado inicial do Automato
     */
    public void reset() {
        this.actualState = States.STATE_INITIAL;
    }

}