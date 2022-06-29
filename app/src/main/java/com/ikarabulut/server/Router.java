package com.ikarabulut.server;

import com.ikarabulut.requests.GetRequestRunner;
import com.ikarabulut.requests.HeadRequestRunner;
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
            put("/head_request", new ArrayList<>(Arrays.asList("HEAD", "OPTIONS")));
            put("/simple_get", new ArrayList<>(Arrays.asList("GET", "HEAD")));
            put("/simple_get_with_body", new ArrayList<>(Arrays.asList("GET", "HEAD")));
        }};
    }

    public Response routeRequest() {
        String method = initialLine.get("httpMethod");
        String path = initialLine.get("httpPath");

        if (paths.get(path).contains(method)) {
            switch (method) {
                case "HEAD":
                    return runHeadRequest();
                case "GET":
                    return runGetRequest();
            }
        } else {
            List<String> allowedMethods = paths.get(path);
            notAllowed(paths.get(allowedMethods));
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

    public void notAllowed(List<String> acceptedMethods) {
    }

}
