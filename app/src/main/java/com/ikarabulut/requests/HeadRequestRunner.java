package com.ikarabulut.requests;

import com.ikarabulut.response.HeadResponse;

import java.util.*;

public class HeadRequestRunner {
    List<String> acceptedPaths;
    String httpPath;
    String httpVersion;
    public HeadRequestRunner(Map<String, String> initialLine) {
        this.httpPath = initialLine.get("httpPath");
        this.httpVersion = initialLine.get("httpVersion");
        this.acceptedPaths = new ArrayList<>( Arrays.asList("/simple_get", "/head_request"));
    }

    public String processResponse(boolean isValidPath) {
        HeadResponse responseHandler = new HeadResponse(isValidPath, httpVersion);
        return responseHandler.stringifyHeadResponse();
    }

    public boolean isValidPath() {
        return acceptedPaths.contains(httpPath);
    }


}
