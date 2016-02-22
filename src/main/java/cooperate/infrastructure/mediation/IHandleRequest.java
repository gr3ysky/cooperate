package cooperate.infrastructure.mediation;

public interface IHandleRequest<TRequest extends IRequest, TResponse extends IResponse> {
    TResponse Handle(TRequest request) throws Exception;
}
