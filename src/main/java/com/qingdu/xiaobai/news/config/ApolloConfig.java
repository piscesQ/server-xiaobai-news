package com.qingdu.xiaobai.news.config;

//import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
//@EnableApolloConfig
public class ApolloConfig {

    private String defaultBgUrl;      // = "http://n.sinaimg.cn/sinacn20121/600/w1920h1080/20190319/657f-hukwxnv7949149.jpg";
    private String defaultMainTitle;  // = "XiaoBaiNews";
    private String defaultSubTitle;   // = "News";
    private String defaultWelcome;    // = "Welcome to XiaoBai News";

    public String getDefaultBgUrl() {
        return defaultBgUrl;
    }

//    @Value("${defaultBgUrl}")
    public void setDefaultBgUrl(String defaultBgUrl) {
        this.defaultBgUrl = defaultBgUrl;
    }

    public String getDefaultMainTitle() {
        return defaultMainTitle;
    }

//    @Value("${defaultMainTitle}")
    public void setDefaultMainTitle(String defaultMainTitle) {
        this.defaultMainTitle = defaultMainTitle;
    }

    public String getDefaultSubTitle() {
        return defaultSubTitle;
    }

//    @Value("${defaultSubTitle}")
    public void setDefaultSubTitle(String defaultSubTitle) {
        this.defaultSubTitle = defaultSubTitle;
    }

    public String getDefaultWelcome() {
        return defaultWelcome;
    }

//    @Value("${defaultWelcome:Welcome to XiaoBai News}")
    public void setDefaultWelcome(String defaultWelcome) {
        this.defaultWelcome = defaultWelcome;
    }
}