package com.ikarabulut.requests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class HeadRequestRunnerTest {
    private List<String> acceptedPaths = new ArrayList<>();

    private void expectedGetValidPaths() {
        File allowedPaths = new File("src/pathfiles/HEADpaths.txt");
        try {
            Scanner fileReader = new Scanner(allowedPaths);
            while (fileReader.hasNextLine()) {
                String path = fileReader.nextLine();
                acceptedPaths.add(path);
            }
            fileReader.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    @DisplayName("When a HeadRequestRunner is constructed the accepted paths should be generated from the pathfiles directory and its HEADpaths.txt file")
    void areAcceptedPathsGenerated() {
        HashMap<String, String> initialLine = new HashMap<>();
        String httpMethod = "HEAD";
        String httpPath = "/test";
        String httpVersion = "HTTP/1.1";
        initialLine.put("httpMethod", httpMethod);
        initialLine.put("httpPath", httpPath);
        initialLine.put("httpVersion", httpVersion);
        HeadRequestRunner headRequestRunner = new HeadRequestRunner(initialLine);
        expectedGetValidPaths();

        List<String> returnedPaths = headRequestRunner.getAcceptedPaths();

        assertEquals(acceptedPaths, returnedPaths);
    }


    @Test
    @DisplayName("When a passed path is not allowed then isValidPath should return false")
    void isAllowedPath_NotAllowed() {
        HashMap<String, String> initialLine = new HashMap<>();
        String httpMethod = "HEAD";
        String httpPath = "/not_allowed_path";
        String httpVersion = "HTTP/1.1";
        initialLine.put("httpMethod", httpMethod);
        initialLine.put("httpPath", httpPath);
        initialLine.put("httpVersion", httpVersion);
        HeadRequestRunner headRequestRunner = new HeadRequestRunner(initialLine);

        assertFalse(headRequestRunner.isValidPath());
    }

    @Test
    @DisplayName("When a passed path is allowed then isValidPath should return true")
    void isAllowedPath_IsAllowed() {
        HashMap<String, String> initialLine = new HashMap<>();
        String httpMethod = "HEAD";
        String httpPath = "/simple_get";
        String httpVersion = "HTTP/1.1";
        initialLine.put("httpMethod", httpMethod);
        initialLine.put("httpPath", httpPath);
        initialLine.put("httpVersion", httpVersion);
        HeadRequestRunner headRequestRunner = new HeadRequestRunner(initialLine);

        assertTrue(headRequestRunner.isValidPath());
    }

}
