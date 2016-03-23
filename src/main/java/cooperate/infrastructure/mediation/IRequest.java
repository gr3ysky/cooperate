package cooperate.infrastructure.mediation;

public interface IRequest<TRequest extends IRequest, TResponse extends IResponse> {
    IHandleRequest<TRequest, TResponse> getHandler();
}
