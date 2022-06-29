package com.ikarabulut.response;

import java.util.HashMap;
import java.util.Map;

public class NewStatusCode {
    public static Map<String, String> ok() {
        return new HashMap<>() {{
            put("name", "OK");
            put("number", "200");
        }};
    };

    public static Map<String, String> notAllowed() {
        return new HashMap<>() {{
            put("name", "NOT ALLOWED");
            put("number", "405");
        }};
    }
}
