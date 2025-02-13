package utils;

import java.util.List;

public class ResponseMapper<T> {
    private String serverMessage;
    private int code;
    private String status;
    private List<T> data;  // Ahora la lista es genérica

    public ResponseMapper(String serverMessage, int code, String status, List<T> data) {
        this.serverMessage = serverMessage;
        this.code = code;
        this.status = status;
        this.data = data;
    }

    public String getServerMessage() {
        return serverMessage;
    }

    public void setServerMessage(String serverMessage) {
        this.serverMessage = serverMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
