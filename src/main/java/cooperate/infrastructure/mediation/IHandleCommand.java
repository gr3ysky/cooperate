package cooperate.infrastructure.mediation;

public interface IHandleCommand<TCommand extends ICommand> {
    void Handle(TCommand command) throws Exception;
}
