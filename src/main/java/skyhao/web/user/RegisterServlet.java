package skyhao.web.user;


import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import skyhao.exception.ServiceException;
import skyhao.service.UserService;
import skyhao.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/register.do")
public class RegisterServlet extends BaseServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        forward(req,resp,"user/register");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        Map<String,Object> result = Maps.newHashMap();
        UserService userService = new UserService();

        if(StringUtils.isNotEmpty(name) && StringUtils.isNotEmpty(password)
                && StringUtils.isNotEmpty(email)){

            try {
                userService.saveNewUser(name,password,email);
                result.put("state","success");
            }catch (ServiceException se){
                result.put("state","error");
                result.put("message",se.getMessage());
            }
        }else {
            result.put("state","error");
            result.put("message","参数错误");
        }
        resp.sendRedirect("/login.do");
        rendJson(resp,result);

    }
}
