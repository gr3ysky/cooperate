package demo.web.core;

import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletException;

public class DemoApplicationInitializer implements WebApplicationInitializer {
    public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {
        servletContext.addFilter("CharacterEncodingFilter", new CharacterEncodingFilter());
    }

}
