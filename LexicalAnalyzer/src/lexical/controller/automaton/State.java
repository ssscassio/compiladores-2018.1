package lexical.controller.automaton;

public interface State {
    public State next(char character);
}