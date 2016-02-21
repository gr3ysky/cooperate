package cooperate.infrastructure.mediation;

/**
 * Created by taner on 15.02.2016.
 */
public interface IHandleCommand<TCommand extends ICommand> {
    void Handle(TCommand command);

}
