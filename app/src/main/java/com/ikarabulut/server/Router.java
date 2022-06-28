package com.ikarabulut.server;

import com.ikarabulut.requests.GetRequestRunner;
import com.ikarabulut.requests.HeadRequestRunner;
import com.ikarabulut.response.Response;

import java.util.Map;

public class Router {
    private Map<String, String> initialLine;

    public Router(Map<String, String> initialLine) {
        this.initialLine = initialLine;
    }

    public Response routeRequest() {
        String method = initialLine.get("httpMethod");
        switch (method) {
            case "HEAD":
                return runHeadRequest();
            case "GET":
                return runGetRequest();
        }
        return null;
    }

    public Response runHeadRequest() {
        HeadRequestRunner requestRunner = new HeadRequestRunner(initialLine);
        Response response = requestRunner.processResponse();

        return response;
    }

    public Response runGetRequest() {
        GetRequestRunner requestRunner = new GetRequestRunner(initialLine);
        Response response = requestRunner.processResponse();

        return response;
    }

}
