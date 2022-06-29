package ikarabulut.http.parser;

import ikarabulut.http.io.ClientReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RequestParserTest {
    private String input = "HEAD /test HTTP/1.1\r\n" +
            "Content-Type" + ":" + " text/plain\r\n" +
            "User-Agent" + ":" + " PostmanRuntime/7.29.0\r\n\r\n" +
            "Hello World\r\n";

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


}
