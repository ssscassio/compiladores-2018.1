package semantic.model;

/**
 * Compreende as informações de um erro semantico encontrado.
 * 
 * @author Cássio Santos
 * @author Beatriz de Brito
 */
public class SemanticError {

    /**
     * Linha onde está localizado o Erro Semântico.
     */
    private final int row;
    /**
     * Texto do erro semântico encontrado
     */
    private final String errorText;

    public SemanticError(String errorText, int row) {
        this.errorText = errorText;
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
     * Retorna o texto do erro encontrado.
     * 
     * @return the found
     */
    public String getErrorText() {
        return this.errorText;
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
        return String.format("Line %02d ERROR: %s", getRow(), getErrorText());
    }
}