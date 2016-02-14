package demo.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @RequestMapping(value = "/", method = RequestMethod.GET,produces = "text/html")
    public ModelAndView index(){
        ModelAndView model= new ModelAndView("home/index");
        model.addObject("a","b");
        return model;
    }
}
