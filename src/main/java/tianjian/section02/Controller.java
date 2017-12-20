package tianjian.section02;

/**
 * Created by tianjian on 2017/12/11.
 * 实现请求绑定和请求路由处理
 */
public interface Controller {
    Response processRequest(Request request);
    void addHandler(Request request, RequestHandler requestHandler);
}
