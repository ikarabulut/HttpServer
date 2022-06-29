package ikarabulut.http.response;

import java.util.Map;

public class MethodNotAllowedResponse implements Response {
    protected final String version;
    protected final String statusCode;
    protected final String statusNumber;
    protected final Map<String, String> headers;
    protected final String body = EMPTYBODY;

    public MethodNotAllowedResponse(String version, String statusCode, String statusNumber, Map<String,String> headers) {
        this.version = version;
        this.statusCode = statusCode;
        this.statusNumber = statusNumber;
        this.headers = headers;
    }

    @Override
    public String stringifyResponse() {
        String initialLine = version + SPACE + statusNumber + SPACE + statusCode + CRLF;
        String headers = stringifyHeaders();

        return initialLine + headers + body;
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
