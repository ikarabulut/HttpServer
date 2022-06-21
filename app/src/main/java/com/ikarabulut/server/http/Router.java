package com.ikarabulut.server.http;

import com.ikarabulut.requests.HeadRequestRunner;

import java.util.Map;

public class Router {
    private Map<String, String> initialLine;

    public Router(Map<String, String> initialLine) {
        this.initialLine = initialLine;
    }

    public String routeRequest() {
        String method = initialLine.get("httpMethod");
        switch (method) {
            case "HEAD":
                return runHeadRequest();
        }
        return null;
    }

    public String runHeadRequest() {
        HeadRequestRunner requestRunner = new HeadRequestRunner(initialLine);
        boolean pathIsValid = requestRunner.isValidPath();
        String response = requestRunner.processResponse(pathIsValid);

        return response;
    }

}
