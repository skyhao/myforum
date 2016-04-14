package skyhao.util;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by 昊 on 2016/4/7.
 */
public class ConfigProperties {

    private static Properties prop = new Properties();

    static {
        try {
            prop.load(ConfigProperties.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key){
        return prop.getProperty(key);
    }
}
