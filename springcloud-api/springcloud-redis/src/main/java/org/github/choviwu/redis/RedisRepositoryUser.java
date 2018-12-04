package org.github..redis;

import org.github..redis.jedis.JedisClientSingle;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

/**
 * redis repository
 */
@Component
public class RedisRepositoryUser extends JedisClientSingle{


    public RedisRepositoryUser(JedisPool jedisPool) {
        super(jedisPool);
    }
}
