package org.github..core.filter;

import lombok.extern.slf4j.Slf4j;
import org.github..common.bean.UserDetails;
import org.github..core.annotation.Permission;
import org.github..redis.RedisRepositoryUser;

/**
 * @author
 * @date 2018/7/11
 * Description :
 */
@Slf4j
public class DefaultRoleFilter extends AbstractFilter {


    public DefaultRoleFilter(RedisRepositoryUser redisRepository) {
        super();
    }

    @Override
    public void matchPermission(Permission permission, UserDetails userDetails) {


    }
}
