package ragna.c03;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchService;

public class WatchFileChange {

    public static void main(String[] args) throws IOException {
        final Path path = Paths.get("src/main/java/ragna/c03");

        final WatchService watchService = path.getFileSystem().newWatchService();

        path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

        System.out.println("Report any file changed within next 1 mnute....");


    }

}
