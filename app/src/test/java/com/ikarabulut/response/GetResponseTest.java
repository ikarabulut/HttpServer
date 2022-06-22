package com.ikarabulut.response;

import com.ikarabulut.response.GetResponse;
import com.ikarabulut.response.GetResponseBuilder;
import com.ikarabulut.response.StatusCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GetResponseTest {
    private String version = "HTTP/1.1";
    private StatusCode statusCode = StatusCode.OK;
    private String statusNumber = statusCode.getStatusNumber();
    private Map<String, String> headers = new HashMap<>() {
        {
            put("Date", new Date().toString());
            put("Content-Language", "en-US");
        }
    };

    @Test
    @DisplayName("A GetResponse must be initialized with a version, status code, status name, and headers.")
    void constructGetResponse() {
        GetResponse getResponse = new GetResponse(version, statusCode, statusNumber, headers);

        assertEquals(version, getResponse.getVersion());
        assertEquals(statusCode, getResponse.getStatusCode());
        assertEquals(statusNumber, getResponse.getStatusNumber());
        assertEquals(headers, getResponse.getHeaders());

    }

    @Test
    @DisplayName("A GetResponse that has a body of 'Hello World' should create a GetResponse body object of 'Hello World'")
    void constructGetResponse_WithBody() {
        String expectedBody = "Hello World";
        GetResponse getResponse = new GetResponseBuilder(version, statusCode, statusNumber, headers)
                .body(expectedBody)
                .build();

        assertEquals(expectedBody, getResponse.getBody());


    }

}
