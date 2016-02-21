package cooperate.web.controllers;

import cooperate.infrastructure.mediation.IHandleRequest;
import cooperate.infrastructure.mediation.IRequest;

public class TestRequest implements IRequest<TestRequest, TestResponse> {

    public IHandleRequest<TestRequest, TestResponse> getHandler() {
        return new TestRequestHandler();
    }
}
