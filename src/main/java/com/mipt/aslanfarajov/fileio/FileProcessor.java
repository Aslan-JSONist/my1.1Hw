package com.mipt.aslanfarajov.fileio;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.file.*;
import java.util.*;

public class FileProcessor {

    public List<Path> splitFile(String sourcePath, String outputDir, int partSize) throws IOException {
        List<Path> parts = new ArrayList<>();
        Path sourceFile = Paths.get(sourcePath);
        String fileName = sourceFile.getFileName().toString();

        Files.createDirectories(Paths.get(outputDir));

        try (FileChannel sourceChannel = FileChannel.open(sourceFile, StandardOpenOption.READ)) {
            ByteBuffer buffer = ByteBuffer.allocate(partSize);
            int partNumber = 1;

            while (sourceChannel.read(buffer) > 0) {
                buffer.flip();

                String partName = fileName + ".part" + partNumber;
                Path partPath = Paths.get(outputDir, partName);

                try (FileChannel partChannel = FileChannel.open(partPath,
                        StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
                    partChannel.write(buffer);
                }

                parts.add(partPath);
                partNumber++;
                buffer.clear();
            }
        }

        return parts;
    }

    public void mergeFiles(List<Path> partPaths, String outputPath) throws IOException {
        try (FileChannel outputChannel = FileChannel.open(Paths.get(outputPath),
                StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {

            for (Path partPath : partPaths) {
                if (!Files.exists(partPath)) {
                    throw new IOException("Part file not found: " + partPath);
                }

                try (FileChannel partChannel = FileChannel.open(partPath, StandardOpenOption.READ)) {
                    partChannel.transferTo(0, partChannel.size(), outputChannel);
                }
            }
        }
    }
}