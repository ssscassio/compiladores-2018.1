package lexical.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileController {

    private static final String INPUT_FOLDER = "entrada";
    private static final String OUTPUT_FOLDER = "saida";

    public FileController() {

    }

    // TODO: Esse método deve retornar um Map onde a chave é o nome do arquivo
    // e o valor é um Array de Strings onde cada 'row' do Array é uma linha do arquivo.
    public static void readFiles() {

        try {
            Files.list(Paths.get(INPUT_FOLDER)).filter(path -> path.toString().endsWith(".txt"))
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}