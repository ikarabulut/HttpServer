package com.ikarabulut.requests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class HeadRequestRunnerTest {

    @Test
    @DisplayName("When a path is not allowed then isValidPath should return false")
    void isAllowedPath_NotAllowed() {
        HashMap<String, String> initialLine = new HashMap<>();
        initialLine.put("httpMethod", "HEAD");
        initialLine.put("httpPath", "/not_allowed_path");
        initialLine.put("httpVersion", "HTTP/1.1");

        HeadRequestRunner headRequestRunner = new HeadRequestRunner(initialLine);

        assertFalse(headRequestRunner.isValidPath());
    }

    @Test
    @DisplayName("When a path is allowed then isValidPath should return true")
    void isAllowedPath_IsAllowed() {
        HashMap<String, String> initialLine = new HashMap<>();
        initialLine.put("httpMethod", "HEAD");
        initialLine.put("httpPath", "/simple_get");
        initialLine.put("httpVersion", "HTTP/1.1");

        HeadRequestRunner headRequestRunner = new HeadRequestRunner(initialLine);

        assertTrue(headRequestRunner.isValidPath());
    }

}
