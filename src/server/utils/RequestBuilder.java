package server.utils;

import server.http.Method;
import server.request.Request;
import server.request.RequestInterface;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.Locale;

public class RequestBuilder implements RequestBuilderInterface {
    static final String CONTENT_TYPE = "Content-Type: ";
    static final String CONTENT_LENGTH = "Content-Length: ";


    public static Request buildRequest(BufferedReader in) throws IOException {
        String line = in.readLine();
        Request request = new Request();

        if (line != null) {
            //String[] splitFirstLine = line.split(" ");
            String[] splitFirstLine = line.split(" ");
            Boolean hasParams = splitFirstLine[1].contains("?");

            //System.out.println(splitFirstLine[0]);

            request.setMethod(splitFirstLine[0]);
            request.setPathname(splitFirstLine[1]);

            //System.out.println(request.getMethod());


            while (!line.isEmpty()) {
                System.out.println(line);

                line = in.readLine();
                //System.out.println(line);

                if (line.startsWith(CONTENT_LENGTH)) {
                    request.setContentLength(getContentLength(line));
                }
                if (line.startsWith(CONTENT_TYPE)) {
                    request.setContentType(getContentType(line));
                }
                if(line.startsWith("Authorization")){
                    String[] auth = line.split(" ");
                    request.setAuth(auth[2]);
                    String[] user = request.getAuth().split("-");
                    request.setUsername(user[0]); //kienboec-mtcgToken ==> kienboec
                }
            }

            if (request.getMethod().equals("POST") || request.getMethod().equals("PUT")) {
                int asciChar;
                for (int i = 0; i < request.getContentLength(); i++) {
                    asciChar = in.read();
                    String body = request.getBody();
                    request.setBody(body + ((char) asciChar));
                }
            }
        }

        return request;
    }

    private static Integer getContentLength(String line) {
        return Integer.parseInt(line.substring(CONTENT_LENGTH.length()));
    }

    private static String getContentType(String line) {
        return line.substring(CONTENT_TYPE.length());
    }
}
