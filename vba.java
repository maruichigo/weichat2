https://blog.csdn.net/babylove_BaLe/article/details/73201115
https://www.cnblogs.com/sebastian-tyd/p/7895182.html

package jp.co.kintetsuls.common.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import jp.co.sharedsys.bb.ApplicationManager;

@ManagedBean(name="messageProperty", eager=true)
@ApplicationScoped
public class MessageProperty {

    public static final String IP_HOME = "home-directory";
    private static Properties prop = null;

    @PostConstruct
    public void init() {
        String homeDir = FacesContext.getCurrentInstance().getExternalContext().getInitParameter(IP_HOME);
        String configFile = homeDir + "/conf/message_JP.properties";

        prop = new Properties();
        try {
            prop.load(new FileInputStream(configFile));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    public String getProperty(String key){
        return prop.getProperty(key);
    }
}
