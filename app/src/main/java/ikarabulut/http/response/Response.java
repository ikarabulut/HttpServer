package ikarabulut.http.response;


import java.util.Map;

public interface Response {
    String CRLF = "\r\n";
    String SPACE = " ";
    String EMPTYBODY = "";

    String stringifyResponse();

    Map<String, String> generateHeaders();

    boolean hasBody();

}
