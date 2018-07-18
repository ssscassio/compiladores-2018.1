package syntax.model;

public class SymbolTable {

    private final int scope; // 0 = global, 1 = local
    private final String keyname; // palavra chave
    private final String type;
    private final String identifier;
    private final String functionProcedureName;
    private final String functionReturnType;
    private final String functionReturn;

    public int getScope() {
        return this.scope;
    }

    public String getKeyName() {
        return this.keyname;
    }

    public String getType() {
        return this.type;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public String getfunctionProcedureName() {
        return this.functionProcedureName;
    }

    public String getfunctionReturnType() {
        return this.functionReturnType;
    }

    public String getfunctionReturn() {
        return this.functionReturn;
    }
}