package ro.ubb.labproblems.tcp;


public class Request {

    private String service;
    private String method;
    private Object[] arguments;

    public Request() {
    }

    public Request(String service, String method, Object... arguments) {
        this.service = service;
        this.method = method;
        this.arguments = arguments;
    }

    public Request(String service, String method) {
        this.service = service;
        this.method = method;
        this.arguments = null;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }
}
