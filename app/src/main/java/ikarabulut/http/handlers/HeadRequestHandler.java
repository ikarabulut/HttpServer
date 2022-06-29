package ikarabulut.http.handlers;

import ikarabulut.http.response.HeadResponse;
import ikarabulut.http.response.Response;
import ikarabulut.http.response.StatusCode;

import java.util.*;

public class HeadRequestHandler {
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

    public Response processResponse() {
        String version = initialLine.get("httpVersion");
        StatusCode statusCode = StatusCode.OK;
        String statusNumber = statusCode.getStatusNumber();
        return new HeadResponse(version, statusCode, statusNumber, headers);
    }


}
