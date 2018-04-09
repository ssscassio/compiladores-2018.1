package lexical.controller.automaton;

public class Automaton {

    private final String input;
    private State actualState;
    private int currentColumn = 0;

    public Automaton(State initialState, String input) {
        this.actualState = initialState;
        this.input = input;
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

}