package ikarabulut.handlers;

import ikarabulut.response.MethodNotAllowedResponse;
import ikarabulut.response.Response;
import ikarabulut.response.StatusCode;

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

    public Response processResponse() {
        String version = initialLine.get("httpVersion");
        String statusCode = StatusCode.NOT_ALLOWED.getStatusCode();
        String statusNumber = StatusCode.NOT_ALLOWED.getStatusNumber();
        Map<String, String> headers = generateAllowHeaders();

        Response response = new MethodNotAllowedResponse(version, statusCode, statusNumber, headers);

        return response;
    }

}
