package org.github..core.conf;

import com.github.pagehelper.autoconfigure.PageHelperProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author
 * @date 2018/8/29
 * Description :
 */
//@Configuration
public class PageHelperConfig {

    @Bean
    @Primary
    public PageHelperProperties pageHelper(){
        PageHeler pageHeler = new PageHeler();
        pageHeler.setAutoDialect("true");
        pageHeler.setReasonable("true");
        pageHeler.setSupportMethodsArguments("true");
        pageHeler.setParams("count=countSql");
        pageHeler.setReasonable("true");
        pageHeler.setOffsetAsPageNum("10");
        pageHeler.setHelperDialect("mysql");
        return pageHeler;
    }

    private class PageHeler extends PageHelperProperties{

    }

}
