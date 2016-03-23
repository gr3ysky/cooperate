package cooperate.infrastructure.mediation;

public interface IMediator {
    <TRequest extends IRequest, TResponse extends IResponse> TResponse request(TRequest request) throws Exception;

    <TEvent extends IEvent> void publish(TEvent event) throws Exception;

    <TCommand extends ICommand> void send(TCommand command) throws Exception;
}