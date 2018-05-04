package ragna.c03;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class ListSelectFiles {

    public static void main(String[] args) throws IOException {
        final String[] files = new File("src/main/java/ragna/c03").list(new java.io.FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".java");
            }
        });
        System.out.println(Arrays.asList(files));

        System.out.println();

        Files.newDirectoryStream(
                Paths.get("src/main/java/ragna/c03"),
                path -> path.toString().endsWith(".java")
        ).forEach(System.out::println);

        System.out.println();



    }

}
