package ikarabulut.http.handlers;

import ikarabulut.http.parser.RequestParser;
import ikarabulut.http.response.PageNotFoundResponse;
import ikarabulut.http.response.Response;
import ikarabulut.http.response.StatusCode;

import java.util.Map;

public class PageNotFoundHandler implements RequestHandler {
    private StatusCode code;
    private RequestParser rawRequest;

    public PageNotFoundHandler(RequestParser rawRequest) {
        this.rawRequest = rawRequest;
        this.code = StatusCode.NOT_FOUND;
    }
    public Response processResponse() {
        Map<String, String> initialLine = rawRequest.parseInitialLine();
        String version = initialLine.get("httpVersion");
        return new PageNotFoundResponse(version, this.code);
    }

}
