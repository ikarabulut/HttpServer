package ikarabulut.http.response;


public interface Response {
    String CRLF = "\r\n";
    String SPACE = " ";
    String EMPTYBODY = "";

    String stringifyResponse();

    boolean hasBody();

}
