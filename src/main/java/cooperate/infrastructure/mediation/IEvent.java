package cooperate.infrastructure.mediation;

public interface IEvent {
    <TEvent extends IEvent> IHandleEvent<TEvent> getHandler();
}
