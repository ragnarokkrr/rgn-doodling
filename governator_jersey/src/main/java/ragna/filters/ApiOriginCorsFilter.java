package ragna.filters;

import javax.inject.Singleton;
import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class ApiOriginCorsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        servletResponse.addHeader("Access-Control-Origin", "*");
        servletResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        servletResponse.addHeader("Access-Control-Allow-Headers", "Content-Type");

        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}
