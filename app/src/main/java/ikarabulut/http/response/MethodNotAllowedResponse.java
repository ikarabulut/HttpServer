package ikarabulut.http.response;

import java.util.HashMap;
import java.util.Map;

public class MethodNotAllowedResponse implements Response {
    private final String version;
    private final StatusCode status;
    private Map<String, String> headers;
    private final String body = EMPTYBODY;

    public MethodNotAllowedResponse(String version, StatusCode status) {
        this.version = version;
        this.status = status;
    }

    @Override
    public Map<String, String> generateHeaders() {
        headers = new HashMap<>() {{
            put("Allow", "HEAD, OPTIONS");
        }};
        return headers;
    }

    @Override
    public String stringifyResponse() {
        String statusCode = status.getStatusCode();
        String statusNumber = status.getStatusNumber();

        String initialLine = version + SPACE + statusNumber + SPACE + statusCode + CRLF;
        String headers = stringifyHeaders();

        return initialLine + headers + body;
    }

    @Override
    public boolean hasBody() {
        return false;
    }

    private String stringifyHeaders() {
        generateHeaders();

        StringBuilder headersAsAString = new StringBuilder();
        for (Map.Entry<String, String> set : headers.entrySet()) {
            headersAsAString.append(set.getKey()).append(": ").append(set.getValue()).append(CRLF);
        }
        headersAsAString.append(CRLF);
        return headersAsAString.toString();
    }

}
