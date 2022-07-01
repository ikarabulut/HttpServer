package ikarabulut.http.handlers;

import ikarabulut.http.response.GetResponse;
import ikarabulut.http.response.Response;
import ikarabulut.http.response.StatusCode;

import java.util.*;

public class GetRequestHandler implements RequestHandler {
    private Map<String, String> initialLine;
    private Map<String, String> headers;
    private String body;

    public GetRequestHandler(Map<String, String> initialLine) {
        this.initialLine = initialLine;
        this.headers = new HashMap<>() {
            {
                put("Date", new Date().toString());
                put("Content-Language", "en-US");
            }
        };
        this.body = "Hello world";
    }

    @Override
    public Response processResponse() {
        String version = initialLine.get("httpVersion");
        StatusCode status = StatusCode.OK;
        String statusCode = status.getStatusCode();
        String statusNumber = status.getStatusNumber();

        Response getResponse;
        if (initialLine.get("httpPath").equals("/simple_get_with_body")) {
            getResponse = new GetResponse(version, status, headers, body);
        } else {
            getResponse = new GetResponse(version, status, headers);
        }

        return getResponse;
    }

}
