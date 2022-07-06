package ikarabulut.http.handlers;

import ikarabulut.http.response.MethodNotAllowedResponse;
import ikarabulut.http.response.Response;
import ikarabulut.http.response.StatusCode;

import java.util.List;
import java.util.Map;

public class MethodNotAllowedHandler {
    private List<String> allowedMethods;
    private Map<String, String> initialLine;

    public MethodNotAllowedHandler(List<String> allowedMethods, Map<String, String> initialLine) {
        this.allowedMethods = allowedMethods;
        this.initialLine = initialLine;
    }

    public Response processResponse() {
        String version = initialLine.get("httpVersion");
        StatusCode status = StatusCode.NOT_ALLOWED;

        Response response = new MethodNotAllowedResponse(version, status);

        return response;
    }

}
