package com.ikarabulut.requests;

import com.ikarabulut.response.MethodNotAllowedResponse;
import com.ikarabulut.response.NewStatusCode;
import com.ikarabulut.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MethodNotAllowedHandler {
    private Map<String, String> statusCode;
    private List<String> allowedMethods;
    private Map<String, String> initialLine;

    public MethodNotAllowedHandler(List<String> allowedMethods, Map<String, String> initialLine) {
        this.allowedMethods = allowedMethods;
        this.initialLine = initialLine;
        this.statusCode = NewStatusCode.notAllowed();
    }

    public Map<String, String> generateAllowHeaders() {
        Map<String, String> allowHeader = new HashMap<>() {{
            put("Allow", "HEAD, OPTIONS");
        }};
        return allowHeader;
    }

    public Response processResponse() {
        String version = initialLine.get("httpVersion");
        String statusName = statusCode.get("code");
        String statusNumber = statusCode.get("number");
        Map<String, String> headers = generateAllowHeaders();

        Response response = new MethodNotAllowedResponse(version, statusName, statusNumber, headers);

        return response;
    }

}
