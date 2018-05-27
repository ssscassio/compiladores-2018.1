package lexical.model;

/**
 * Compreende as informações de um token encontrado.
 * 
 * @author Cássio Santos
 * @author Beatriz de Brito
 */
public class Token {

    /**
     * Linha onde está localizado o Token.
     */
    private final int row;

    /**
     * Coluna onde está localizado o Token.
     */
    private final int column;

    /**
     * Representa o tipo do Token.
     */
    private final String type;

    /**
     * Representa o lexema do Token.
     */
    private final String lexeme;

    public Token(String type, String lexeme, int row, int column) {
        this.row = row;
        this.column = column;
        this.type = type;
        this.lexeme = lexeme;
    }

    /**
     * Retorna o tipo do token.
     * 
     * @return Tipo do token
     */
    public String getType() {
        return this.type;
    }

    /**
     * Retorna em qual linha está localizado o token.
     * 
     * @return linha do token
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Retorna em que coluna começa o token.
     * 
     * @return coluna do token
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * Retorna o lexema relacionado ao token.
     * 
     * @return lexema do token
     */
    public String getLexeme() {
        return this.lexeme;
    }

    @Override
    public String toString() {
        return String.format("%02d %s %s", getRow(), getType(), getLexeme());
    }
}