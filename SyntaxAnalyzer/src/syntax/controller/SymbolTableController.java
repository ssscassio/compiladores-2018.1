package syntax.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import syntax.model.Symbol;

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
    private static SymbolTableController INSTANCE_TABLE = new SymbolTableController();

    private static Symbol cache = new Symbol();

    private static String field = "";

    private static int scope = 0;

    private static int lastScope = 0;

    private static HashMap<String, ArrayList<Symbol>> symbolTable = new HashMap<String, ArrayList<Symbol>>();

    /**
     * Construtor privado que inicializa a lista de símbolos.
     */
    private SymbolTableController() {
        cache = new Symbol();
    }

    public static void updateCache(String fieldName, String value) {
        cache.updateField(fieldName, value);
    }

    public String getCacheString() {
        return cache.toString();
    }

    public static void clearCache() {
        cache = new Symbol();
    }

    public static Symbol getCache() {
        return cache;
    }

    public static String getField() {
        return field;
    }

    public static int getLastScope() {
        return lastScope;
    }

    public static void updateLastScope() {
        lastScope = lastScope + 1;
    }

    public static void setField(String fieldParam) {
        field = fieldParam;
    }

    public static String getScope() {
        return scope;
    }

    public static void setScope(int scopeParam) {
        scope = scopeParam;
    }

    public static SymbolTableController getInstance() {
        return INSTANCE_TABLE;
    }

    public static void createSymbolFromCache() {
        cache.updateField("scope", scope + "");
        if (!symbolTable.containsKey(cache.getField("name"))) {
            symbolTable.put(cache.getField("name"), new ArrayList<>(Arrays.asList(new Symbol(cache))));
        } else {
            symbolTable.get(cache.getField("name")).add(new Symbol(cache));
        }
    }

    // New Scope Symbol
    public static void createSymbolFromCache(int scopeReference) {
        cache.updateField("scopeReference", scopeReference + "");
        createSymbolFromCache();
    }

    public static HashMap<String, ArrayList<Symbol>> getTable() {
        return symbolTable;
    }
}