package lexical.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
                        Stream<String> stringList = Files.lines(path);
                        List<String> list = new ArrayList<>();
                        stringList.forEach((line) ->{
                            list.add(line.toString()+"\n"); 
                        });
                        String lines = "";
                        for(int i=0; i<list.size(); i++){
                            lines = lines+list.get(i);
                        }
                         return lines;
                    } catch (IOException err) {
                        return new String();
                    }
                }));
    }

}