package skyhao.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import skyhao.dao.ForgetpasswordDAO;
import skyhao.dao.UserDAO;
import skyhao.entity.ForgetPassword;
import skyhao.entity.User;
import skyhao.exception.ServiceException;
import skyhao.util.ConfigProperties;
import skyhao.util.EmailUtil;

import java.util.Formatter;
import java.util.UUID;


public class UserService {

    private UserDAO userDAO = new UserDAO();
    private ForgetpasswordDAO fpd = new ForgetpasswordDAO();

    public User queryByname(String name){
        return userDAO.queryByName(name);
    }

    public User queryByEmail(String email){
        return userDAO.queryByEmail(email);
    }

    public void saveNewUser(String name, String password, String email){
        if(queryByname(name) != null){
            throw new ServiceException("该账号已被注册");
        }
        if(queryByEmail(email) != null){
            throw new ServiceException("该邮箱已被注册");
        }

        User user = new User();
        user.setName(name);
        user.setPassword(DigestUtils.md5Hex(password+ ConfigProperties.get("user.password.salt")));
        user.setEmail(email);
        user.setCreatetime(DateTime.now().toString("YYYY-MM-dd HH:mm:ss"));
        user.setAvatar(ConfigProperties.get("user.default.avatar"));
        user.setState(User.USER_STATE_NORMAL);

        userDAO.save(user);
    }

    public User login(String name, String password, String ip){
        User user = queryByname(name);
        if(user != null && user.getPassword().equals(DigestUtils.md5Hex(password+
                ConfigProperties.get("user.password.salt")))){

            user.setLoginip(ip);
            user.setLogintime(DateTime.now().toString("yyyy-mm-dd hh:mm:ss"));

            userDAO.update(user);
            return user;
        }
      return null;
    }

    public void forgetPassword(String name) {
        User user = userDAO.queryByName(name);

        if(user != null){

            String uuid = UUID.randomUUID().toString();
            String email = user.getEmail();
            String title = "天朝论坛,找回密码";
            String url = "http://localhost/forget/password.do?token="+uuid;

            String msg = user.getName() + ":<br>\n" + "点击该<a href='"+url
                    +"'>链接</a>设置新的密码，该链接三十分钟内有效。<br>\n"+url;

            ForgetPassword fp = new ForgetPassword();

            fp.setCreatetime(DateTime.now().toString("yyyy-mm-dd hh:mm:ss"));
            fp.setToken(uuid);
            fp.setUid(user.getId());
            fpd.save(fp);

            EmailUtil.sendHtmlEmail(title, msg, email);
        }
    }

    public Integer validateForgetpasswordToken(String token){

        ForgetPassword fp = fpd.findByToken(token);

        if(fp == null){
            throw new ServiceException("该链接已失效");
        }else {
            String createTime = fp.getCreatetime();

            DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-mm-dd hh:mm:ss");
            DateTime dateTime = dateTimeFormatter.parseDateTime(createTime);
            dateTime = dateTime.plusMinutes(30);

            if(dateTime.isAfterNow()){
                return fp.getUid();
            }else {
                throw new ServiceException("该链接已失效");
            }
        }
    }

    /**
     * 用户找回密码：设置新密码
     * @param token
     * @param password
     */

    public void forgetSetNewPassword(String token, String password){

        ForgetPassword forgetPassword = fpd.findByToken(token);
        if(forgetPassword == null){
            throw new ServiceException("token无效");
        }else {
            Integer uid = forgetPassword.getUid();
            User user = userDAO.querById(uid);

            user.setPassword(DigestUtils.md5Hex(password + ConfigProperties.get("user.password.salt")));
            userDAO.update(user);

            fpd.deleteByUid(uid);
        }
    }

    public void updateUser(User user) {
        userDAO.update(user);
    }

    public void changePassword(User user, String password) {
        user.setPassword(DigestUtils.md5Hex(password+ConfigProperties.get("user.password.salt")));
        updateUser(user);
    }
}
