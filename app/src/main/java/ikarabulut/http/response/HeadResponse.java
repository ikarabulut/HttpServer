package ikarabulut.http.response;

import java.util.Map;

public class HeadResponse implements Response {
    private final String version;
    private final StatusCode status;
    private final Map<String, String> headers;

    public HeadResponse(String version, StatusCode status, Map<String, String> headers) {
        this.version = version;
        this.status = status;
        this.headers = headers;
    }

    @Override
    public String stringifyResponse() {
        String statusNumber = status.getStatusNumber();
        String statusCode = status.getStatusCode();

        String initialLine = version + SPACE + statusNumber + SPACE + statusCode + CRLF;
        String headers = stringifyHeaders();

        return initialLine + headers;
    }

    @Override
    public boolean hasBody() {
        return false;
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
