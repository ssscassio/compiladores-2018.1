package lexical;

import lexical.controller.FileController;

/**
 * Classe principal do projeto.
 * 
 * @author Cássio Santos
 * @author Beatriz de Brito
 */
public class Main {

    public static void main(String[] args) throws Exception {
        // Leitura do arquivo de entrada
        // Análise léxica
        // Escrita de Tokens e Erros no arquivo de saída
        FileController.readFilesAsString().forEach((inputFile, string) -> {
            String fileName = inputFile.replaceFirst(FileController.INPUT_FOLDER, "");
            Lexer lexer = new Lexer(string);
            lexer.analyze();
            String results = lexer.createOutputData();
            FileController.saveOnFile(fileName, results);
        });

    }
}