package com.ikarabulut.server;

import com.ikarabulut.requests.GetRequestRunner;
import com.ikarabulut.requests.HeadRequestRunner;
import com.ikarabulut.requests.MethodNotAllowedHandler;
import com.ikarabulut.response.Response;

import java.util.*;


public class Router {
    private Map<String, String> initialLine;
    private Map<String, List<String>> paths;

    public Router(Map<String, String> initialLine) {
        this.initialLine = initialLine;
        generateRoutes();
    }

    public void generateRoutes() {
        this.paths = new HashMap<>() {{
            put("/head_request", Arrays.asList("HEAD", "OPTIONS"));
            put("/simple_get", Arrays.asList("GET", "HEAD"));
            put("/simple_get_with_body", Arrays.asList("GET", "HEAD"));
        }};
    }

    public Response routeRequest() {

        if (!pathIncludesMethod()) {
            return methodNotAllowed(pathsIncludedMethods());
        }

        switch (initialLine.get("httpMethod")) {
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

    public Response methodNotAllowed(List<String> acceptedMethods) {
        MethodNotAllowedHandler handler = new MethodNotAllowedHandler(acceptedMethods, initialLine);
        return handler.processResponse();
    }

    private boolean pathIncludesMethod() {
        String method = initialLine.get("httpMethod");
        String path = initialLine.get("httpPath");
        return paths.get(path).contains(method);
    }

    private List<String> pathsIncludedMethods() {
        String path = initialLine.get("httpPath");
        return paths.get(path);
    }
}
