package lexical;

import lexical.controller.FileController;

/**
 * Classe principal do projeto.
 * 
 * @author Cássio Santos
 * @author Beatriz de Brito
 */
public class Main {

    /**
     * Metodo responsável por chamar o metodo de leitura do arquivo de entrada,
     * encaminhar para a analise lexica e chamar o metodo para a escrita
     * no arquivo de saida.
     */
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