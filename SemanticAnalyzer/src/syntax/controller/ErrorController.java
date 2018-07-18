package syntax.controller;

import java.util.ArrayList;
import syntax.model.SyntaxError;

/**
 * Controlador de erros encontrados em uma análise sintática.
 * 
 * @author Cássio Santos
 * @author Beatriz de Brito
 */
public class ErrorController {

    /**
     * Cria uma instancia da classe ErrorController e salva na classe para ser usado
     * de forma estática.
     */
    private static ErrorController INSTANCE = new ErrorController();

    /**
     * Inicializa o vetor da instancia que criada.
     */
    private ArrayList<SyntaxError> errors = new ArrayList<SyntaxError>();

    /**
     * Construtor privado que inicializa a lista de erros sintáticos
     */
    private ErrorController() {
        errors = new ArrayList<SyntaxError>();
    }

    public static ErrorController getInstance() {
        return INSTANCE;
    }

    /**
     * Adiciona um novo erro à lista de erros da análise sintática.
     * 
     * @param expectd String com lexemas esperados
     * @param found   String com lexemas encontrados
     * @param row     linha em que houve o erro
     */
    public void addError(String expected, String found, int row) {
        errors.add(new SyntaxError(expected, found, row));

    }

    /**
     * Limpa a lista de erros da análise sintática
     */
    public void clearErrors() {
        errors.clear();
    }

    /**
     * Retorna a lista de erros da análise sintática
     * 
     * @return lista de erros
     */
    public ArrayList<SyntaxError> getErrors() {
        return errors;
    }
}