package tianjian.section03.sub;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.ResourceHandler;
import org.mortbay.jetty.servlet.Context;

/**
 * Created by tianjian on 2017/12/20.
 */
public class JettySample {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        Context root = new Context(server, "/testGetContentOK");
        root.setResourceBase("./testGetContentOK");
        root.setHandler(new ResourceHandler());
        server.start();
    }
}
