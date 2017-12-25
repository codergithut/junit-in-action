package tianjian.section06;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static junit.framework.TestCase.assertFalse;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertTrue;

/**
 * Created by tianjian on 2017/12/24.
 */
public class EasyMockSampleServletTest {
    private SampleServlet servlet;
    private HttpServletRequest mockHttpServletRequest;
    private HttpSession mockHttpSession;

    @Before
    public void setUp() {
        servlet = new SampleServlet();
        mockHttpServletRequest = createStrictMock(HttpServletRequest.class);
        mockHttpSession = createStrictMock(HttpSession.class);
    }

    @Test
    public void testIsAuthenticatedAuthenticated() {
        expect(mockHttpServletRequest.getSession(false))
                .andReturn(mockHttpSession);
        expect(mockHttpSession.getAttribute(eq("authenticated")))
                .andReturn("true");
        replay(mockHttpServletRequest);
        replay(mockHttpSession);

        assertTrue(servlet.isAuthenticated(mockHttpServletRequest));

    }

    @Test
    public void testIsAuthenticatedNotAuthenticated() {
        expect(mockHttpServletRequest.getSession(false))
                .andReturn(mockHttpSession);
        expect(mockHttpSession.getAttribute(eq("authenticated")))
                .andReturn("false");
        replay(mockHttpServletRequest);
        replay(mockHttpSession);

        assertFalse(servlet.isAuthenticated(mockHttpServletRequest));

    }

    @Test
    public void testIsAuthenticatedNoSession() {
        expect(mockHttpServletRequest.getSession(eq(false))).andReturn(null);
        replay(mockHttpServletRequest);
        replay(mockHttpSession);

        assertFalse(servlet.isAuthenticated(mockHttpServletRequest));
    }

    @After
    public void tearDown() {
        verify(mockHttpServletRequest);
        verify(mockHttpSession);
        //http://blog.csdn.net/u011904605/article/details/54590383
        //http://download.csdn.net/download/zmx729618/8989709
    }
}
