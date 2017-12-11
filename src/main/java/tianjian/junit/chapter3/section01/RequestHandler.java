package tianjian.junit.chapter3.section01;

/**
 * Created by tianjian on 2017/12/11.
 * 消息处理方法
 */
public interface RequestHandler {
    Response process(Request request) throws Exception;
}
