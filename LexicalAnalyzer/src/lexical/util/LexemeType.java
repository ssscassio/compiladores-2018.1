package lexical.util;

public class LexemeType {

    public LexemeType() {

    }

    /**
	 * Verifica se uma string é do tipo letra
	 * 
	 * @param string String para ser validada
	 * @return verdadeiro se a String s for uma letra, falso caso contrário
	 */
    public static boolean isLetter(String s) {
        return s.matches("[a-zA-Z]");
    }
    
    /**
	 * Verifica se uma string é do tipo digito
	 * 
	 * @param string String para ser validada
	 * @return verdadeiro se a String s for um digito, falso caso contrário
	 */
    public static boolean isDigit(String s) {
        return s.matches("\\d");
    }
    
    /**
	 * Verifica se uma string é do tipo número
	 * 
	 * @param string String para ser validada
	 * @return verdadeiro se a String s for um número, falso caso contrário
	 */
    public static boolean isNumber(String s) {
        return s.matches("(-)?[\\u0020\\u0009\\u000A\\u000D]*[0-9][0-9]*(\\.[0-9][0-9]*)?");
    }

    /**
	 * Verifica se uma string é do tipo identificador
	 * 
	 * @param string String para ser validada
	 * @return verdadeiro se a String s for um identificador, falso caso contrário
	 */
    public static boolean isIdentifier(String s) {
        return s.matches("[a-zA-Z]\\w*");
    }
    
    /**
	 * Verifica se uma string é do tipo Espaco
	 * 
	 * @param string String para ser validada
	 * @return verdadeiro se a String s for um Espaco, falso caso contrário
	 */
    public static boolean isSpace(String s) {
        return s.matches("[\\u0020\\u0009\\u000A\\u000D]");
    }
    
         
    /**
	 * Verifica se uma string é do tipo Simbolo
	 * 
	 * @param string String para ser validada
	 * @return verdadeiro se a String s for um Simbolo, falso caso contrário
	 */
    public static boolean isSymbol(String s) {
        return s.matches("[\\u0020-\\u007E&&[^\\u0022]]");
    }

    /**
	 * Verifica se uma string é do tipo Cadeia de caracteres
	 * 
	 * @param string String para ser validada
	 * @return verdadeiro se a String s for uma Cadeia de caracteres, falso caso contrário
	 */
    public static boolean isString(String s) {
        return s.matches("\\u0022[a-zA-Z0-9\\u0020-\\u007E&&[^\\u0022](\\u005C\\u0022)]*\\u0022");
    }

    /**
	 * Verifica se uma string é do tipo Delimitador
	 * 
	 * @param string String para ser validada
	 * @return verdadeiro se a String s for um Delimitador, falso caso contrário
	 */
    public static boolean isDelimiter(String s) {
        return s.matches("[\\;\\,\\(\\)\\[\\]\\{\\}\\.]");
    }

    /**
	 * Verifica se uma string é do tipo operador lógico
	 * 
	 * @param string String para ser validada
	 * @return verdadeiro se a String s for um operador lógico, falso caso contrário
	 */
    public static boolean isLogic(String s) {
        return s.matches("!|&&|\\|\\|");
    }
    
    /**
	 * Verifica se uma string é do tipo operador lógico
	 * 
	 * @param string String para ser validada
	 * @return verdadeiro se a String s for um operador lógico, falso caso contrário
	 */
    public static boolean isLogic(String s) {
        return s.matches("!|&&|\\|\\|");
    }
    
    /**
	 * Verifica se uma string é do tipo operador relacional
	 * 
	 * @param string String para ser validada
	 * @return verdadeiro se a String s for um operador relacional, falso caso contrário
	 */
    public static boolean isRelational(String s) {
        return s.matches("!=|==|<|<=|>|>=|=");
    }

    /**
	 * Verifica se uma string é do tipo operador aritmético
	 * 
	 * @param string String para ser validada
	 * @return verdadeiro se a String s for um operador aritmético, falso caso contrário
	 */
    public static boolean isArithmetic(String s) {
        return s.matches("\\+|-|\\*|/|\\+\\+|--");
    }
} 