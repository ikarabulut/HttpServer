package com.ikarabulut.requests;

import com.ikarabulut.response.HeadResponseHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class HeadRequestRunner {
    List<String> acceptedPaths = new ArrayList<>();
    String httpPath;
    String httpVersion;
    public HeadRequestRunner(HashMap<String, String> initialLine) {
        this.httpPath = initialLine.get("httpPath");
        this.httpVersion = initialLine.get("httpVersion");
        getValidPaths();
    }

    public String processResponse(boolean isValidPath) {
        HeadResponseHandler responseHandler = new HeadResponseHandler(isValidPath, httpVersion);
        return responseHandler.stringifyHeadResponse();
    }

    public boolean isValidPath() {
        return acceptedPaths.contains(httpPath);
    }

    public List<String> getAcceptedPaths() {
        return acceptedPaths;
    }

    private void getValidPaths() {
        File allowedPaths = new File("/Users/8thlight/learning-trails/java/HttpServer/app/src/pathfiles/HEADpaths.txt");
        try {
            Scanner fileReader = new Scanner(allowedPaths);
            while (fileReader.hasNextLine()) {
                String path = fileReader.nextLine();
                acceptedPaths.add(path);
            }
            fileReader.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

}
