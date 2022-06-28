package com.ikarabulut.requests;

import com.ikarabulut.response.GetResponse;
import com.ikarabulut.response.Response;
import com.ikarabulut.response.StatusCode;

import java.util.*;

public class GetRequestRunner {
    private List<String> acceptedPaths;
    private Map<String, String> initialLine;
    private Map<String, String> headers;
    private String body;

    public GetRequestRunner(Map<String, String> initialLine) {
        this.initialLine = initialLine;
        this.acceptedPaths = new ArrayList<>(Arrays.asList("/simple_get", "/simple_get_with_body"));
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
