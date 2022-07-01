package ikarabulut.http.handlers;

import ikarabulut.http.response.HeadResponse;
import ikarabulut.http.response.Response;
import ikarabulut.http.response.StatusCode;

import java.util.*;

public class HeadRequestHandler implements RequestHandler {
    Map<String, String> initialLine;
    Map<String, String> headers;

    public HeadRequestHandler(Map<String, String> initialLine) {
        this.initialLine = initialLine;
        this.headers = new HashMap<>() {
            {
                put("Date", new Date().toString());
                put("Content-Language", "en-US");
            }
        };
    }

    @Override
    public Response processResponse() {
        String version = initialLine.get("httpVersion");
        StatusCode status = StatusCode.OK;
        return new HeadResponse(version, status, headers);
    }


}
