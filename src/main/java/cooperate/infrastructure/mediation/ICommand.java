package cooperate.infrastructure.mediation;

public interface ICommand {
    <TCommand extends ICommand> IHandleCommand<TCommand> getHandler();
}
