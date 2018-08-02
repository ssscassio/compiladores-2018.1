import lexical.controller.FileController;
import syntax.controller.ErrorController;
import lexical.Lexer;
import syntax.Parser;

/**
 * Classe principal do projeto.
 * 
 * @author Cássio Santos
 * @author Beatriz de Brito
 */
public class Main {

    public static void main(String[] args) throws Exception {
        // Leitura do arquivo de entrada
        FileController.readFilesAsString().forEach((inputFile, string) -> {
            String fileName = inputFile.replaceFirst(FileController.INPUT_FOLDER, "");
            System.out.println(fileName);
            // Análise léxica
            Lexer lexer = new Lexer(string);
            lexer.analyze();

            // Análise sintática
            Parser parser = new Parser(lexer.getTokensList());
            parser.analyze();

            // Escrita de Tokens e Erros no arquivo de saída
            String results = parser.createOutputDataSemantic();
            FileController.saveOnFile(fileName, results);
            ErrorController.getInstance().clearErrors();
        });

    }
}