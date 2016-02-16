package demo.infrastructure.mediation;

public interface ICommand {
    <TCommand extends ICommand> IHandleCommand<TCommand> getHandler();
}
