package com.ikarabulut.requests;

import com.ikarabulut.response.HeadResponse;
import com.ikarabulut.response.Response;
import com.ikarabulut.response.StatusCode;

import java.util.*;

public class HeadRequestRunner {
    List<String> acceptedPaths;
    Map<String, String> initialLine;
    Map<String, String> headers;

    public HeadRequestRunner(Map<String, String> initialLine) {
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
