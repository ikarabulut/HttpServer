package ikarabulut.http.response;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HeadResponse implements Response {
    private final String version;
    private final StatusCode status;
    private Map<String, String> headers;

    public HeadResponse(String version, StatusCode status) {
        this.version = version;
        this.status = status;
    }

    @Override
    public Map<String, String> generateHeaders() {
        headers = new HashMap<>() {{
            put("Date", new Date().toString());
            put("Content-Language", "en-US");
        }};
        return headers;
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
        generateHeaders();

        StringBuilder headersAsAString = new StringBuilder();
        for (Map.Entry<String, String> set : headers.entrySet()) {
            headersAsAString.append(set.getKey()).append(": ").append(set.getValue()).append(CRLF);
        }
        headersAsAString.append(CRLF);
        return headersAsAString.toString();
    }

}
