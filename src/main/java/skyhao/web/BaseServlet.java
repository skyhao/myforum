package skyhao.web;

import com.google.gson.Gson;
import skyhao.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class BaseServlet extends HttpServlet {

    public void forward(HttpServletRequest req, HttpServletResponse resp, String viewName)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/"+viewName+".jsp").forward(req,resp);
    }

    public void rendJson(HttpServletResponse resp, Object result) throws IOException {
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter pw = resp.getWriter();
        pw.print(new Gson().toJson(result));
        pw.flush();
        pw.close();
    }

    public void rendText(HttpServletResponse resp, String result) throws IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        PrintWriter pw = resp.getWriter();
        pw.print(result);
        pw.flush();
        pw.close();
    }

    public String getRemoteIp(HttpServletRequest request){
        String ip = request.getRemoteAddr();

        if("0:0:0:0:0:0:0:1".equals(ip)){
            ip = "127.0.0.1";
        }

        return ip;
    }

    public boolean isAjaxRequest(HttpServletRequest request){
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }

    public User getLoginUser(HttpServletRequest request){
        return (User) request.getSession().getAttribute("curr_user");
    }


}
