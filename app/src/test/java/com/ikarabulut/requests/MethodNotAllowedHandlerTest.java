package com.ikarabulut.requests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MethodNotAllowedHandlerTest {

    private Map<String, String> initialLine = new HashMap<>(){{
        put("httpMethod", "HEAD");
        put("httpPath", "/simple_get");
        put("httpVersion", "HTTP/1.1");
    }};

    @Test
    @DisplayName(("MethodNotAllowedHandler should generate a header displaying the allowed methods"))
    void generateHeaders() {
        List<String> allowedMethods = new ArrayList<>(Arrays.asList("HEAD", "GET"));
        Map<String, String> expectedHeader = new HashMap<>() {{ put("Allow", "HEAD, OPTIONS"); }};

        MethodNotAllowedHandler handler = new MethodNotAllowedHandler(allowedMethods, initialLine);

        Map<String, String> generatedHeader = handler.generateAllowHeaders();

        assertEquals(expectedHeader, generatedHeader);
    }

}
