package com.nisarg.filter;


import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SimpleCORSFilter implements Filter {

  public SimpleCORSFilter() {
    log.info("SimpleCORSFilter init");
  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
      throws IOException, ServletException {

    val request = (HttpServletRequest) req;
    val response = (HttpServletResponse) res;

    response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
    response.setHeader("Access-Control-Allow-Credentials", "true");
    response.setHeader("Access-Control-Allow-Methods",
        "HEAD, POST, GET, OPTIONS, DELETE, TRACE, PUT, PATCH");
    response.setHeader("Access-Control-Max-Age", "3600");
    response.setHeader("Access-Control-Allow-Headers",
        "Content-Type, Accept, X-Requested-With, remember-me");

    chain.doFilter(req, res);
  }

  @Override
  public void init(FilterConfig filterConfig) {
    log.info("Initialised CORS Filter: {}", filterConfig.getFilterName());
  }

  @Override
  public void destroy() {
    log.info("Destroyed filter: {}", this.getClass().getCanonicalName());
  }

}