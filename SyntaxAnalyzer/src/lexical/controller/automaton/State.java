package lexical.controller.automaton;

/**
 * Interface de definição de um estado.
 *
 * @see State
 */
public interface State {
    /**
     * Método abstrato para definição das transições possíves a partir de um estado.
     * Dado o estado atual, e um caractere, retornar no próximo estado.
     * 
     * @param character Caractere a ser analisado
     * @return Próximo estado
     */
    public State next(char character);
}