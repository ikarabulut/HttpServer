package com.ikarabulut.server.http;

import com.ikarabulut.requests.HeadRequestRunner;

import java.util.HashMap;

public class Router {
    private HashMap<String, String> initialLine;

    public Router(HashMap<String, String> initialLine) {
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
