package tianjian.junit.chapter3.section01.test;

import org.junit.Before;
import org.junit.Test;
import tianjian.junit.chapter3.section01.Request;
import tianjian.junit.chapter3.section01.RequestHandler;
import tianjian.junit.chapter3.section01.Response;
import tianjian.junit.chapter3.section01.impl.DefaultController;
import tianjian.junit.chapter3.section01.impl.ErrorResponse;

import static junit.framework.TestCase.assertSame;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

/**
 * Created by tianjian on 2017/12/11.
 */
public class TestDefaultController {
    private DefaultController controller;
    private Request request;
    private RequestHandler handler;
    private RequestHandler sampleExceptionHandler;

    @Before
    public void instantiate() {
        controller = new DefaultController();
        request = new SampleRequest();
        handler = new SampleHandler();
        sampleExceptionHandler = new SampleExceptionHandler();
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

    @Test
    public void testProcessRequestAnswersErrorResponse() {
        SampleRequest sampleRequest = new SampleRequest("testError");
        sampleExceptionHandler = new SampleExceptionHandler();
        controller.addHandler(sampleRequest, sampleExceptionHandler);
        Response response = controller.processRequest(request);

        assertNotNull("Must not reutn a null response", response);
        assertEquals(ErrorResponse.class, response.getClass());
    }

    @Test(expected = RuntimeException.class)
    public void testGetHandlerNotDefined() {
        SampleRequest request = new SampleRequest("testNotDefined");
        controller.getHandler(request);
    }

    @Test(expected = RuntimeException.class)
    public void testAddRequestDuplicateName() {
        SampleRequest request = new SampleRequest();
        SampleHandler handler = new SampleHandler();
        controller.addHandler(request, handler);
    }

    @Test(timeout = 130)
    public void testProcessMultipleRequestsTimeOut() {
        Request request;
        Response response = new SampleResponse();
        RequestHandler handler = new SampleHandler();

        for(int i = 0; i < 99999; i++) {
            request = new SampleRequest(String.valueOf(i));
            controller.addHandler(request, handler);
            response = controller.processRequest(request);
            assertNotNull(response);
            assertNotSame(ErrorResponse.class, response.getClass());
        }
    }

    private class SampleRequest implements Request {

        private static final String DEFAULT_NAME = "Test";
        private String name;

        public SampleRequest(String name) {
            this.name = name;
        }

        public SampleRequest() {
            this(DEFAULT_NAME);
        }

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

    private class SampleExceptionHandler implements RequestHandler {

        @Override
        public Response process(Request request) throws Exception {
            throw new Exception("error processing request");
        }
    }
}
