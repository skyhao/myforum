package skyhao.web.user;

import skyhao.service.UserService;
import skyhao.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/forget/password.do")
public class ForgetPasswordServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        forward(req,resp,"user/forget");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("username");

        UserService userService = new UserService();

        userService.forgetPassword(name);

        resp.sendRedirect("/forget/password.do?state=2001");

    }
}
