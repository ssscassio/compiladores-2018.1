package syntax.controller;

import java.util.ArrayList;
import syntax.model.SymbolTable;

public class SymbolTableController {

    private static SymbolTableController INSTANCE = new SymbolTableController();

    private ArrayList<SymbolTable> symboltable = new ArrayList<SymbolTable>();

    private ErrorController() {
        symboltable = new ArrayList<SymbolTable>();
    }

    public static SymbolTableController getInstance() {
        return INSTANCE;
    }

    public void clearSymbolTable() {
        symboltable.clear();
    }

    public ArrayList<SyntaxError> getSymbolTable() {
        return symboltable;
    }
}