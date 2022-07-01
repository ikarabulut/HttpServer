package ikarabulut.http.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GetResponseTest {
    private String version = "HTTP/1.1";
    private StatusCode status = StatusCode.OK;
    private Map<String, String> headers = new HashMap<>() {
        {
            put("Date", new Date().toString());
            put("Content-Language", "en-US");
        }
    };

    @Test
    @DisplayName("A GetResponse with a body should return a string with a body when stringified")
    void stringifyResponse_WithBody() {
        String expectedBody = "Hello World";
        String statusNumber = status.getStatusNumber();
        String expectedResponse = version + " " + statusNumber + " " + status + "\r\n" +
                "Date: " + headers.get("Date") + "\r\n" +
                "Content-Language: " + headers.get("Content-Language") + "\r\n" +
                "\r\n" +
                expectedBody;
        GetResponse getResponse = new GetResponse(version, status, headers, expectedBody);
        String stringifiedResponse = getResponse.stringifyResponse();

        assertEquals(expectedResponse, stringifiedResponse);
    }

    @Test
    @DisplayName("A GetResponse without a body should return a string without a body when stringified")
    void stringifyResponse_WithoutBody() {
        String statusCode = status.getStatusCode();
        String statusNumber = status.getStatusNumber();
        String expectedResponse = version + " " + statusNumber + " " + statusCode + "\r\n" +
                "Date: " + headers.get("Date") + "\r\n" +
                "Content-Language: " + headers.get("Content-Language") + "\r\n" + "\r\n";
        GetResponse getResponse = new GetResponse(version, status, headers);

        String stringifiedResponse = getResponse.stringifyResponse();

        assertEquals(expectedResponse, stringifiedResponse);
    }

}
