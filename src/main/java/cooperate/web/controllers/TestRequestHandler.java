package cooperate.web.controllers;

import cooperate.infrastructure.mediation.IHandleRequest;

/**
 * Created by taner on 16.02.2016.
 */
public class TestRequestHandler implements IHandleRequest<TestRequest, TestResponse> {

    public TestResponse Handle(TestRequest request) {
        return new TestResponse();
    }
}
