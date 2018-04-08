package lexical.util;

import java.util.Arrays;

/**
 * Utilitario para verificar Strings e informar se faz parte 
 * de determinado 'tipo/token' da linguagem
 * 
 * @author Cássio Santos
 */
public class LexemeType {

    /**
     * Regex usadas para cada 'tipo' da linguagem
     */
    private static final String LETTER_REGEX = "[a-zA-Z]";
    private static final String DIGIT_REGEX = "[\\d]";
    private static final String ARITHMETIC_REGEX = "\\+|-|\\*|/|\\+\\+|--";
    private static final String RELATIONAL_REGEX = "!=|==|<|<=|>|>=|=";
    private static final String LOGIC_REGEX = "!|&&|\\|\\|";
    private static final String SPACE_REGEX = "\\u0020|\\u0009|\\n|\\u000D";
    private static final String DELIMITER_REGEX = "\\;|\\,|\\(|\\)|\\[|\\]|\\{|\\}|\\.";
    private static final String LETTER_DIGIT_UNDERSCORE_REGEX = "\\w*";
    private static final String SYMBOL_REGEX = "[\\u0020-\\u007E&&[^\\u0022]]";
    private static final String STRING_REGEX = "\\u0022[" + LETTER_REGEX + DIGIT_REGEX + SYMBOL_REGEX
            + "(\\u005C\\u0022)" + "]*\\u0022";
    private static final String IDENTIFIER_REGEX = LETTER_REGEX + LETTER_DIGIT_UNDERSCORE_REGEX;
    private static final String NUMBER_REGEX = "(-)?" + "[" + SPACE_REGEX + "]*" + DIGIT_REGEX + DIGIT_REGEX + "*"
            + "(\\." + DIGIT_REGEX + DIGIT_REGEX + "*)?";

    /**
     * Array que armazena todas as palavras reservadas da linguagem de forma ordenada
     */
    private static final String KEYWORDS[] = { "bool", "const", "else", "extends", "false", "float", "function", "if",
            "int", "print", "procedure", "return", "scan", "start", "string", "struct", "then", "true", "typedef",
            "var", "while" };

    /**
     * Construtor privado para previnir que a classe seja instanciada
     */
    private LexemeType() {
        throw new AssertionError();
    }

    /**
     * Verifica se uma string é do tipo letra
     * 
     * @param s String para ser validada
     * @return verdadeiro se a String s for uma letra, falso caso contrário
     */
    public static boolean isLetter(String s) {
        return s.matches(LETTER_REGEX);
    }

    /**
     * Verifica se uma string é do tipo digito
     * 
     * @param s String para ser validada
     * @return verdadeiro se a String s for um digito, falso caso contrário
     */
    public static boolean isDigit(String s) {
        return s.matches(DIGIT_REGEX);
    }

    /**
     * Verifica se uma string é do tipo número
     * 
     * @param s String para ser validada
     * @return verdadeiro se a String s for um número, falso caso contrário
     */
    public static boolean isNumber(String s) {
        return s.matches(NUMBER_REGEX);
    }

    /**
     * Verifica se uma string é do tipo identificador
     * 
     * @param s String para ser validada
     * @return verdadeiro se a String s for um identificador, falso caso contrário
     */
    public static boolean isIdentifier(String s) {
        return s.matches(IDENTIFIER_REGEX);
    }

    /**
     * Verifica se uma string é do tipo Espaco
     * 
     * @param s String para ser validada
     * @return verdadeiro se a String s for um Espaco, falso caso contrário
     */
    public static boolean isSpace(String s) {
        return s.matches(SPACE_REGEX);
    }

    /**
     * Verifica se uma string é do tipo Simbolo
     * 
     * @param s String para ser validada
     * @return verdadeiro se a String s for um Simbolo, falso caso contrário
     */
    public static boolean isSymbol(String s) {
        return s.matches(SYMBOL_REGEX);
    }

    /**
     * Verifica se uma string é do tipo Cadeia de caracteres
     * 
     * @param s String para ser validada
     * @return verdadeiro se a String s for uma Cadeia de caracteres, falso caso contrário
     */
    public static boolean isString(String s) {
        return s.matches(STRING_REGEX);
    }

    /**
     * Verifica se uma string é do tipo Delimitador
     * 
     * @param s String para ser validada
     * @return verdadeiro se a String s for um Delimitador, falso caso contrário
     */
    public static boolean isDelimiter(String s) {
        return s.matches(DELIMITER_REGEX);
    }

    /**
     * Verifica se uma string é do tipo operador lógico
     * 
     * @param s String para ser validada
     * @return verdadeiro se a String s for um operador lógico, falso caso contrário
     */
    public static boolean isLogic(String s) {
        return s.matches(LOGIC_REGEX);
    }

    /**
     * Verifica se uma string é do tipo operador relacional
     * 
     * @param s String para ser validada
     * @return verdadeiro se a String s for um operador relacional, falso caso contrário
     */
    public static boolean isRelational(String s) {
        return s.matches(RELATIONAL_REGEX);
    }

    /**
     * Verifica se uma string é do tipo operador aritmético
     * 
     * @param s String para ser validada
     * @return verdadeiro se a String s for um operador aritmético, falso caso contrário
     */
    public static boolean isArithmetic(String s) {
        return s.matches(ARITHMETIC_REGEX);
    }

    /**
     * Verifica se uma string é palavra reservada fazendo busca binária
     * na lista de palavras reservadas
     * 
     * @param s String para ser validada
     * @return verdadeiro se a String s for uma palavra reservada da linguagem, falso caso contrário
     */
    public static boolean iskeyword(String s) {
        return (Arrays.binarySearch(KEYWORDS, s) >= 0);
    }
}