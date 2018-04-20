package lexical.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileController {

    private static final String INPUT_FOLDER = "entrada";
    private static final String OUTPUT_FOLDER = "saida";

    /**
     * Construtor privado para previnir que a classe seja instanciada
     */
    private FileController() {
        throw new AssertionError();
    }

    /**
     * Carrega os arquivos dentro da pasta entrada na raiz do projeto
     * 
     * @return Map de Listas onde as chaves do map são os caminhos dos arquivos de entrada
     * e o valor é uma Lista de Strings contendo as linhas arquivo
     */
    public static Map<String, List<String>> readFiles() throws IOException {
        return Files.list(Paths.get(INPUT_FOLDER)).filter(path -> path.toString().endsWith(".txt"))
                .collect(Collectors.toMap(path -> path.toString(), path -> {
                    try {
                        return Files.readAllLines(path);
                    } catch (IOException err) {
                        return new ArrayList<String>();
                    }
                }));
    }

    /**
     * Carrega os arquivos separando o conteúdo de cada um em Strings.
     *  
     * @return String com todas as linhas do arquivo separadas por \n
     */
    public static Map<String, String> readFilesToString() throws IOException {
        return Files.list(Paths.get(INPUT_FOLDER)).filter(path -> path.toString().endsWith(".txt"))
                .collect(Collectors.toMap(path -> path.toString(), path -> {
                    try {
                        return Files.lines(path).collect(Collectors.joining("\n", "", "\0"));
                    } catch (IOException err) {
                        return new String();
                    }
                }));
    }

    /**
     * Junta os resultados obtidos do analisador léxico: tokens e erros.
     *  
     * @return String com o resultado completo.
     */
    public static String createOutputData(ArrayList tokens, ArrayList errors){
            String results = "";
            for (int i=0; i < tokens.size(); i++){
                results = results+tokens.get(i).toString()+System.lineSeparator();
            }
            results = results+System.lineSeparator();
            for (int i=0; i < errors.size(); i++){
                results = results+errors.get(i).toString()+System.lineSeparator();
            }
            return results;
    }

    
    /**
     * Gera o arquivo de saida com os resultados obtidos.
     *  
     */
    public static void saveOnFile(String fileName, String results){
        try{
            Files.createDirectories(Paths.get(OUTPUT_FOLDER));    
            Files.write(Paths.get("./"+fileName), (results).getBytes());
        }catch(IOException ie){
            ie.printStackTrace();
        }
    }

}