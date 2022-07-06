package ikarabulut.http.handlers;

import ikarabulut.http.parser.RequestParser;
import ikarabulut.http.response.HeadResponse;
import ikarabulut.http.response.Response;
import ikarabulut.http.response.StatusCode;

import java.util.*;

public class HeadRequestHandler implements RequestHandler {
    private Map<String, String> initialLine;
    private RequestParser rawRequest;

    public HeadRequestHandler(RequestParser rawRequest) {
        this.rawRequest = rawRequest;
    }

    @Override
    public Response processResponse() {
        initialLine = rawRequest.parseInitialLine();
        String version = initialLine.get("httpVersion");
        StatusCode status = StatusCode.OK;
        return new HeadResponse(version, status);
    }

}
