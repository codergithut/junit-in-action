package tianjian.section05.mock;

import tianjian.section05.ConnectionFactory;

import java.io.InputStream;

/**
 * Created by tianjian on 2017/12/24.
 */
public class MockConnectionFactory implements ConnectionFactory{
    private InputStream inputStream;
    public void setData(InputStream stream) {
        this.inputStream = stream;
    }
    @Override
    public InputStream getData() throws Exception {
        return this.inputStream;
    }
}
