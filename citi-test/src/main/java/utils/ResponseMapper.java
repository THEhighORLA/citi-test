package utils;

public class ResponseMapper<T> {
    private String message;
    private int status;
    private String statusText;
    private T data;

    public ResponseMapper(String message, int status, String statusText, T data) {
        this.message = message;
        this.status = status;
        this.statusText = statusText;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
