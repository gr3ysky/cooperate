package demo.infrastructure.mediation;

/**
 * Created by taner on 15.02.2016.
 */
public interface IHandleEvent<TEvent extends IEvent> {
    void Handle(TEvent event);
}
