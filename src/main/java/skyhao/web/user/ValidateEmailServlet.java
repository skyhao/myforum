package skyhao.web.user;

import skyhao.entity.User;
import skyhao.service.UserService;
import skyhao.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/validate/email.do")
public class ValidateEmailServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        email = new String(email.getBytes("ISO8859-1"),"UTF-8");

        UserService userService = new UserService();
        User user = userService.queryByEmail(email);

        String result;
        if(user == null){
            result = "true";
        }else {
            result = "false";
        }

        rendText(resp,result);
    }
}
