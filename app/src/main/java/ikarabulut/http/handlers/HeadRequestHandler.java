package ikarabulut.http.handlers;

import ikarabulut.http.parser.RequestParser;
import ikarabulut.http.response.HeadResponse;
import ikarabulut.http.response.Response;
import ikarabulut.http.response.StatusCode;

import java.util.*;

public class HeadRequestHandler implements RequestHandler {
    Map<String, String> initialLine;
    RequestParser parsedRequest;

    public HeadRequestHandler(RequestParser parsedRequest) {
        this.parsedRequest = parsedRequest;
    }

    @Override
    public Response processResponse() {
        initialLine = parsedRequest.parseInitialLine();
        String version = initialLine.get("httpVersion");
        StatusCode status = StatusCode.OK;
        return new HeadResponse(version, status);
    }

}
