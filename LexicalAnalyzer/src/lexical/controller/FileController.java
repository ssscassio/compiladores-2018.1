package lexical.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Classe responsável pela manipulação de arquivos.
 * 
 * @author Cássio Santos
 * @author Beatriz de Brito
 */
public class FileController {

    /** Diretório padrão para leitura de arquivos de código fonte */
    public static final String INPUT_FOLDER = "entrada";
    /** Diretório padrão para escrita de arquivos de resultado */
    public static final String OUTPUT_FOLDER = "saida";

    /**
     * Construtor privado para previnir que a classe seja instanciada.
     */
    private FileController() {
        throw new AssertionError();
    }

    /**
     * Carrega os arquivos dentro da pasta entrada na raiz do projeto como uma Lista de Strings.
     * 
     * @return Map de Listas onde as chaves do map são os caminhos dos arquivos de entrada
     * e o valor é uma Lista de Strings contendo as linhas arquivo.
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
     * Carrega os arquivos dentro da pasta entrada na raiz do projeto como uma unica String
     * separada por \n.
     *  
     * @return Map de String onde as chaves do map são os caminhos dos arquivos de entrada
     * e o valor é uma única Strings contendo as linhas arquivo separadas por \n.
     */
    public static Map<String, String> readFilesAsString() throws IOException {
        return Files.list(Paths.get(INPUT_FOLDER)).filter(path -> path.toString().endsWith(".txt"))
                .collect(Collectors.toMap(path -> path.toString(), path -> {
                    try {
                        return new String(Files.lines(path, StandardCharsets.ISO_8859_1)
                                .collect(Collectors.joining("\n", "", "\0")).getBytes("ISO-8859-1"), "UTF-8");
                    } catch (IOException err) {
                        return new String();
                    }
                }));
    }

    /**
     * Método para salvar uma String em um arquivo no diretório pardrão de saída (OUTPUT_FOLDER) 
     * 
     * @param   fileName 
     *          Nome do arquivo que será salvo
     * @param   results 
     *          Conteúdo que será salvo no arquivo
     */
    public static void saveOnFile(String fileName, String results) {
        try {
            Files.createDirectories(Paths.get(OUTPUT_FOLDER));
            Files.write(Paths.get(OUTPUT_FOLDER + fileName), results.getBytes(StandardCharsets.UTF_8));
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }
}