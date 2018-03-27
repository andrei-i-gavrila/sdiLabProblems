package ro.ubb.labproblems.tcp;

public class Response {

    private boolean successful;
    private String data;

    public Response() {
    }

    public Response(boolean successful, String data) {
        this.successful = successful;
        this.data = data;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
