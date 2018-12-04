package org.github..common.bean;

/**
 * @author
 * @date 2018/8/6
 * Description :
 */

public class MybatisBean {

    private boolean aggressiveLazyLoading;
    private boolean cacheEnabled;
    private boolean useGeneratedKeys;
    private boolean mapUnderScoreToCamelCase;
    private String mapperLocation;

    public boolean isAggressiveLazyLoading() {
        return aggressiveLazyLoading;
    }

    public void setAggressiveLazyLoading(boolean aggressiveLazyLoading) {
        this.aggressiveLazyLoading = aggressiveLazyLoading;
    }

    public boolean isCacheEnabled() {
        return cacheEnabled;
    }

    public void setCacheEnabled(boolean cacheEnabled) {
        this.cacheEnabled = cacheEnabled;
    }

    public boolean isUseGeneratedKeys() {
        return useGeneratedKeys;
    }

    public void setUseGeneratedKeys(boolean useGeneratedKeys) {
        this.useGeneratedKeys = useGeneratedKeys;
    }

    public boolean isMapUnderScoreToCamelCase() {
        return mapUnderScoreToCamelCase;
    }

    public void setMapUnderScoreToCamelCase(boolean mapUnderScoreToCamelCase) {
        this.mapUnderScoreToCamelCase = mapUnderScoreToCamelCase;
    }

    public String getMapperLocation() {
        return mapperLocation;
    }

    public void setMapperLocation(String mapperLocation) {
        this.mapperLocation = mapperLocation;
    }
}
