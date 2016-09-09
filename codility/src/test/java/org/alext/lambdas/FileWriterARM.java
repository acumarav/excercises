package org.alext.lambdas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by tsumaraa on 09/09/2016.
 */
@FunctionalInterface
interface UseInstance<T, X extends Throwable> {
    void accept(T instance) throws X;
}

public class FileWriterARM implements AutoCloseable {

    private final FileWriter writer;

    public static void use(File file,
                           final UseInstance<FileWriterARM, IOException> block) throws IOException {
        final FileWriterARM writerARM = new FileWriterARM(new FileWriter(file));
        try {
            block.accept(writerARM);
        } finally {
            writerARM.close();
        }
    }

    public static void run(File file) throws IOException {
        FileWriter writer = new FileWriter(file);
        try (final FileWriterARM writerARM = new FileWriterARM(writer)) {
            writerARM.writeStuff("peek-a-boo");


            System.out.println("done with the resource...");

        }
    }

    private FileWriterARM(FileWriter writer) {
        this.writer = writer;
    }

    public void writeStuff(final String message) throws IOException {
        writer.write(message);
    }

    @Override
    public void close() throws IOException {
        System.out.println("close called automatically...");
        writer.close();
    }
}
