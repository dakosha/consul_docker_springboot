package com.credorax.poc.service.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SomeFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SomeFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if ("GET".equals(req.getMethod())) {
            LOGGER.info(
                    "Logging Request  {} : {}", req.getMethod(),
                    req.getRequestURI());
            chain.doFilter(request, response);
            LOGGER.info(
                    "Logging Response :{}",
                    res.getContentType());
        } else {
            res.sendError(405);
        }
    }
}
