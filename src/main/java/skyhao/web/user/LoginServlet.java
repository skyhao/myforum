package skyhao.web.user;


import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import skyhao.entity.User;
import skyhao.service.UserService;
import skyhao.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/login.do")
public class LoginServlet extends BaseServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        forward(req,resp,"user/login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("username");
        String password = req.getParameter("password");

        Map<String, Object> result = Maps.newHashMap();

        if(StringUtils.isEmpty(name) || StringUtils.isEmpty(password)){
            result.put("state","error");
            result.put("message","参数错误");
        }else {

            UserService userService = new UserService();
            User user = userService.login(name, password, getRemoteIp(req));

            if(user == null){
                result.put("state", "error");
                result.put("message","用户名或密码错误");
            }else {
                HttpSession session = req.getSession();
                session.setAttribute("curr_user", user);
                result.put("state","success");
            }
        }

        rendJson(resp,result);

    }
}

