package server.utils;

import server.request.Request;

import java.io.BufferedReader;
import java.io.IOException;

public interface RequestBuilderInterface {
    public static Request buildRequest(BufferedReader in) throws IOException {
        return null;
    }

    private static Integer getContentLength(String line) {
        return null;
    }

    private static String getContentType(String line) {
        return null;
    }
}
