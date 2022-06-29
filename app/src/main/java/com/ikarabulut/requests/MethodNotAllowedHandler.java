package com.ikarabulut.requests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MethodNotAllowedHandler {
    private List<String> allowedMethods;
    private Map<String, String> initialLine;

    public MethodNotAllowedHandler(List<String> allowedMethods, Map<String, String> initialLine) {
        this.allowedMethods = allowedMethods;
        this.initialLine = initialLine;
    }

    public Map<String, String> generateAllowHeaders() {
        Map<String, String> allowHeader = new HashMap<>() {{
            put("Allow", "HEAD, OPTIONS");
        }};
        return allowHeader;
    }

}
