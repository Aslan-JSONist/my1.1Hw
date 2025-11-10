package com.mipt.aslanfarajov.fileio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.*;
import java.util.*;
import java.io.*;

class FileProcessorTest {

    @TempDir
    Path tempDir;

    @Test
    void testSplitAndMergeFile() throws IOException {
        FileProcessor processor = new FileProcessor();

        Path testFile = tempDir.resolve("test.dat");
        byte[] testData = new byte[1500];
        new Random().nextBytes(testData);
        Files.write(testFile, testData);

        Path outputDir = tempDir.resolve("parts");
        List<Path> parts = processor.splitFile(testFile.toString(), outputDir.toString(), 500);

        assertEquals(3, parts.size());

        Path mergedFile = tempDir.resolve("merged.dat");
        processor.mergeFiles(parts, mergedFile.toString());

        assertArrayEquals(Files.readAllBytes(testFile), Files.readAllBytes(mergedFile));
    }
}