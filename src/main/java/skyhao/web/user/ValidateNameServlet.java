package skyhao.web.user;

import skyhao.entity.User;
import skyhao.service.UserService;
import skyhao.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/validate/name.do")
public class ValidateNameServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        String name = req.getParameter("username");
        name = new String(name.getBytes("ISO8859-1"),"UTF-8");

        UserService userService = new UserService();
        User user = userService.queryByname(name);

        String result;
        if(user == null){
            if("forget".equals(action)){
                result = "false";
            }else {
                result = "true";
            }
        }else {
            if("forget".equals(action)){
                result = "true";
            }else {
                result = "false";
            }
        }

        rendText(resp,result);

    }
}
