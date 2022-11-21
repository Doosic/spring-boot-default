package com.setup.nuxtspringdefaultsetup.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

@Configuration
public class SpaRedirectFilterConfig {
  private static final Logger logger = LoggerFactory.getLogger(SpaRedirectFilterConfig.class);

  @Bean
  public FilterRegistrationBean spaRedirectFiler() {
    FilterRegistrationBean<OncePerRequestFilter> registration = new FilterRegistrationBean<>();
    registration.setFilter(createRedirectFilter());
    registration.addUrlPatterns("/*");
    registration.setName("frontendRedirectFiler");
    registration.setOrder(1);
    return registration;
  }

  private OncePerRequestFilter createRedirectFilter() {
    return new OncePerRequestFilter() {
      // Forwards all routes except '/index.html', '/200.html', '/favicon.ico', '/sw.js' '/api/', '/api/**'

      @Override
      protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        final String REGEX = "(?!/actuator|/_nuxt|/sv|/resources/app|/static|/index\\.html|/200\\.html|/favicon\\.ico|/sw\\.js).*$";
        Pattern pattern = Pattern.compile(REGEX);
        String uri = req.getRequestURI().replace(req.getContextPath(),"");
        if (pattern.matcher(uri).matches() && !uri.equals("/")) {
          // Delegate/Forward to `/` if `pattern` matches and it is not `/`
          // Required because of 'mode: history'usage in frontend routing, see README for further details
          logger.info("URL " + req.getRequestURI() + " entered directly into the Browser, redirecting...");
          RequestDispatcher rd = req.getRequestDispatcher("/");
          rd.forward(req, res);
        } else {
          chain.doFilter(req, res);
        }
      }
    };
  }
}
