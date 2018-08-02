package syntax.controller;

import java.util.ArrayList;
import syntax.model.SyntaxError;
import semantic.model.SemanticError;

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
     * Erros sintaticos
     */
    private ArrayList<SyntaxError> syntaxErrors = new ArrayList<SyntaxError>();

    /**
     * Erros semânticos.
     */
    private ArrayList<SemanticError> semanticErrors = new ArrayList<SemanticError>();

    /**
     * Construtor privado que inicializa a lista de erros sintáticos
     */
    private ErrorController() {
        syntaxErrors = new ArrayList<SyntaxError>();
        semanticErrors = new ArrayList<SemanticError>();
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
        syntaxErrors.add(new SyntaxError(expected, found, row));

    }

    public void addSemanticError(String errorText, int row) {
        semanticErrors.add(new SemanticError(errorText, row));
    }

    public void addSemanticError(String errorText) {
        semanticErrors.add(new SemanticError(errorText));
    }

    /**
     * Limpa a lista de erros da análise sintática
     */
    public void clearErrors() {
        syntaxErrors.clear();
        semanticErrors.clear();
    }

    /**
     * Retorna a lista de erros da análise sintática
     * 
     * @return lista de erros
     */
    public ArrayList<SyntaxError> getErrors() {
        return syntaxErrors;
    }

    public ArrayList<SemanticError> getSemanticErrors() {
        return semanticErrors;
    }
}