package org.chen.fish.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@PropertySource(value="classpath:app.properties")
@ConfigurationProperties(prefix = "my")
public class Myconfig{

    private static Logger logger = LoggerFactory.getLogger(Myconfig.class);

    private String os;

    private String file;

    @PostConstruct
    public void init(){
        logger.info("~~~~~~~~~~~`os:{},file:{}",os,file);
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
