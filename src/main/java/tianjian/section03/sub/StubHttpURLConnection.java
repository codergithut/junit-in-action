package tianjian.section03.sub;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by tianjian on 2017/12/21.
 */
public class StubHttpURLConnection extends HttpURLConnection{
    private boolean isInput = true;
    /**
     * Constructor for the HttpURLConnection.
     *
     * @param url the URL
     */
    public StubHttpURLConnection(URL url) {
        super(url);
    }

    @Override
    public void disconnect() {

    }

    @Override
    public boolean usingProxy() {
        return false;
    }

    @Override
    public void connect() throws IOException {

    }

    @Override
    public InputStream getInputStream() throws ProtocolException {
        if(!isInput) {
            throw new ProtocolException(
                    "Cannot read from URLConnection"
                    + "if doInput=false (call setDoInput(true))"
            );
        }
        ByteArrayInputStream bais = new ByteArrayInputStream(new String("It works").getBytes());
        return bais;
    }
}
