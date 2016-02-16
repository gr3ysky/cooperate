package demo.infrastructure.mediation;

public interface IHandleRequest<TRequest extends IRequest, TResponse extends IResponse> {
    TResponse Handle(TRequest request);
}
