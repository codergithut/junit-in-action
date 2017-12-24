package tianjian.section05;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by tianjian on 2017/12/24.
 */
public class WebClient02 {
    public String getContent(URL url) {
        StringBuffer content = new StringBuffer();
        try{
            HttpURLConnection connection = createHttpURLConnection(url);
            connection.setDoInput(true);
            InputStream is = connection.getInputStream();
            int count;
            while (-1 != (count = is.read())) {
                content.append(new String(Character.toChars(count)));
            }
        } catch (IOException e) {
            return null;
        }
        return content.toString();
    }

    protected HttpURLConnection createHttpURLConnection(URL url) throws IOException {
        return (HttpURLConnection) url.openConnection();
    }
}
