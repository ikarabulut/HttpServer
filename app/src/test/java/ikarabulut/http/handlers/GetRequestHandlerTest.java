package ikarabulut.http.handlers;

import ikarabulut.handlers.GetRequestHandler;
import ikarabulut.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class GetRequestHandlerTest {
    private  HashMap<String, String> initialLine = new HashMap<>();
    @Test
    @DisplayName("When the path is 'get_request' the processed response should not return a body")
    void processResponse_WithoutBody() {
        initialLine.put("httpMethod", "GET");
        initialLine.put("httpPath", "/simple_get");
        initialLine.put("httpVersion", "HTTP/1.1");
        GetRequestHandler getRequestHandler = new GetRequestHandler(initialLine);

        Response receivedResponse = getRequestHandler.processResponse();

        assertFalse(receivedResponse.hasBody());
    }

    @Test
    @DisplayName("When the path is 'get_request_with_body the processed response should have a body")
    void processResponse_WithBody() {
        initialLine.put("httpMethod", "GET");
        initialLine.put("httpPath", "/simple_get_with_body");
        initialLine.put("httpVersion", "HTTP/1.1");
        GetRequestHandler getRequestHandler = new GetRequestHandler(initialLine);

        Response receivedResponse = getRequestHandler.processResponse();

        assertTrue(receivedResponse.hasBody());
    }



}
