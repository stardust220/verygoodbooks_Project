/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vgb.web;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Administrator
 */
@WebFilter(filterName = "PerformanceFilter", urlPatterns = {"*.jsp", "*.do"}, 
        initParams = {@WebInitParam(name = "prefix", value = "Performace:")},
        dispatcherTypes = {DispatcherType.REQUEST,DispatcherType.FORWARD})
public class PerformanceFilter implements Filter {
    private FilterConfig filterConfig;
    private String prefix = "效能監測:";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;

        String prefix = filterConfig.getInitParameter("prefix");
        if (prefix != null) {
            this.prefix = prefix;
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long begin = System.currentTimeMillis();

        //交棒
        chain.doFilter(request, response);

        long end = System.currentTimeMillis();
        StringBuffer msg = new StringBuffer(prefix);
        msg.append(((HttpServletRequest) request).getRequestURL());
        msg.append("執行了");
        msg.append(end - begin);
        msg.append(" ms.");

        this.filterConfig.getServletContext().log(msg.toString());

    }

    @Override
    public void destroy() {
    }
}
