package ikarabulut.http.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PageNotFoundResponseTest {
    private String version = "HTTP/1.1";
    private StatusCode status = StatusCode.NOT_FOUND;

    @Test
    @DisplayName("A Response should be generated with a 405 code and PAGE NOT FOUND code")
    void stringifyResponse_ShouldHave404() {
        Response response = new PageNotFoundResponse(version, status);

        String receivedResponse = response.stringifyResponse();

        assertTrue(receivedResponse.contains("PAGE NOT FOUND"));
        assertTrue(receivedResponse.contains("404"));
    }

}
