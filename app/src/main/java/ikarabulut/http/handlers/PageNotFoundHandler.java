package ikarabulut.http.handlers;

import ikarabulut.http.parser.RequestParser;
import ikarabulut.http.response.Response;
import ikarabulut.http.response.StatusCode;

public class PageNotFoundHandler implements RequestHandler {
    private StatusCode code;
    private RequestParser rawRequest;

    public PageNotFoundHandler(RequestParser rawRequest) {
        this.rawRequest = rawRequest;
        this.code = StatusCode.NOT_FOUND;
    }
    public Response processResponse() {
        return null; //TEMPORARY
    }

}
