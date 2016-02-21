package cooperate.web.controllers;

import cooperate.infrastructure.mediation.IHandleCommand;
import org.springframework.stereotype.Service;

/**
 * Created by taner on 16.02.2016.
 */
@Service
public class TestCommandHandler implements IHandleCommand<TestCommand> {
    public void Handle(TestCommand command) {

    }
}
