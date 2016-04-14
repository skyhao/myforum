package skyhao.util;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

/**
 * Created by 昊 on 2016/4/7.
 */
public class ConnectionManager {

    private static BasicDataSource dataSource = new BasicDataSource();

    static {

        dataSource.setDriverClassName(ConfigProperties.get("jdbc.driver"));
        dataSource.setUrl(ConfigProperties.get("jdbc.url"));
        dataSource.setUsername(ConfigProperties.get("jdbc.username"));
        dataSource.setPassword(ConfigProperties.get("jdbc.password"));

        dataSource.setInitialSize(5);
        dataSource.setMaxIdle(20);
        dataSource.setMinIdle(5);
        dataSource.setMaxWaitMillis(5000);

    }

    public static DataSource getDataSource(){

        return dataSource;

    }



}
