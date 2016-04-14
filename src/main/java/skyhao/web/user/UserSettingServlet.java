package skyhao.web.user;

import com.qiniu.util.Auth;
import skyhao.util.ConfigProperties;
import skyhao.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 昊 on 2016/4/12.
 */
@WebServlet("/user/setting.do")
public class UserSettingServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Auth auth = Auth.create(ConfigProperties.get("qiniu.ak"), ConfigProperties.get("qiniu.sk"));
        String token = auth.uploadToken(ConfigProperties.get("qiniu.bucketname"));
        req.setAttribute("token", token);

        forward(req, resp, "user/setting");
    }
}
