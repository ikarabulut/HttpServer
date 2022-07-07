package ikarabulut.http.server;

import ikarabulut.http.parser.RequestParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.HashMap;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RouterTest {
    private HashMap<String, String> initialLine = new HashMap<>();

    @Test
    @DisplayName("When a Router takes in a HEAD request with a valid path then routeRequest should call runHeadMethod")
    void headRequestHandler() {
        RequestParser parsedRequest = mock(RequestParser.class);
        initialLine.put("httpMethod", "HEAD");
        initialLine.put("httpPath", "/head_request");
        initialLine.put("httpVersion", "HTTP/1.1");
        when(parsedRequest.parseInitialLine()).thenReturn(initialLine);
        Router router = new Router(parsedRequest);
        Router spyRouter = Mockito.spy(router);

        spyRouter.routeRequest();

        Mockito.verify(spyRouter).runHeadRequest();
    }

    @Test
    @DisplayName("When a Router takes in a GET request  with a valid path then routeRequest should call runGetMethod")
    void getRequestHandler() {
        RequestParser parsedRequest = mock(RequestParser.class);
        initialLine.put("httpMethod", "GET");
        initialLine.put("httpPath", "/simple_get");
        initialLine.put("httpVersion", "HTTP/1.1");
        when(parsedRequest.parseInitialLine()).thenReturn(initialLine);
        Router router = new Router(parsedRequest);
        Router spyRouter = Mockito.spy(router);

        spyRouter.routeRequest();

        Mockito.verify(spyRouter).runGetRequest();
    }

    @Test
    @DisplayName("When a GET request is made to a HEAD request path, then methodNotFound() will be called")
    void routeRequest_NotAllowed() {
        RequestParser parsedRequest = mock(RequestParser.class);
        String headPath = "/head_request";
        initialLine.put("httpMethod", "GET");
        initialLine.put("httpPath", headPath);
        initialLine.put("httpVersion", "HTTP/1.1");
        when(parsedRequest.parseInitialLine()).thenReturn(initialLine);
        Router router = new Router(parsedRequest);
        Router spyRouter = Mockito.spy(router);

        spyRouter.routeRequest();

        Mockito.verify(spyRouter).methodNotAllowed(Arrays.asList("HEAD", "OPTIONS"));
    }

    @Test
    @DisplayName("When a POST request is made with a valid path, then routeRequest should call runPostMethod()")
    void routeRequest_PostRequest() {
        RequestParser parsedRequest = mock(RequestParser.class);
        initialLine.put("httpMethod", "POST");
        initialLine.put("httpPath", "/echo_body");
        initialLine.put("httpVersion", "HTTP/1.1");
        when(parsedRequest.parseInitialLine()).thenReturn(initialLine);
        Router router = new Router(parsedRequest);
        Router spyRouter = Mockito.spy(router);

        spyRouter.routeRequest();

        Mockito.verify(spyRouter).runPostRequest();
    }

    @Test
    @DisplayName("When a request includes an unconfigured resource then runPageNotFound should be called")
    void routeRequest_PageNotFound() {
        RequestParser parsedRequest = mock(RequestParser.class);
        initialLine.put("httpMethod", "POST");
        initialLine.put("httpPath", "/unconfigured_resource");
        initialLine.put("httpVersion", "HTTP/1.1");
        when(parsedRequest.parseInitialLine()).thenReturn(initialLine);
        Router router = new Router(parsedRequest);
        Router spyRouter = Mockito.spy(router);

        spyRouter.routeRequest();

        Mockito.verify(spyRouter).runPageNotFound();
    }
}
