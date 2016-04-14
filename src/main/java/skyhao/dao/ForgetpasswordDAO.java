package skyhao.dao;

import org.apache.commons.dbutils.handlers.BeanHandler;
import skyhao.entity.ForgetPassword;
import skyhao.util.DBHelper;

/**
 * Created by 昊 on 2016/4/11.
 */
public class ForgetpasswordDAO {

    public void save(ForgetPassword fp){
        String sql = "insert into t_forgetpassword(token, uid, createtime) values(?, ?, ?)";
        DBHelper.update(sql, fp.getToken(), fp.getUid(), fp.getCreatetime());
    }

    public ForgetPassword findByToken(String token){
        String sql = "select * from t_forgetpassword where token = ?";
        return DBHelper.query(sql, new BeanHandler<ForgetPassword>(ForgetPassword.class), token);
    }

    public void deleteByUid(Integer uid){
        String sql = "delete from t_forgetpassword where uid = ?";
        DBHelper.update(sql, uid);
    }

}
