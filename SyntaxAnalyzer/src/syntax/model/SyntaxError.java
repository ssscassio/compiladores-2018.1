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
     * Representa a mensagem de erro para ser apresentado.
     */
    private final String message;

    public SyntaxError(int row, String message) {
        this.row = row;
        this.message = message;
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
     * Retorna a mensagem de erro que deve ser apresentada para o usuário.
     * 
     * @return mensagem de erro.
     */
    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return String.format("%02d %s", getRow(), getMessage());
    }
}