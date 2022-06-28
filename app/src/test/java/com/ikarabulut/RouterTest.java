package com.ikarabulut;

import com.ikarabulut.server.Router;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;

class RouterTest {
    private HashMap<String, String> initialLine = new HashMap<>();

    @Test
    @DisplayName("When a Router takes in a HEAD request routeRequest should call runHeadMethod")
    void headRequestHandler() {
        initialLine.put("httpMethod", "HEAD");
        initialLine.put("httpPath", "/test");
        initialLine.put("httpVersion", "HTTP/1.1");
        Router router = new Router(initialLine);
        Router spyRouter = Mockito.spy(router);

        spyRouter.routeRequest();

        Mockito.verify(spyRouter).runHeadRequest();
    }

    @Test
    @DisplayName("When a Router takes in a GET request routeRequest should call runGetMethod")
    void getRequestHandler() {
        initialLine.put("httpMethod", "GET");
        initialLine.put("httpPath", "/test");
        initialLine.put("httpVersion", "HTTP/1.1");
        Router router = new Router(initialLine);
        Router spyRouter = Mockito.spy(router);

        spyRouter.routeRequest();

        Mockito.verify(spyRouter).runGetRequest();
    }

    @Test
    @DisplayName("When a GET request is made to a HEAD request path, then methodNotFound() will be called")
    void routeRequest_NotAllowed() {
        String headPath = "head_request";
        initialLine.put("httpMethod", "HEAD");
        initialLine.put("httpPath", headPath);
        initialLine.put("httpVersion", "HTTP/1.1");
        Router router = new Router(initialLine);
        Router spyRouter = Mockito.spy(router);

        spyRouter.routeRequest();

        Mockito.verify(spyRouter).notAllowed(null);
    }

}
