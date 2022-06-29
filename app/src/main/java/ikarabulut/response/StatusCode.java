package ikarabulut.response;

public enum StatusCode {
    OK("OK" , "200"),
    NOT_ALLOWED("NOT ALLOWED", "405");

    final String code;
    final String number;

    StatusCode(String code, String number) {
        this.code = code;
        this.number = number;
    }

    public String getStatusNumber() {
        return number;
    }

    public String getStatusCode() { return code; }

}
