package server.request;
import server.http.Method;

public class Request implements RequestInterface{
    private String method;
    private String pathname;
    private String contentType;
    private Integer contentLength;
    private String body = "";
    private String auth;
    private String username;

    public String getPathname() {
        return this.pathname;
    }

    public void setUsername(String user){
        this.username = user;
    }

    public String getUsername(){ return this.username; }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setAuth(String auth){
        this.auth = auth;
    }

    public void setPathname(String pathname) {
        this.pathname = pathname;
    }

    public void setContentLength(Integer contentLength) {
        this.contentLength = contentLength;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getAuth(){ return this.auth; }

    public String getMethod() {
        return this.method;
    }

    public int getContentLength() {
        return this.contentLength;
    }

    public String getContentType() {
        return this.contentType;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String s) {
        this.body = s;
    }
}
