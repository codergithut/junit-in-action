package tianjian.section05;

import org.junit.Test;
import tianjian.section05.mock.MockHttpURLConnection;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

/**
 * Created by tianjian on 2017/12/24.
 */
public class TestWebClient02 {
    private class TesttableWebClient extends WebClient02 {
        private HttpURLConnection connection;
        public void setHttpURLConnection(HttpURLConnection connection){
            this.connection = connection;
        }

        @Override
        public HttpURLConnection createHttpURLConnection(URL url) {
            return this.connection;
        }
    }

    @Test
    public void testGetContentOK() throws MalformedURLException {
        MockHttpURLConnection mockConnection = new MockHttpURLConnection();
        mockConnection.setExpectedInputStream("It works".getBytes());
        TesttableWebClient client = new TesttableWebClient();
        String result = client.getContent(new URL("http:///localhost"));
        assertEquals("It works", result);

    }


}
