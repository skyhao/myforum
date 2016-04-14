package skyhao.web.filter;


import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

/**
 * Created by 昊 on 2016/4/8.
 */
public abstract class AbstractFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
    @Override
    public void destroy() {}


}
