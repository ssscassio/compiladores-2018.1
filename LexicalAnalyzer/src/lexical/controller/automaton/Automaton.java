package lexical.controller.automaton;

/**
 * Classe contendo os estados do automato a serem utilizados
 * permitindo verificar o estado atual e acessar o próximo caractere lido. 
 * 
 * @author Cássio Santos
 * @author Beatriz de Brito
 */
public class Automaton {

    private State actualState;

    /**
     * Seta o estado inicial do automato.
     */
    public Automaton(State initialState) {
        this.actualState = initialState;
    }

    /**
     * Chama o State_Initial para analisar o caracter lido.
     */
    public Automaton() {
        this.actualState = States.STATE_INITIAL;
    }

    /**
     * @return O estado atual do Automato.
     */
    public void next(char character) {
        this.actualState = actualState.next(character);
    }

    /**
     * @return O estado atual do Automato.
     */
    public State getActualState() {
        return this.actualState;
    }

    /**
     * @return Verifica se esta no estado final.
     */
    public boolean inFinalState() {
        return this.actualState instanceof FinalState;
    }

    /**
     * Volta ao estado inicial do Automato.
     */
    public void reset() {
        this.actualState = States.STATE_INITIAL;
    }

}