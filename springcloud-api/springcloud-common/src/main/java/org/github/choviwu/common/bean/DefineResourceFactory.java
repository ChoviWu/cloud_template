package org.github..common.bean;

import org.github..common.base.Message;
import org.github..common.exception.BusException;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author
 * @date 2018/7/10
 * Description :
 */
@Component
public class DefineResourceFactory implements ResourceFactory {

    Map<String, UserPermission> permissionMap = new ConcurrentHashMap<>();

    @Override
    public synchronized Object createResource(List<UserPermission> object) {
        for (UserPermission userPermission : object) {
            if (userPermission == null) {
                throw new BusException("permission bean is empty");
            }
            //repeat or discovery
            UserPermission current = permissionMap.get(userPermission.getPermissionName());
            if (current != null) {
                continue;
            }
            permissionMap.putIfAbsent(userPermission.getPermissionName(), userPermission);
        }
        return permissionMap;
    }

    @Override
    public synchronized List<UserPermission> getList() {
        Set<Map.Entry<String, UserPermission>> entrySet = permissionMap.entrySet();
        Iterator<Map.Entry<String, UserPermission>> iterable = entrySet.iterator();
        List<UserPermission> list = new LinkedList<>();
        while (iterable.hasNext()) {
            list.add(iterable.next().getValue());
        }
        return list;
    }

    @Override
    public synchronized Object removeAll() {
        permissionMap.clear();
        return Message.OK;
    }
}
