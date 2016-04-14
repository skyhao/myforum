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
 * Created by 昊 on 2016/4/13.
 */
@WebServlet("/user/changeavatar.do")
public class ChangeAvatarServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if(isAjaxRequest(req)){
            String key = req.getParameter("key");
            User user = getLoginUser(req);
            user.setAvatar(key);
            UserService userService = new UserService();
            userService.updateUser(user);

            Map<String, Object> result = Maps.newHashMap();
            result.put("state", "success");
            rendJson(resp, result);
        }
    }


}
