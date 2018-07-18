package syntax.controller;

import java.util.ArrayList;
import syntax.model.SymbolTable;

/**
 * Controlador da tabela de símbolos.
 * 
 * @author Cássio Santos
 * @author Beatriz de Brito
 */
public class SymbolTableController {

    /**
     * Cria uma instancia da classe SymbolTableController e salva na classe para ser
     * usado de forma estática.
     */
    private static SymbolTableController INSTANCE = new SymbolTableController();

    /**
     * Inicializa o vetor da instancia que criada.
     */
    private ArrayList<SymbolTable> symbols = new ArrayList<SymbolTable>();

    /**
     * Construtor privado que inicializa a lista de símbolos.
     */
    private SymbolTableController() {
        symbols = new ArrayList<SymbolTable>();
    }

    public static SymbolTableController getInstance() {
        return INSTANCE;
    }

    /**
     * Adiciona um novo símbolo à tabela de símbolos.
     * 
     * @param
     */
    public void addSymbol(int scope, String keyname, String type, String identifier, String functionProcedureName,
            String functionReturnType, String functionReturn) {
        symbols.add(new SymbolTable(scope, keyname, type, identifier, functionProcedureName, functionReturnType,
                functionReturn));
    }

    /**
     * Limpa a lista de símbolos.
     */
    public void clearSymbols() {
        symbols.clear();
    }

    /**
     * Retorna a lista de símbolos.
     * 
     * @return lista de símbolos
     */
    public ArrayList<SymbolTable> getSymbols() {
        return symbols;
    }
}