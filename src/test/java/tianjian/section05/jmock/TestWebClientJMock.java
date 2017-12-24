package tianjian.section05.jmock;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;
import tianjian.section05.ConnectionFactory;
import tianjian.section05.WebClient03;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by tianjian on 2017/12/24.
 */
@RunWith(JMock.class)
public class TestWebClientJMock {
    private Mockery context = new JUnit4Mockery(){
        {
            //setImposteriser(ClassImposteriser.INSTANCE);
        }
    };

    @Test
    public void testGetContentOK() throws Exception {
        final ConnectionFactory factory = context.mock(ConnectionFactory.class);
        final InputStream mockStream = context.mock(InputStream.class);

        context.checking(new Expectations() {
            {
                oneOf(factory).getData();
                will(returnValue(mockStream));

                atLeast(1).of(mockStream).read();
                will( onConsecutiveCalls(
                        returnValue(new Integer((byte) 'W')),
                        returnValue(new Integer((byte) 'o')),
                        returnValue(new Integer((byte) 'r')),
                        returnValue(new Integer((byte) 'k')),
                        returnValue(new Integer((byte) 's')),
                        returnValue(new Integer((byte) '!')),
                        returnValue(-1)
                ));

                oneOf(mockStream).close();
            }
        });

        WebClient03 client = new WebClient03();
        String result = client.getContent(factory);
        assertEquals("Works!", result);

    }

    @Test
    public void testGetContentCannotCloseInputStream() throws Exception {
        final ConnectionFactory factory = context.mock(ConnectionFactory.class);
        final InputStream mockStream = context.mock(InputStream.class);
        context.checking( new Expectations() {
            {
                oneOf(factory).getData();
                will(returnValue(mockStream));

                oneOf(mockStream).read();
                will(returnValue(-1));
                oneOf(mockStream).close();
                will(throwException(new IOException("can not close")));
            }
        });

        WebClient03 client = new WebClient03();
        String result = client.getContent(factory);
        assertNull(result);
    }
}
