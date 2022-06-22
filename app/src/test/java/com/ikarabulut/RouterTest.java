package com.ikarabulut;

import com.ikarabulut.server.Router;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

}
