package syntax.model;

/**
 * Compreende as informações de um erro encontrado.
 * 
 * @author Cássio Santos
 * @author Beatriz de Brito
 */
public class SyntaxError {

    /**
     * Linha onde está localizado o Erro Sintático.
     */
    private final int row;
    /**
     * Token esperado.
     */
    private final String expected;
    /**
     * Token encontrado.
     */
    private final String found;

    public SyntaxError(String expected, String found, int row) {
        this.expected = expected;
        this.found = found;
        this.row = row;
    }

    /**
     * Retorna em qual linha está localizado o erro.
     * 
     * @return linha do erro.
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Retorna o lexema esperado.
     * 
     * @return token esperado.
     */
    public String getExpected() {
        return this.expected;
    }

    /**
     * Retorna o lexema encontrado.
     * 
     * @return the found
     */
    public String getFound() {
        return this.found;
    }

    /**
     * Mensagem do erro encontrado na análise sintática.
     * 
     * @return String com a mensagem de erro com a linha, token esperado e token
     *         encontrado
     * 
     */
    @Override
    public String toString() {
        return String.format("Line %02d ERROR: expected %s found %s", getRow(), getExpected(), getFound());
    }
}