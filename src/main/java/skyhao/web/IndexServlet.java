package skyhao.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 昊 on 2016/4/8.
 */
@WebServlet(name = "IndexServlet",value = "/index.do")
public class IndexServlet extends BaseServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        forward(request,response,"index");
    }
}
