package syntax.model;

public class Symbol {

    private String name = "";
    private String scope = "";
    private String category = "";
    private String type = "";
    private String params = ""; // Wrong type
    private String fields = ""; // Wrong type
    private String scopeReference = ""; // Wrong type

    public Symbol() {

    }

    public Symbol(Symbol symbol) {
        this.name = symbol.name;
        this.scope = symbol.scope;
        this.category = symbol.category;
        this.type = symbol.type;
        this.params = symbol.params;
        this.fields = symbol.fields;
        this.scopeReference = symbol.scopeReference;
    }

    public void updateField(String fieldName, String value) {
        switch (fieldName) {
        case "name":
            this.name = value;
            break;
        case "scope":
            this.scope = value;
            break;
        case "category":
            this.category = value;
            break;
        case "type":
            this.type = value;
            break;
        case "params":
            this.params = value;
            break;
        case "fields":
            this.fields = value;
            break;
        case "scopeReference":
            this.scopeReference = value;
        }
    }

    public String getField(String fieldName) {
        switch (fieldName) {
        case "name":
            return this.name;
        case "scope":
            return this.scope;
        case "category":
            return this.category;
        case "type":
            return this.type;
        case "params":
            return this.params;
        case "fields":
            return this.fields;
        case "scopeReference":
            return this.scopeReference;
        default:
            return "";
        }
    }

    @Override
    public String toString() {
        return this.name + " | " + this.scope + " | " + this.category + " | " + this.type + " | " + this.params + " | "
                + this.fields + " | " + this.scopeReference + " | ";
    }
}