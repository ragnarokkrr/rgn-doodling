package ragna.c03;

import java.io.File;
import java.util.Arrays;

public class ListHiddenFiles {

    public static void main(String[] args) {
        final File[] files2 = new File(".").listFiles(file->file.isHidden());

        System.out.println(Arrays.asList(files2));


        final File[] files3 = new File(".").listFiles(File::isHidden);

        System.out.println(Arrays.asList(files3));

    }

}
