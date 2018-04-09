package lexical.controller.automaton;

interface State {
    public State next(char character);
}