package skyhao.util;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.SQLException;


public class DBHelper {

    public static void update(String sql, Object...params){
        QueryRunner qr = new QueryRunner(ConnectionManager.getDataSource());
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static <T> T query(String sql, ResultSetHandler<T> handler, Object...params) {

        QueryRunner qr = new QueryRunner(ConnectionManager.getDataSource());
        try {
            return qr.query(sql, handler, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
