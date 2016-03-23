package cooperate.infrastructure.mediation;

import org.springframework.stereotype.Component;

@Component
public class Mediator implements IMediator {


    public <TRequest extends IRequest, TResponse extends IResponse> TResponse request(TRequest request) throws Exception {
        IHandleRequest handler = request.getHandler();
        return (TResponse) handler.Handle(request);
    }

    public <TEvent extends IEvent> void publish(TEvent event) throws Exception {
        event.getHandler().Handle(event);
    }

    public <TCommand extends ICommand> void send(TCommand command) throws Exception {
        command.getHandler().Handle(command);
    }
}
