package tianjian.section05;

import java.io.InputStream;

/**
 * Created by tianjian on 2017/12/24.
 */
public interface ConnectionFactory {
    InputStream getData() throws Exception;
}
