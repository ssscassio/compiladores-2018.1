package syntax.controller;

import java.util.ArrayList;
import syntax.model.SyntaxError;

public class ErrorController {

    private static ErrorController INSTANCE = new ErrorController();

    private ArrayList<SyntaxError> errors = new ArrayList();

    private ErrorController() {
        errors = new ArrayList();
    }

    public static ErrorController getInstance() {
        return INSTANCE;
    }

    public void addError(String expected, String found, int row) {
        errors.add(new SyntaxError(expected, found, row));

    }

    public void clearErrors() {
        errors.clear();
    }

    public ArrayList<SyntaxError> getErrors() {
        return errors;
    }
}