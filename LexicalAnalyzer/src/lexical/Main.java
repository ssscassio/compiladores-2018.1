package lexical;

import lexical.controller.FileController;

public class Main {

    public static void main(String[] args) throws Exception {

        FileController.readFilesAsString().forEach((inputFile, string) -> {
            String fileName = inputFile.replaceFirst(FileController.INPUT_FOLDER, "");
            Lexer lexer = new Lexer(string);
            lexer.analyze();
            String results = lexer.createOutputData();
            FileController.saveOnFile(fileName, results);
        });

    }
}