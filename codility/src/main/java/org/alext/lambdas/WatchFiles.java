package org.alext.lambdas;


import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by tsumaraa on 09/09/2016.
 */
public class WatchFiles {

    public void watchCFiles() throws IOException, InterruptedException {
        String drive = System.getenv("SystemDrive");

        //Path path= Paths.get(drive);
        Path path= Paths.get("C:\\del");
        WatchService watchService = path.getFileSystem().newWatchService();

        path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

        final WatchKey watchKey=watchService.poll(1, TimeUnit.MINUTES);

        if(watchKey!=null){
            watchKey.pollEvents().stream().forEach(System.out::println);
        }

        System.out.println("End, going to sleep");
        Thread.sleep(60*1000);

    }
}
