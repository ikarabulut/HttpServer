package ikarabulut.http.response;

import java.util.Map;

public class GetResponse implements Response {
    private final String version;
    private final StatusCode status;
    private final Map<String, String> headers;
    private String body;

    public GetResponse(String version, StatusCode status, Map<String, String> headers) {
        this(version, status, headers, EMPTYBODY);
    }

    public GetResponse(String version, StatusCode status, Map<String, String> headers, String body) {
        this.version = version;
        this.status = status;
        this.headers = headers;
        this.body = body;
    }

    @Override
    public String stringifyResponse() {
        String statusCode = status.getStatusCode();
        String statusNumber = status.getStatusNumber();

        String initialLine = version + SPACE + statusNumber + SPACE + statusCode + CRLF;
        String headers = stringifyHeaders();

        return hasBody() ? initialLine + headers + body : initialLine + headers;
    }

    public boolean hasBody() {
        return !body.equals(EMPTYBODY);
    }

    private String stringifyHeaders() {
        StringBuilder headersAsAString = new StringBuilder();
        for (Map.Entry<String, String> set : headers.entrySet()) {
            headersAsAString.append(set.getKey()).append(": ").append(set.getValue()).append(CRLF);
        }
        headersAsAString.append(CRLF);
        return headersAsAString.toString();
    }

}
