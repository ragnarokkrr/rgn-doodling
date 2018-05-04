package ragna.c05;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterARM implements AutoCloseable {
    private final FileWriter writer;

    public FileWriterARM(String fileName) throws IOException {
        this.writer = new FileWriter(fileName);
    }

    public void writeStuff(final String message) throws IOException {
        writer.write(message);
    }

    @Override
    public void close() throws IOException {
        System.out.println("close called automatically");
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        try(final FileWriterARM writerARM = new FileWriterARM("peeckaboo.txt")) {
            writerARM.writeStuff("peek-a-boo");
        }
    }
}
