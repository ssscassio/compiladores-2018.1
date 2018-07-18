package syntax.model;

public class SymbolTable {

    private final int scope; // 0 = global, 1 = local
    private final String keyname; // palavra chave
    private final String type;
    private final String identifier;
    private final String functionProcedureName;
    private final String functionReturnType;
    private final String functionReturn;

    public SymbolTable(int scope, String keyname, String type, String identifier, String functionProcedureName,
            String functionReturnType, String functionReturn) {
        this.scope = scope;
        this.keyname = keyname;
        this.type = type;
        this.identifier = identifier;
        this.functionProcedureName = functionProcedureName;
        this.functionReturnType = functionReturnType;
        this.functionReturn = functionReturn;

    }

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