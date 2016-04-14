package skyhao.web.user;

import com.google.common.collect.Maps;
import skyhao.entity.User;
import skyhao.service.UserService;
import skyhao.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by 昊 on 2016/4/14.
 */

@WebServlet("/user/changepassword.do")
public class ChangePasswordServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if(isAjaxRequest(req)){
            String password = req.getParameter("password");

            User user = getLoginUser(req);

            UserService userService = new UserService();
            userService.changePassword(user, password);

            Map<String, Object> result = Maps.newHashMap();
            result.put("state", "success");
            rendJson(resp, result);
        }
    }
}
