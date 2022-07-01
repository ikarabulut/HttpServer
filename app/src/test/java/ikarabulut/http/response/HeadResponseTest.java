package ikarabulut.http.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HeadResponseTest {
    private String version = "HTTP/1.1";
    private StatusCode status = StatusCode.OK;
    private Map<String, String> headers = new HashMap<>() {
        {
            put("Date", new Date().toString());
            put("Content-Language", "en-US");
        }
    };

    @Test
    @DisplayName("With a valid path, stringifyHeadResponse should return a string with a status line and headers")
    void stringifyResponse_ForHead() {
        String statusNumber = status.getStatusNumber();
        String statusCode = status.getStatusCode();

        String expectedResponse = version + " " + statusNumber + " " + statusCode + "\r\n" +
                "Date: " + headers.get("Date") + "\r\n" +
                "Content-Language: " + headers.get("Content-Language") + "\r\n" +
                "\r\n";
        HeadResponse headResponse = new HeadResponse(version, status);

        String stringifiedResponse = headResponse.stringifyResponse();

        assertEquals(expectedResponse, stringifiedResponse);
    }

    @Test
    @DisplayName("A Header should be generated with at minimum the date")
    void generateHeaders_DefaultDate() {
        Map<String,String> defaultHeader = new HashMap<>() {{
            put("Date", new Date().toString());
        }};
        Response response = new HeadResponse(version, status);

        Map<String, String> generatedHeaders = response.generateHeaders();

        assertTrue(generatedHeaders.containsKey("Date"));
    }

}
