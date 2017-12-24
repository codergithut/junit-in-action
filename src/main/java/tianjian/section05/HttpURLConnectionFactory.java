package tianjian.section05;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by tianjian on 2017/12/24.
 */
public class HttpURLConnectionFactory implements ConnectionFactory {

    private URL url;

    public HttpURLConnectionFactory(URL url) {
        this.url = url;
    }

    @Override
    public InputStream getData() throws Exception {
        HttpURLConnection connection =  (HttpURLConnection) this.url.openConnection();
        return connection.getInputStream();
    }
}
