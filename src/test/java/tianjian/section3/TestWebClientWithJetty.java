package tianjian.section3;

import org.junit.*;
import org.mortbay.jetty.HttpHeaders;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.AbstractHandler;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.util.ByteArrayISO8859Writer;
import tianjian.section03.Webclient;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by tianjian on 2017/12/20.
 */
public class TestWebClientWithJetty {

    @BeforeClass
    public static void setUp() throws Exception {
        Server server = new Server(8080);
        TestWebClientWithJetty t = new TestWebClientWithJetty();
        Context contentOKContent = new Context(server, "/testGetContentOK");
        contentOKContent.setHandler(t.new TestGetContentOKHandler());

        Context contentNotFoundContext = new Context(server, "/testGetContentNotFound");
        contentNotFoundContext.setHandler(t.new TestGetContentNotFound());
        server.start();
    }

    @AfterClass
    public static void tearDown() {

    }

    @Test
    public void testGetContentOK() throws MalformedURLException {
        Webclient client = new Webclient();
        String result = client.getContent(new URL(
                "http://localhost:8080/testGetContentOK"
        ));
        assertEquals("It works", result);
    }

    @Test
    public void testGetContentNotFound() throws MalformedURLException {
        Webclient client = new Webclient();
        String result = client.getContent(new URL(
                "http://localhost:8080/testGetContentNotFound"
        ));
        assertNull(result);
    }

    private class TestGetContentOKHandler extends AbstractHandler {
        @Override
        public void handle(String target, HttpServletRequest request, HttpServletResponse response, int dispatch) throws IOException, ServletException {
            OutputStream out = response.getOutputStream();
            ByteArrayISO8859Writer writer = new ByteArrayISO8859Writer();
            writer.write("It works");
            writer.flush();
            response.setIntHeader(HttpHeaders.CONTENT_LENGTH, writer.size());
            writer.writeTo(out);
            out.flush();

        }
    }

    private class TestGetContentNotFound extends AbstractHandler{
        @Override
        public void handle(String target, HttpServletRequest request, HttpServletResponse response, int dispatch) throws IOException, ServletException {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
