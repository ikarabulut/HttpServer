package ikarabulut.http.handlers;

import ikarabulut.http.parser.RequestParser;
import ikarabulut.http.response.PostResponse;
import ikarabulut.http.response.Response;
import ikarabulut.http.response.StatusCode;

import java.util.Map;

public class PostRequestHandler implements RequestHandler {
    private RequestParser parsedRequest;

    public PostRequestHandler(RequestParser parsedRequest) {
        this.parsedRequest = parsedRequest;
    }

    public Response processResponse() {
        Map<String, String> initialLine = parsedRequest.parseInitialLine();
        String version = initialLine.get("httpVersion");
        StatusCode status = StatusCode.OK;
        String body = parsedRequest.parseBody();

        return new PostResponse(version, status, body);
    }


}
