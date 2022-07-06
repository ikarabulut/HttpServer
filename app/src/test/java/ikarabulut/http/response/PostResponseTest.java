package ikarabulut.http.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PostResponseTest {
    private String version = "HTTP/1.1";
    private StatusCode status = StatusCode.OK;
    private String body = "hello world";

    @Test
    @DisplayName("PostResponse should be able to generate headers with at minimum the date")
    void generateHeaders_DefaultValues() {
        Map<String,String> defaultHeader = new HashMap<>() {{
            put("Date", new Date().toString());
        }};
        Response response = new PostResponse(version, status, body);

        Map<String, String> generatedHeaders = response.generateHeaders();

        assertTrue(generatedHeaders.containsKey("Date"));
    }

    @Test
    @DisplayName("A PostResponse with a status code of OK should generate a string response with 200 and OK")
    void stringifyResponse_HasStatusCodeValues() {
        Response response = new PostResponse(version, status, body);

        String receivedResponse = response.stringifyResponse();

        assertTrue(receivedResponse.contains("OK"));
        assertTrue(receivedResponse.contains("200"));
    }

    @Test
    @DisplayName("A PostResponse with using HTTP 1.1 protocol should generate a string response with HTTP/1.1")
    void stringifyResponse_HasCorrectVersion() {
        Response response = new PostResponse(version, status, body);

        String receivedResponse = response.stringifyResponse();

        assertTrue(receivedResponse.contains("HTTP/1.1"));
    }

}
