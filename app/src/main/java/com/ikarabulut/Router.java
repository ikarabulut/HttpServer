package com.ikarabulut;

import com.ikarabulut.requests.HeadRequestRunner;

import java.util.HashMap;

public class Router {
    private HashMap<String, String> initialLine;
    private String httpMethod;
    private String httpPath;
    private String httpVersion;

    public Router(HashMap<String, String> initialLine) {
        this.initialLine = initialLine;
        httpMethod = initialLine.get("httpMethod");
        httpPath = initialLine.get("httpPath");
        httpVersion = initialLine.get("httpVersion");
    }

    public String routeRequest() {
        switch (httpMethod) {
            case "HEAD":
                return runHeadRequest(httpPath, httpVersion);
        }
        return null;
    }

    public String runHeadRequest(String httpPath, String httpVersion) {
        HeadRequestRunner requestRunner = new HeadRequestRunner(initialLine);
        boolean pathIsValid = requestRunner.isValidPath();
        String response = requestRunner.processResponse(pathIsValid);

        return response;
    }

}
