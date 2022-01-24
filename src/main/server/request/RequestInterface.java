package server.request;

public interface RequestInterface {

    //pathname
    public String getPathname();
    public void setPathname(String pathname);

    //username
    public void setUsername(String user);
    public String getUsername();

    //GET and POST
    public void setMethod(String method);
    public String getMethod();

    //Authorization
    public void setAuth(String auth);
    public String getAuth();

    //Body
    public String getBody();
    public void setBody(String s);

    //ContentLength
    public int getContentLength();
    public void setContentLength(Integer contentLength);


    //ContentType
    public String getContentType();
    public void setContentType(String contentType);

}
