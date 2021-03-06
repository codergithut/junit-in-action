package tianjian.section02.impl;

import tianjian.section02.Request;
import tianjian.section02.Response;

/**
 * Created by tianjian on 2017/12/11.
 * 错误返回消息
 */
public class ErrorResponse implements Response {
    private Request originalRequest;
    private Exception originalException;
    public ErrorResponse(Request request, Exception exception) {
        this.originalException = exception;
        this.originalRequest = request;
    }

    public Request getOriginalRequest() {
        return originalRequest;
    }

    public Exception getOriginalException() {
        return originalException;
    }
}
