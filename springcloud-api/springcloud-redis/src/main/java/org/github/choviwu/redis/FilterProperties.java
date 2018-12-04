package org.github..redis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author
 * @date 2018/8/24
 * Description :
 */
//@Configuration
@Component
@ConfigurationProperties(prefix = "org.githu.")
//@ConditionalOnProperty(prefix = "org.githu.",name = "filter")
public class FilterProperties {

    private List<String > filter;

    public List<String > getFilter() {
        return filter;
    }

    public void setFilter(List<String > filter) {
        this.filter = filter;
    }
}
