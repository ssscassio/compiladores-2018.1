package syntax.controller.grammar;

import java.util.ArrayList;

import lexical.model.Token;

/**
 * Interface de definição de uma produção.
 *
 * @see Production
 * @author Cássio Santos
 * @author Beatriz de Brito
 */
public interface Production {

    public boolean hasAsFirst(Token token);

    public boolean hasAsFollow(Token token);

    public ArrayList<Token> run(ArrayList<Token> tokens);
}