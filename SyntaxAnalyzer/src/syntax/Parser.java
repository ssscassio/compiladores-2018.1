package syntax;

import java.util.ArrayList;
import java.util.Arrays;

import lexical.model.Token;
import lexical.util.Consts;
import syntax.model.SyntaxError;
import syntax.controller.grammar.*;

/**
 * Classe responsável pela etapa de análise sintática.
 * 
 * @author Cássio Santos
 * @author Beatriz de Brito
 */
public class Parser {

    /**
     * Lista de tokens encontrados na etapa de análise léxica.
     */
    private ArrayList<Token> tokens;

    /**
     * Lista de erros encontrados na etapa de análise sintática.
     */
    private ArrayList<SyntaxError> errors = new ArrayList<SyntaxError>();

    /**
     * Construtor Parser
     * 
     * @param tokens a Lista de tokens gerada pelo análisador léxico para ser
     *               processada pelo análisador sintático
     */
    public Parser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    /**
     * Método responsável por analisar sintáticamente a lista de tokens passada na
     * construção da instância da classe Parser
     */
    public void analyze() {
        System.out.println("Analisador Sintático...");
        Productions.Program.run(tokens);
    }
}