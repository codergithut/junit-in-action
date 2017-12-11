package tianjian.junit.chapter3.section01.impl;

import tianjian.junit.chapter3.section01.Controller;
import tianjian.junit.chapter3.section01.Request;
import tianjian.junit.chapter3.section01.RequestHandler;
import tianjian.junit.chapter3.section01.Response;
import tianjian.junit.chapter3.section01.util.ErrorResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianjian on 2017/12/11.
 * 默认实现的根据请求的消息进行路由处理
 */
public class DefaultController implements Controller {

    private Map requestHandlers = new HashMap();

    public RequestHandler getHandler(Request request) {
        if(!this.requestHandlers.containsKey(request.getName())) {
            String message =  "cannot find handler for request name";
            throw new RuntimeException(message);
        }
        return (RequestHandler)this.requestHandlers.get(request.getName());
    }

    @Override
    public Response processRequest(Request request) {
        Response response;
        try {
            response = getHandler(request).process(request);
        } catch (Exception exception) {
            response = new ErrorResponse(request, exception);
        }
        return response;
    }

    @Override
    public void addHandler(Request request, RequestHandler requestHandler) {
        if(this.requestHandlers.containsKey(request.getName())) {
            throw new RuntimeException("A request handler has"
            + "already been registered for request name "
            + "[" + request.getName() + "]");
        } else {
            this.requestHandlers.put(request.getName(), requestHandler);
        }

    }
}
