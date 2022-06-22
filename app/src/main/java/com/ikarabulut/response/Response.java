package com.ikarabulut.response;

import java.util.Map;

public interface Response {
    String getVersion();
    StatusCode getStatusCode();
    String getStatusNumber();
    Map<String, String> getHeaders();
    String getBody();
}
