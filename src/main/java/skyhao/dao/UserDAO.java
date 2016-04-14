package skyhao.dao;

import org.apache.commons.dbutils.handlers.BeanHandler;
import skyhao.entity.User;
import skyhao.util.DBHelper;


public class UserDAO {

    public void save(User user){
        String sql = "insert into t_user(name,password,email,avatar,createtime,state)"
                + "values(?,?,?,?,?,?)";

        DBHelper.update(sql,user.getName(),user.getPassword(),user.getEmail(),
                user.getAvatar(),user.getCreatetime(),user.getState());

    }

    public User queryByName(String name){
        String sql = "select * from t_user where name=?";
        return DBHelper.query(sql, new BeanHandler<User>(User.class),name);
    }

    public User queryByEmail(String email){
        String sql = "select * from t_user where email=?";
        return DBHelper.query(sql,new BeanHandler<User>(User.class),email);
    }

    public void update(User user){

        String sql = "update t_user set password = ?, avatar = ?, email = ?, loginip = ?, " +
                "logintime = ?, state = ? where id = ?";

        DBHelper.update(sql, user.getPassword(), user.getAvatar(), user.getEmail(), user.getLoginip(),
                user.getLogintime(), user.getState(), user.getId());
    }

    public User querById(Integer uid) {
        String sql = "select * from t_user where id = ?";
        return DBHelper.query(sql, new BeanHandler<User>(User.class), uid);
    }
}
