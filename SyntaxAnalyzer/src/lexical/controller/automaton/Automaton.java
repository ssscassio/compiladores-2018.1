package lexical.controller.automaton;

/**
 * Classe que define e manipula um autômato baseado nos estados definidos nos
 * enum States e FinalStates.
 * 
 * @see #next
 * @see States
 * @see FinalStates
 * @author Cássio Santos
 * @author Beatriz de Brito
 */
public class Automaton {

    /** Estado atual em que se encontra o autômato. */
    private State actualState;

    /**
     * Construtor Automaton. Para inicial com o autômato com o estado inicial padrão
     * deve ser usado o construtor sem argumento.
     * 
     * @param initialState Estado em que deseja-se iniciar o autômato. Deve ser uma
     *                     classe presente em States ou FinalStates.
     * 
     * @see #Automaton()
     * @see States
     * @see FinalStates
     */
    public Automaton(State initialState) {
        this.actualState = initialState;
    }

    /**
     * Construtor Automaton sem argumento inicializa o autômato com o estado
     * STATE_INITIAL definido no enum States.
     * 
     * @see States
     */
    public Automaton() {
        this.actualState = States.STATE_INITIAL;
    }

    /**
     * Executa a leitura do próximo caractere na cadeia de caracteres. A partir do
     * estado atual autômatoefetua a transição baseado no caractere passado como
     * parâmetro e define o novo estado atual do autômato.
     * 
     * @param character Caractere a ser tratado pelo autômato
     * @see #actualState
     */
    public void next(char character) {
        this.actualState = actualState.next(character);
    }

    /**
     * @return O estado atual do autômato.
     */
    public State getActualState() {
        return this.actualState;
    }

    /**
     * Método para verificar se o autômato se encontra em um estado final.
     * 
     * @see FinalStates
     * @return verdadeiro se o estado atual do autômato é um estado final, falso
     *         caso contrário
     */
    public boolean inFinalState() {
        return this.actualState instanceof FinalState;
    }

    /**
     * Método para re-iniciar o autômato voltando ao estado inicial (STATE_INITIAL)
     * definido no enum States.
     * 
     * @see States
     */
    public void reset() {
        this.actualState = States.STATE_INITIAL;
    }
}