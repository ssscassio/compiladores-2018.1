package lexical.model;

/**
 * Compreende as informações de um erro encontrado
 * 
 * @author Cássio Santos
 */
public class LexicalError {

    /**
     * Linha onde está localizado o Erro Léxico
     */
    private final int row;

    /**
     * Representa o lexema que ocorreu o erro
     */
    private final String lexeme;

    /**
     *  Representa a mensagem de erro para ser apresentado
     */
    private final String message;

    public LexicalError(int row, String lexeme, String message) {
        this.row = row;
        this.lexeme = lexeme;
        this.message = message;
    }

    /**
     * Retorna a linha aonde está localizado o erro
     * 
     * @return linha do erro
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Retorna o lexema que ocorreu o erro
     * 
     * @return lexema com erro
     */
    public int getLexeme() {
        return this.lexeme;
    }

    /**
     * Retorna a mensagem de erro que deve ser apresentada para o usuário
     * 
     * @return mensagem de erro
     */
    public int getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return String.format("%s\t%s\t%s", getRow(), getLexeme(), getMessage());
    }
}