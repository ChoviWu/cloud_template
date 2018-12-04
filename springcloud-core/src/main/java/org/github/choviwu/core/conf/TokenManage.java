package org.github..core.conf;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.github..common.constant.RedisConstants;
import org.github..common.util.DateUtils;
import org.github..common.util.JsonUtils;
import org.github..common.bean.UserDetails;
import org.github..common.bean.UserPermission;
import org.github..redis.RedisRepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author
 * @date 2018/7/12
 * Description :
 */
@Component
public class TokenManage {

    private static String key = "jwqif3456ghdfsdfs123@Q!$!%@$";
    @Autowired
    RedisRepositoryUser redisRepository;

    /**
     * generator user login key
     *
     * @param userDetails
     * @return
     */
    public String generatKey(UserDetails userDetails) {
        String token = Jwts.builder()
                .setSubject(userDetails.getUserName())
                .signWith(SignatureAlgorithm.HS512, key)
                .setExpiration(DateUtils.addDays(new Date(), 7))
                .compact();
        String keys = redisRepository.get(RedisConstants.USER_TOKEN + userDetails.getUserLogin().getUserId());
        if(keys==null) {
            redisRepository.set(RedisConstants.USER_TOKEN + userDetails.getUserLogin().getUserId(), JsonUtils.toJson(userDetails));//, DateUtils.addDays(new Date(), 7).getTime());
            putPermission(userDetails.getUserPermissions());
        }
        return token;
    }

    /**
     * get user token
     *
     * @param token
     * @return
     */
    public String getUserByToken(String token) {
        token = RedisConstants.USER_TOKEN + token;
        return redisRepository.get(token);
    }

    /**
     * delete user information
     *
     * @param userName
     */
    public void deleteToken(String userName) {
        userName = RedisConstants.USER_TOKEN + userName;
        redisRepository.del(userName);
    }

    /**
     * set user's permission
     *
     * @param userPermissions
     */
    private void putPermission(List<UserPermission> userPermissions) {
        redisRepository.set(RedisConstants.USER_PERMISSION, JsonUtils.toJson(userPermissions));//, 24 * 60 * 60 * 10);
    }


}
