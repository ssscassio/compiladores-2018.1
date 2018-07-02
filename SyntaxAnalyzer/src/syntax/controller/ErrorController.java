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
     * Nova instância da classe ErrorController.
     */
    private static ErrorController INSTANCE = new ErrorController();

    /**
     * Lista de erros encontrados na etapa de análise sintática.
     */
    private ArrayList<SyntaxError> errors = new ArrayList<SyntaxError>();

    /**
     * Nova lista de erros
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
     * @param expectd token esperado
     * @param found   token encontrado
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