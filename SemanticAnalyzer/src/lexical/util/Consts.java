package lexical.util;

/**
 * Coleção de constantes de uso geral.
 * 
 * @author Cássio Santos
 * @author Beatriz de Brito
 */
public final class Consts {

    /** Erros léxicos */
    public static final String ERROR_STRING_NOT_CLOSED = "Cadeia de caractere mal formada.";
    public static final String ERROR_BLOCK_COMMENT_NOT_CLOSED = "Comentario em bloco mal formado.";
    public static final String ERROR_INVALID_CHARACTER = "Caractere invalido.";
    public static final String ERROR_INVALID_CHARACTER_ON_STRING = "Caractere invalido na cadeia de caracteres.";
    public static final String ERROR_LOGICAL_OP = "Operador logico incompleto.";
    
    /** Tokens */
    public static final String IDENTIFIER = "IDE";
    public static final String KEY_WORD = "PRE";
    public static final String NUMBER = "NRO";
    public static final String RELATIONAL_OPERATOR = "REL";
    public static final String LOGICAL_OPERATOR = "LOG";
    public static final String ARITHMETIC_OPERATOR = "ART";
    public static final String STRING = "CAD";
    public static final String DELIMITER = "DEL";
    public static final String END_OF_FILE = "EOF";

    /**
     * Construtor privado para previnir que a classe seja instanciada.
     */
    private Consts() {
        throw new AssertionError();
    }
}