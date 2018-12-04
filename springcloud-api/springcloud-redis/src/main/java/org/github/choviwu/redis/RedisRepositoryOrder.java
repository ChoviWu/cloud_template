package org.github..redis;

import org.github..redis.jedis.JedisClientSingle;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

/**
 * redis repository
 */
@Component
public class RedisRepositoryOrder extends JedisClientSingle{


    public RedisRepositoryOrder(JedisPool jedisPool) {
        super(jedisPool);
    }
}
