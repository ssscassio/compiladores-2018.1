package lexical.util;

public class LexemeType {

	// Regex usadas para cada tipo 
	private static final String LETTER_REGEX = "[a-zA-Z]";
	private static final String DIGIT_REGEX = "[\\d]";
	private static final String ARITHMETIC_REGEX = "\\+|-|\\*|/|\\+\\+|--";
	private static final String RELATIONAL_REGEX = "!=|==|<|<=|>|>=|=";
	private static final String LOGIC_REGEX = "!|&&|\\|\\|";
	private static final String SPACE_REGEX = "\\u0020|\\u0009|\\n|\\u000D";
	private static final String DELIMITER_REGEX = "\\;|\\,|\\(|\\)|\\[|\\]|\\{|\\}|\\.";
	private static final String LETTER_DIGIT_UNDERSCORE_REGEX = "\\w*";
	private static final String SYMBOL_REGEX = "[\\u0020-\\u007E&&[^\\u0022]]";
	private static final String STRING_REGEX = "\\u0022["+ LETTER_REGEX + DIGIT_REGEX + SYMBOL_REGEX + "(\\u005C\\u0022)" +"]*\\u0022";
	private static final String IDENTIFIER_REGEX = LETTER_REGEX + LETTER_DIGIT_UNDERSCORE_REGEX;
	private static final String NUMBER_REGEX = "(-)?" + "[" + SPACE_REGEX + "]*" + DIGIT_REGEX + DIGIT_REGEX + "*" + "(\\." + DIGIT_REGEX + DIGIT_REGEX + "*)?";
	
	public LexemeType() {

    }

    /**
	 * Verifica se uma string é do tipo letra
	 * 
	 * @param string String para ser validada
	 * @return verdadeiro se a String s for uma letra, falso caso contrário
	 */
    public static boolean isLetter(String s) {
        return s.matches(LETTER_REGEX);
    }
    
    /**
	 * Verifica se uma string é do tipo digito
	 * 
	 * @param string String para ser validada
	 * @return verdadeiro se a String s for um digito, falso caso contrário
	 */
    public static boolean isDigit(String s) {
        return s.matches(DIGIT_REGEX);
    }
    
    /**
	 * Verifica se uma string é do tipo número
	 * 
	 * @param string String para ser validada
	 * @return verdadeiro se a String s for um número, falso caso contrário
	 */
    public static boolean isNumber(String s) {
        return s.matches(NUMBER_REGEX);
    }

    /**
	 * Verifica se uma string é do tipo identificador
	 * 
	 * @param string String para ser validada
	 * @return verdadeiro se a String s for um identificador, falso caso contrário
	 */
    public static boolean isIdentifier(String s) {
        return s.matches(IDENTIFIER_REGEX);
    }
    
    /**
	 * Verifica se uma string é do tipo Espaco
	 * 
	 * @param string String para ser validada
	 * @return verdadeiro se a String s for um Espaco, falso caso contrário
	 */
    public static boolean isSpace(String s) {
        return s.matches(SPACE_REGEX);
    }
    
         
    /**
	 * Verifica se uma string é do tipo Simbolo
	 * 
	 * @param string String para ser validada
	 * @return verdadeiro se a String s for um Simbolo, falso caso contrário
	 */
    public static boolean isSymbol(String s) {
        return s.matches(SYMBOL_REGEX);
    }

    /**
	 * Verifica se uma string é do tipo Cadeia de caracteres
	 * 
	 * @param string String para ser validada
	 * @return verdadeiro se a String s for uma Cadeia de caracteres, falso caso contrário
	 */
    public static boolean isString(String s) {
        return s.matches(STRING_REGEX);
    }

    /**
	 * Verifica se uma string é do tipo Delimitador
	 * 
	 * @param string String para ser validada
	 * @return verdadeiro se a String s for um Delimitador, falso caso contrário
	 */
    public static boolean isDelimiter(String s) {
        return s.matches(DELIMITER_REGEX);
    }
    
    /**
	 * Verifica se uma string é do tipo operador lógico
	 * 
	 * @param string String para ser validada
	 * @return verdadeiro se a String s for um operador lógico, falso caso contrário
	 */
    public static boolean isLogic(String s) {
        return s.matches(LOGIC_REGEX);
    }
    
    /**
	 * Verifica se uma string é do tipo operador relacional
	 * 
	 * @param string String para ser validada
	 * @return verdadeiro se a String s for um operador relacional, falso caso contrário
	 */
    public static boolean isRelational(String s) {
        return s.matches(RELATIONAL_REGEX);
    }

    /**
	 * Verifica se uma string é do tipo operador aritmético
	 * 
	 * @param string String para ser validada
	 * @return verdadeiro se a String s for um operador aritmético, falso caso contrário
	 */
    public static boolean isArithmetic(String s) {
        return s.matches(ARITHMETIC_REGEX);
    }
} 