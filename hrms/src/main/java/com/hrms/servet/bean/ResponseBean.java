package com.hrms.servet.bean;

public class ResponseBean {
    private Boolean isSuccess;
    private String message;
    private Object data;
    private Object metaData;

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getMetaData() {
        return metaData;
    }

    public void setMetaData(Object metaData) {
        this.metaData = metaData;
    }

    @Override
    public String toString() {
        return "{" +
                "isSuccess=" + isSuccess +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", metaData=" + metaData +
                '}';
    }
}
