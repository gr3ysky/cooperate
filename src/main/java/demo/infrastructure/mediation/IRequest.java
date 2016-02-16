package demo.infrastructure.mediation;

/**
 * Created by taner on 15.02.2016.
 */
public interface IRequest<TRequest extends IRequest, TResponse extends IResponse> {
    IHandleRequest<TRequest, TResponse> getHandler();
}
