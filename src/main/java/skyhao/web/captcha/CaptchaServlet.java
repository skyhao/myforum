package skyhao.web.captcha;

import org.patchca.color.ColorFactory;
import org.patchca.color.RandomColorFactory;
import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by 昊 on 2016/4/14.
 */
@WebServlet("/captcha.png")
public class CaptchaServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ConfigurableCaptchaService captchaService = new ConfigurableCaptchaService();
        captchaService.setColorFactory(new SingleColorFactory(new Color(26,69,226)));
        captchaService.setFilterFactory(new CurvesRippleFilterFactory(captchaService.getColorFactory()));

        RandomWordFactory wordFactory = new RandomWordFactory();
        wordFactory.setMaxLength(4);
        wordFactory.setMinLength(4);
        captchaService.setWordFactory(wordFactory);

        response.setContentType("image/png");

        OutputStream outputStream = response.getOutputStream();
        String captcha = EncoderHelper.getChallangeAndWriteImage(captchaService, "png", outputStream);
        HttpSession session = request.getSession();
        session.setAttribute("captcha", captcha);

        outputStream.flush();
        outputStream.close();
    }
}
