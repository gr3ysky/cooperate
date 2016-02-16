package demo.web.controllers;

import demo.infrastructure.mediation.Mediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @Autowired
    private Mediator _mediator;

    @RequestMapping(value = "/", method = RequestMethod.GET,produces = "text/html")
    public ModelAndView index(){
        ModelAndView model= new ModelAndView("home/index");
        model.addObject("a", "yiğit");
        _mediator.send(new TestCommand(5));
        TestResponse response = _mediator.request(new TestRequest());
        return model;
    }
}
