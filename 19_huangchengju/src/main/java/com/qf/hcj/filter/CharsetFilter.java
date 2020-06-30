package com.qf.hcj.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharsetFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    //执行过滤;
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        chain.doFilter(req,resp);
    }

    public void destroy() {

    }
}
