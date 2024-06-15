package com.bty.blog.filter;

import com.bty.blog.wrapper.MultiReadRequestWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author bty
 * @date 2022/12/15
 * @since 1.8
 **/
public class MultiReadFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (request instanceof HttpServletRequest) {
            if (StringUtils.startsWithIgnoreCase(request.getContentType(), MediaType.APPLICATION_JSON_VALUE)) {
                ServletRequest wrapper = new MultiReadRequestWrapper((HttpServletRequest) request, response);
                chain.doFilter(wrapper, response);
                return;
            }
            chain.doFilter(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
