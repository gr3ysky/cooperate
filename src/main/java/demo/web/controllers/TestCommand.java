package demo.web.controllers;

import demo.infrastructure.mediation.ICommand;
import demo.infrastructure.mediation.IHandleCommand;

/**
 * Created by taner on 16.02.2016.
 */
public class TestCommand implements ICommand {
    private int a;

    public TestCommand(int a) {
        this.a = a;
    }

    public IHandleCommand<TestCommand> getHandler() {
        return new TestCommandHandler();
    }
}
