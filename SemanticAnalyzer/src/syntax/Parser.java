package syntax;

import java.util.ArrayList;
import java.util.Arrays;

import lexical.model.Token;
import lexical.util.Consts;
import syntax.model.SyntaxError;
import syntax.controller.ErrorController;
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
     * Construtor Parser
     * 
     * @param tokens a Lista de tokens gerada pelo análisador léxico para ser
     *               processada pelo análisador sintático
     */
    public Parser(ArrayList<Token> tokens) {
        this.tokens = tokens;
        this.tokens.add(new Token(Consts.END_OF_FILE, "END_OF_FILE", tokens.get(tokens.size() - 1).getRow() + 1, 0));
    }

    /**
     * Método responsável por analisar sintáticamente a lista de tokens passada na
     * construção da instância da classe Parser
     */
    public void analyze() {
        System.out.println("Analisador Sintatico...");
        try {
            Productions.Program.run(tokens);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Junta os resultados obtidos do analisador sintático: erros.
     * 
     * @return String com o resultado completo.
     */
    public String createOutputData(ArrayList<SyntaxError> errors) {
        String results = "";
        if (errors.isEmpty()) {
            results = results + "Sucesso!";
        } else {
            for (SyntaxError error : errors) {
                results = results + error.toString() + System.lineSeparator();
            }
        }
        return results;
    }

    public String createOutputData() {
        return createOutputData(ErrorController.getInstance().getErrors());
    }

}