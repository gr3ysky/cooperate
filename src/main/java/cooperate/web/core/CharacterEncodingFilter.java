package cooperate.web.core;

import javax.servlet.*;
import java.io.IOException;


public class CharacterEncodingFilter implements Filter {

    public void init(final FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            response.setCharacterEncoding("UTF-8");
        } finally {
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
    }
}