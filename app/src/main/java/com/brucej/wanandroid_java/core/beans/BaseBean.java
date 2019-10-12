package com.brucej.wanandroid_java.core.beans;

public class BaseBean<T> {
    /**
     * data	{…}
     * errorCode	0
     * errorMsg	""
     */
    private T data;
    private int errorCode;
    private String errorMsg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "data=" + data +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
