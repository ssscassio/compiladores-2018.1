package lexical.util;

/**
 * Coleção de constantes de uso geral 
 * 
 * @author Cássio Santos
 */
public final class Consts {

    /** Erros léxicos */
    public static final String ERROR_STRING_NOT_CLOSED = "Chegou no final da linha e não encontrou o caractere de fechamento de cadeia de caracteres.";
    public static final String ERROR_BLOCK_COMMENT_NOT_CLOSED = "Chegou no final do arquivo e não encontrou os caracteres de fechamento de comentário em bloco.";
    public static final String ERROR_INVALID_CHARACTER = "Caractere invalido.";
    public static final String ERROR_INVALID_CHARACTER_ON_STRING = "Caractere invalido na cadeia de caracteres.";
    public static final String ERROR_LOGICAL_OP = "Operador logico incompleto.";

    /**
     * Construtor privado para previnir que a classe seja instanciada
     */
    private Consts() {
        throw new AssertionError();
    }
}