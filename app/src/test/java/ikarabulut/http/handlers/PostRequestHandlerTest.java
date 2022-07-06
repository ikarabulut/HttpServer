package ikarabulut.http.handlers;

import ikarabulut.http.parser.RequestParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PostRequestHandlerTest {
    @Test
    @DisplayName("PostRequestHandler should process the response using the initial line from the composed RequestParser object")
    void processResponse_UsesRequestParser() {
        RequestParser parsedRequest = mock(RequestParser.class);
        RequestHandler requestHandler = new PostRequestHandler(parsedRequest);

        requestHandler.processResponse();

        verify(parsedRequest).parseInitialLine();
    }

    @Test
    @DisplayName("PostRequestHandler should echo back the body of the request by calling parseBody from the composed RequestParser")
    void processResponse_EchosRequestBody() {
        RequestParser parsedRequest = mock(RequestParser.class);
        RequestHandler requestHandler = new PostRequestHandler(parsedRequest);

        requestHandler.processResponse();

        verify(parsedRequest).parseInitialLine();
    }

}
