package skyhao.web.captcha;

import skyhao.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/validate/captcha.do")
public class ValidateCaptchaServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String code = req.getParameter("code");
        String result;

        String sessionValue = (String) req.getSession().getAttribute("captcha");
        if(sessionValue.equals(code)){
            result = "true";
        }else {
            result = "false";
        }

        rendJson(resp, result);
    }
}
