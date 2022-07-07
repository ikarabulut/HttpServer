package ikarabulut.http.response;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PageNotFoundResponse implements Response {
    private String version;
    private StatusCode status;
    private Map<String, String> headers;

    public PageNotFoundResponse(String version, StatusCode status) {
        this.version = version;
        this.status = status;
    }

    public String stringifyResponse() {
        String statusCode = status.getStatusCode();
        String statusNumber = status.getStatusNumber();

        String initialLine = version + SPACE + statusNumber + SPACE + statusCode + CRLF;
        String headers = stringifyHeaders();

        return initialLine + headers;
    }

    public Map<String, String> generateHeaders() {
        headers = new HashMap<>() {{
            put("Date", new Date().toString());
            put("Content-Language", "en-US");
        }};
        return headers;
    }

    public boolean hasBody() { return false; }

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
