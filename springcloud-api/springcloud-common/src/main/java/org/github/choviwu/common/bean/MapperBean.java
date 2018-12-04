package org.github..common.bean;

import java.util.List;

/**
 * @author
 * @date 2018/8/6
 * Description :
 */
public class MapperBean {

    private boolean notEmpty;
    private List<Class> mappers;
    private String identity;

    public boolean isNotEmpty() {
        return notEmpty;
    }

    public void setNotEmpty(boolean notEmpty) {
        this.notEmpty = notEmpty;
    }

    public List<Class> getMappers() {
        return mappers;
    }

    public void setMappers(List<Class> mappers) {
        this.mappers = mappers;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}

