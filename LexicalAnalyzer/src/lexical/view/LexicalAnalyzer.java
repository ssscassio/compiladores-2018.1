package lexical.view;

import lexical.util.LexemeType;
import lexical.controller.FileController;

public class LexicalAnalyzer {

    public static void main(String[] args) throws Exception {
        System.out.println("Analisador Léxico");
        System.out.println(LexemeType.iskeyword("const"));
        FileController.readFiles().forEach((key, value) -> {
            System.out.println(value);
        });
    }
}