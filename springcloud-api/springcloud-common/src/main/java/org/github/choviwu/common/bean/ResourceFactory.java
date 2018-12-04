package org.github..common.bean;

import java.util.List;

/**
 * @author
 * @date 2018/7/10
 * Description :  permission
 */
public interface ResourceFactory {

    Object createResource(List<UserPermission> object);

    /**
     * getList
     *
     * @return
     */
    List<UserPermission> getList();

    Object removeAll();
}
