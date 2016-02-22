package cooperate.infrastructure.mediation;

public interface IMediator {
    <TRequest extends IRequest, TResponse extends IResponse> TResponse request(TRequest request) throws Exception;

    <TEvent extends IEvent> void publish(TEvent event);

    <TCommand extends ICommand> void send(TCommand command);
}