package ikarabulut.http.handlers;

import ikarabulut.http.parser.RequestParser;
import ikarabulut.http.response.PageNotFoundResponse;
import ikarabulut.http.response.Response;
import ikarabulut.http.response.StatusCode;

import java.util.Map;

public class PageNotFoundHandler implements RequestHandler {
    private RequestParser rawRequest;

    public PageNotFoundHandler(RequestParser rawRequest) {
        this.rawRequest = rawRequest;
    }
    public Response processResponse() {
        Map<String, String> initialLine = rawRequest.parseInitialLine();
        StatusCode status = StatusCode.NOT_FOUND;
        String version = initialLine.get("httpVersion");
        return new PageNotFoundResponse(version, status);
    }

}
