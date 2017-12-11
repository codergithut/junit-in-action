package tianjian.junit.chapter3.section01.test;

import org.junit.Before;
import org.junit.Test;
import tianjian.junit.chapter3.section01.Request;
import tianjian.junit.chapter3.section01.RequestHandler;
import tianjian.junit.chapter3.section01.Response;
import tianjian.junit.chapter3.section01.impl.DefaultController;

import static junit.framework.TestCase.assertSame;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by tianjian on 2017/12/11.
 */
public class TestDefaultController {
    private DefaultController controller;
    private Request request;
    private RequestHandler handler;

    @Before
    public void instantiate() {
        controller = new DefaultController();
        request = new SampleRequest();
        handler = new SampleHandler();
    }

    @Test
    public void testMethod() {
        throw new RuntimeException("implement me");
    }

    @Test
    public void testAddHandler() {
        controller.addHandler(request, handler);
        RequestHandler handler1 = controller.getHandler(request);
        assertSame("Handler we set in controller should be the same handler we get", handler, handler1);
    }

    @Test
    public void testProcessRequest() {
        controller.addHandler(request, handler);
        Response response = controller.processRequest(request);
        assertNotNull("Must not return a null response", response);
        assertEquals("Response should be of type SampleResponse", SampleResponse.class, response.getClass());
    }

    private class SampleRequest implements Request {

        @Override
        public String getName() {
            return "Test";
        }
    }

    private class SampleHandler implements RequestHandler {

        @Override
        public Response process(Request request) throws Exception {
            return new SampleResponse();
        }
    }

    private class SampleResponse implements Response {
        private static final String NAME = "Test";
        public String getName() {
            return NAME;
        }

        public boolean equals(Object object) {
            boolean result = false;
            if(object instanceof SampleResponse) {
                result = ((SampleResponse)object).getName().equals(getName());
            }
            return result;
        }

        public int hashCode() {
            return NAME.hashCode();
        }

    }
}
