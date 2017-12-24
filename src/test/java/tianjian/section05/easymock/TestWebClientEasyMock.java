package tianjian.section05.easymock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tianjian.section05.ConnectionFactory;
import tianjian.section05.WebClient03;

import java.io.IOException;
import java.io.InputStream;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by tianjian on 2017/12/24.
 */
public class TestWebClientEasyMock {
    private ConnectionFactory factory;
    private InputStream stream;

    @Before
    public void setUp() {
        factory = createMock("factory", ConnectionFactory.class);
        stream = createMock("stream", InputStream.class);
    }

    @Test
    public void testGetContentOK() throws Exception{
        expect(factory.getData()).andReturn(stream);
        expect(stream.read()).andReturn(new Integer((byte) 'W'));
        expect(stream.read()).andReturn(new Integer((byte) 'o'));
        expect(stream.read()).andReturn(new Integer((byte) 'r'));
        expect(stream.read()).andReturn(new Integer((byte) 'k'));
        expect(stream.read()).andReturn(new Integer((byte) 's'));
        expect(stream.read()).andReturn(new Integer((byte) '!'));
        expect(stream.read()).andReturn(-1);
        stream.close();

        replay(factory);
        replay(stream);

        WebClient03 client = new WebClient03();
        String result = client.getContent(factory);
        assertEquals("Works!", result);
    }

    @Test
    public void testGetContentCannotCloseInputStream() throws Exception {
        expect(factory.getData()).andReturn(stream);
        expect(stream.read()).andReturn(-1);
        stream.close();
        expectLastCall().andThrow(new IOException("can not close"));

        replay(factory);
        replay(stream);

        WebClient03 client = new WebClient03();
        String result = client.getContent(factory);
        assertNull(result);
    }

    @After
    public void tearDown() {
        verify(factory);
        verify(stream);
    }
}
