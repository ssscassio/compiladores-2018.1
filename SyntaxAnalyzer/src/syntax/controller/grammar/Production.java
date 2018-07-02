package syntax.controller.grammar;

import java.util.ArrayList;

import lexical.model.Token;

/**
 * Interface de definição de uma produção.
 *
 * @see Productions
 * @author Cássio Santos
 * @author Beatriz de Brito
 */
public interface Production {

    /**
     * Método que verifica se determinado token faz parte do conjunto primeiro do
     * Simbolo Não Terminal.
     * 
     * @param token Token a ser analisado
     */
    public boolean hasAsFirst(Token token);

    /**
     * Método que verifica se determinado token faz parte do conjunto seguinte do
     * Simbolo Não Terminal.
     * 
     * @param token Token a ser analisado
     */
    public boolean hasAsFollow(Token token);

    /**
     * Método, cujo dado uma lista de tokens executa a devida produção e analisa
     * presença de erros sintáticos na lista.
     * 
     * @param tokens Lista de tokens a ser analisado para a produção
     */
    public ArrayList<Token> run(ArrayList<Token> tokens);
}