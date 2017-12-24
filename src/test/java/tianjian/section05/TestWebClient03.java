package tianjian.section05;

import org.junit.Test;
import tianjian.section05.mock.MockConnectionFactory;
import tianjian.section05.mock.MockInputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by tianjian on 2017/12/24.
 */
public class TestWebClient03 {

    @Test
    public void testGetContentOK() throws IOException {
        MockConnectionFactory mockConnectionFactory = new MockConnectionFactory();
        MockInputStream mockInputStream = new MockInputStream();
        mockInputStream.setBuffer("It works");
        mockConnectionFactory.setData(mockInputStream);
        WebClient03 client = new WebClient03();
        String result = client.getContent(mockConnectionFactory);
        assertEquals("It works", result);
        mockInputStream.verify();
    }

}
