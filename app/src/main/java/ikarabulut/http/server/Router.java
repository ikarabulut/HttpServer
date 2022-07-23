package ikarabulut.http.server;

import ikarabulut.http.handlers.*;
import ikarabulut.http.parser.RequestParser;
import ikarabulut.http.response.Response;

import java.util.*;


public class Router {
    private Map<String, String> initialLine;
    private RequestParser rawRequest;
    private Map<String, List<String>> paths;

    public Router(RequestParser rawRequest) {
        this.rawRequest = rawRequest;
        generateRoutes();
    }

    public void generateRoutes() {
        this.paths = new HashMap<>() {{
            put("/head_request", Arrays.asList("HEAD", "OPTIONS"));
            put("/simple_get", Arrays.asList("GET", "HEAD"));
            put("/simple_get_with_body", Arrays.asList("GET", "HEAD"));
            put("/echo_body", Arrays.asList("POST", "HEAD"));
        }};
    }

    public Response routeRequest() {

        if (!resourceExists()) {
            return runPageNotFound();
        } else if(!pathIncludesMethod()) {
            return methodNotAllowed(pathsIncludedMethods());
        }

        switch (initialLine.get("httpMethod")) {
            case "HEAD":
                return runHeadRequest();
            case "GET":
                return runGetRequest();
            case "POST":
                return runPostRequest();
        }
        return null;
    }

    public Response runHeadRequest() {
        HeadRequestHandler requestRunner = new HeadRequestHandler(rawRequest);
        Response response = requestRunner.processResponse();

        return response;
    }

    public Response runGetRequest() {
        GetRequestHandler requestRunner = new GetRequestHandler(rawRequest);
        Response response = requestRunner.processResponse();

        return response;
    }

    public Response runPostRequest() {
        PostRequestHandler requestHandler = new PostRequestHandler(rawRequest);
        return requestHandler.processResponse();
    }

    public Response methodNotAllowed(List<String> acceptedMethods) {
        MethodNotAllowedHandler requestHandler = new MethodNotAllowedHandler(acceptedMethods, initialLine);
        return requestHandler.processResponse();
    }

    public Response runPageNotFound(){
        PageNotFoundHandler requestHandler = new PageNotFoundHandler(rawRequest);
        return requestHandler.processResponse();
    }

    private boolean pathIncludesMethod() {
        initialLine = rawRequest.parseInitialLine();
        String method = initialLine.get("httpMethod");
        String path = initialLine.get("httpPath");
        return paths.get(path).contains(method);
    }

    private List<String> pathsIncludedMethods() {
        initialLine = rawRequest.parseInitialLine();
        String path = initialLine.get("httpPath");
        return paths.get(path);
    }

    private boolean resourceExists() {
        initialLine = rawRequest.parseInitialLine();
        String resource = initialLine.get("httpPath");
        return paths.containsKey(resource);
    }

}
