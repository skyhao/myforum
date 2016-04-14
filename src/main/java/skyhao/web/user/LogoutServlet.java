package skyhao.web.user;

import skyhao.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 昊 on 2016/4/9.
 */

@WebServlet("/logout.do")
public class LogoutServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getSession().invalidate();
        resp.sendRedirect("/login.do?state=1001");
    }
}
