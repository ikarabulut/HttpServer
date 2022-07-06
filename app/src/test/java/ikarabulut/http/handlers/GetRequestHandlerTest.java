package ikarabulut.http.handlers;

import ikarabulut.http.parser.RequestParser;
import ikarabulut.http.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetRequestHandlerTest {
    private  HashMap<String, String> initialLine = new HashMap<>();
    @Test
    @DisplayName("When the path is 'get_request' the processed response should not return a body")
    void processResponse_WithoutBody() {
        RequestParser parsedRequest = mock(RequestParser.class);
        initialLine.put("httpMethod", "GET");
        initialLine.put("httpPath", "/simple_get");
        initialLine.put("httpVersion", "HTTP/1.1");
        when(parsedRequest.parseInitialLine()).thenReturn(initialLine);
        GetRequestHandler getRequestHandler = new GetRequestHandler(parsedRequest);

        Response receivedResponse = getRequestHandler.processResponse();

        assertFalse(receivedResponse.hasBody());
    }

    @Test
    @DisplayName("When the path is 'get_request_with_body the processed response should have a body")
    void processResponse_WithBody() {
        RequestParser parsedRequest = mock(RequestParser.class);
        initialLine.put("httpMethod", "GET");
        initialLine.put("httpPath", "/simple_get_with_body");
        initialLine.put("httpVersion", "HTTP/1.1");
        when(parsedRequest.parseInitialLine()).thenReturn(initialLine);
        GetRequestHandler getRequestHandler = new GetRequestHandler(parsedRequest);

        Response receivedResponse = getRequestHandler.processResponse();

        assertTrue(receivedResponse.hasBody());
    }

}
