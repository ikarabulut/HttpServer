package com.ikarabulut;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;

class RouterTest {
    private HashMap<String, String> initialLine = new HashMap<>();

    @Test
    @DisplayName("When a Router takes in a HEAD request routeRequest should call runHeadMethod")
    void headRequestHandler() {
        String httpMethod = "HEAD";
        String httpPath = "/test";
        String httpVersion = "HTTP/1.1";
        initialLine.put("httpMethod", httpMethod);
        initialLine.put("httpPath", httpPath);
        initialLine.put("httpVersion", httpVersion);
        Router router = new Router(initialLine);
        Router spyRouter = Mockito.spy(router);

        spyRouter.routeRequest();

        Mockito.verify(spyRouter).runHeadRequest(httpPath, httpVersion);
    }

}
