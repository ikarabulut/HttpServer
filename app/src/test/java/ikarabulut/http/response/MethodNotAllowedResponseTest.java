package ikarabulut.http.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MethodNotAllowedResponseTest {
    private String version = "HTTP/1.1";
    private String statusCode = StatusCode.NOT_ALLOWED.getStatusCode();
    private String statusNumber = StatusCode.NOT_ALLOWED.getStatusNumber();
    private Map<String, String> headers = new HashMap<>() {
        {
            put("Allow", "HEAD, OPTIONS");
        }
    };

    @Test
    @DisplayName("A MethodNotAllowedResponse should return a 405 error with the allow header")
    void stringifyResponse() {
        String expectedResponse = version + " " + statusNumber + " " + statusCode + "\r\n" +
                "Allow: " + headers.get("Allow") + "\r\n" + "\r\n";
        Response response = new MethodNotAllowedResponse(version, statusCode, statusNumber, headers);
        String stringifiedResponse = response.stringifyResponse();

        assertEquals(expectedResponse, stringifiedResponse);
    }

}
