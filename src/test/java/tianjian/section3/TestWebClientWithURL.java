package tianjian.section3;

import org.junit.BeforeClass;
import org.junit.Test;
import tianjian.section03.Webclient;
import tianjian.section03.sub.StubHttpURLConnection;

import java.io.IOException;
import java.net.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by tianjian on 2017/12/21.
 */
public class TestWebClientWithURL {
    @BeforeClass
    public static void setUp() {
        TestWebClientWithURL t = new TestWebClientWithURL();
        URL.setURLStreamHandlerFactory(t.new StubStreamHandlerFactory());
    }

    @Test
    public void testGetContentOK() throws MalformedURLException {
        Webclient client = new Webclient();
        String result = client.getContent(new URL(
                "http://localhost:8080/testGetContentOK"
        ));
        assertEquals("It works", result);
    }

    private class StubStreamHandlerFactory implements URLStreamHandlerFactory {
        @Override
        public URLStreamHandler createURLStreamHandler(String protocol) {
            return new StubHttpURLStreamHandler();
        }
    }

    private class StubHttpURLStreamHandler extends URLStreamHandler {

        @Override
        protected URLConnection openConnection(URL url) throws IOException {
            return new StubHttpURLConnection(url);
        }
    }

}
