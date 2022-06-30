package ikarabulut.http.handlers;

import ikarabulut.http.response.Response;

import java.util.Map;

public interface RequestHandler {
    Response processResponse();

//    Map<String, String> generateHeaders();
}
