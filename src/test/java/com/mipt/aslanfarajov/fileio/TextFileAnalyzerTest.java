package com.mipt.aslanfarajov.fileio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.*;
import java.io.*;
import java.util.*;

class TextFileAnalyzerTest {

    @TempDir
    Path tempDir;

    @Test
    void testAnalyzeFile() throws IOException {
        TextFileAnalyzer analyzer = new TextFileAnalyzer();

        Path testFile = tempDir.resolve("test.txt");
        Files.write(testFile, Arrays.asList("Hello world!", "This is test."));

        TextFileAnalyzer.AnalysisResult result = analyzer.analyzeFile(testFile.toString());

        assertEquals(2, result.getLineCount());
        assertEquals(5, result.getWordCount());
        assertEquals(27, result.getCharCount());
    }

    @Test
    void testSaveAnalysisResult() throws IOException {
        TextFileAnalyzer analyzer = new TextFileAnalyzer();

        TextFileAnalyzer.AnalysisResult result = new TextFileAnalyzer.AnalysisResult(2, 5, 20);

        Path outputFile = tempDir.resolve("analysis.txt");
        analyzer.saveAnalysisResult(result, outputFile.toString());

        assertTrue(Files.size(outputFile) > 0);

        String content = Files.readString(outputFile);
        assertTrue(content.contains("Line count: 2"));
        assertTrue(content.contains("Word count: 5"));
        assertTrue(content.contains("Character count: 20"));
    }
}