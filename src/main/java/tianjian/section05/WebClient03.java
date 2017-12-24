package tianjian.section05;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by tianjian on 2017/12/24.
 */
public class WebClient03 {
    public String getContent(ConnectionFactory connectionFactory) {
        StringBuffer content = new StringBuffer();
        InputStream is = null;
        String result = null;
        try{
            is = connectionFactory.getData();
            int count;
            while (-1 != (count = is.read())) {
                content.append(new String(Character.toChars(count)));
            }
            result = content.toString();
        } catch (Exception e) {
            return null;
        }
        if(is != null) {
            try {
                is.close();
            } catch (IOException e) {
                result = null;
            }
        }
        return result;
    }
}
