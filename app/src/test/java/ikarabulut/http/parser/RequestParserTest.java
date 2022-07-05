package ikarabulut.http.parser;

import ikarabulut.http.io.ClientReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RequestParserTest {
    private String input = "HEAD /test HTTP/1.1\r\n" +
            "Content-Type" + ":" + " text/plain\r\n" +
            "User-Agent" + ":" + " PostmanRuntime/7.29.0\r\n\r\n" +
            "Hello World\r\n";
    private String inputWithoutBody = "HEAD /test HTTP/1.1\r\n" +
            "Content-Type" + ":" + " text/plain\r\n" +
            "User-Agent" + ":" + " PostmanRuntime/7.29.0\r\n\r\n";

    @Test
    @DisplayName("When parsing the initial line, a HashMap containing 'httpMethod', 'httpPath', 'httpVersion' keys and corresponding keys should be generated")
    void parseInitialLine() throws IOException {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        ClientReader clientReader = new ClientReader(in);
        RequestParser requestParser = new RequestParser(clientReader);

        Map<String, String> expectedInitialLine = new HashMap<>();
        expectedInitialLine.put("httpMethod", "HEAD");
        expectedInitialLine.put("httpPath", "/test");
        expectedInitialLine.put("httpVersion", "HTTP/1.1");

        Map<String, String> returnedInitialLine = requestParser.parseInitialLine();

        assertEquals(expectedInitialLine, returnedInitialLine);
    }

    @Test
    @DisplayName("Given a request with 'Content-Type' and 'User-Agent' headers is sent, then a hashmap containing those headers as keys should be generated with corresponding keys")
    void parseHeaders() throws IOException {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        ClientReader clientReader = new ClientReader(in);
        RequestParser requestParser = new RequestParser(clientReader);

        HashMap<String, String> expectedHeaders = new HashMap<>();
        expectedHeaders.put("Content-Type", "text/plain");
        expectedHeaders.put("User-Agent", "PostmanRuntime/7.29.0");

        Map<String, String> returnedHeaders = requestParser.parseHeaders();

        assertEquals(expectedHeaders, returnedHeaders);
    }

    @Test
    @DisplayName("A Request with a body should have that body parsed into a body object within RequestParser ")
    void parseBody() throws IOException {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        ClientReader clientReader = new ClientReader(in);
        RequestParser requestParser = new RequestParser(clientReader);

        requestParser.parseBody();

        String parsedBody = requestParser.getBody();
        String expectedParsedBody = "Hello World";
        assertEquals(expectedParsedBody, parsedBody);
    }

    @Test
    @DisplayName("A Request without a body should return a null body object")
    void parseBody_WhenNoBody() throws IOException {
        InputStream in = new ByteArrayInputStream(inputWithoutBody.getBytes());
        ClientReader clientReader = new ClientReader(in);
        RequestParser requestParser = new RequestParser(clientReader);

        requestParser.parseBody();

        String parsedBody = requestParser.getBody();
        assertNull(parsedBody);
    }


}
