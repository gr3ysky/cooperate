package demo.web.controllers;

import demo.infrastructure.mediation.IHandleRequest;
import demo.infrastructure.mediation.IRequest;

public class TestRequest implements IRequest<TestRequest, TestResponse> {

    public IHandleRequest<TestRequest, TestResponse> getHandler() {
        return new TestRequestHandler();
    }
}
