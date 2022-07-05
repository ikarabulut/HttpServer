package ikarabulut.http.handlers;

import ikarabulut.http.parser.RequestParser;
import ikarabulut.http.response.GetResponse;
import ikarabulut.http.response.Response;
import ikarabulut.http.response.StatusCode;

import java.util.*;

public class GetRequestHandler implements RequestHandler {
    private Map<String, String> initialLine;
    private RequestParser parsedRequest;
    private String body;

    public GetRequestHandler(RequestParser parsedRequest) {
        this.parsedRequest = parsedRequest;
        this.body = "Hello world";
    }

    @Override
    public Response processResponse() {
        initialLine = parsedRequest.parseInitialLine();
        String version = initialLine.get("httpVersion");
        StatusCode status = StatusCode.OK;

        Response getResponse;
        if (initialLine.get("httpPath").equals("/simple_get_with_body")) {
            getResponse = new GetResponse(version, status, body);
        } else {
            getResponse = new GetResponse(version, status);
        }

        return getResponse;
    }

}
