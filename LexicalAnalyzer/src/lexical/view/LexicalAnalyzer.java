package lexical.view;

import lexical.util.LexemeType;

public class LexicalAnalyzer {

    public static void main(String[] args) throws Exception {
        System.out.println("Analisador Léxico");
        System.out.println(LexemeType.iskeyword("const"));
    }
}