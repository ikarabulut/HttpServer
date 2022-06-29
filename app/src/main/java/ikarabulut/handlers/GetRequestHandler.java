package ikarabulut.handlers;

import ikarabulut.response.GetResponse;
import ikarabulut.response.Response;
import ikarabulut.response.StatusCode;

import java.util.*;

public class GetRequestHandler {
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

    public Response processResponse() {
        String version = initialLine.get("httpVersion");
        StatusCode statusCode = StatusCode.OK;
        String statusNumber = statusCode.getStatusNumber();

        Response getResponse;
        if (initialLine.get("httpPath").equals("/simple_get_with_body")) {
            getResponse = new GetResponse(version, statusCode, statusNumber, headers, body);
        } else {
            getResponse = new GetResponse(version, statusCode, statusNumber, headers);
        }

        return getResponse;
    }

}
