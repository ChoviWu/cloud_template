package org.github..redis;

import org.github..redis.jedis.JedisClient;
import org.github..redis.jedis.JedisClientSingle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author
 * @date 2018/7/6
 * Description :
 */
@Configuration
@EnableRedisRepositories
@ConditionalOnProperty(prefix = "spring.redis",name = "host")
public class RedisConfig {

    @Autowired
    RedisProperties redisProperties;

    @Autowired
    Environment environment;
//    @Bean
//    @Qualifier("redisRepositoryUser")
//    public RedisRepositoryUser redisRepositoryUser(JedisPool jedisPool){
//        return new RedisRepositoryUser(jedisPool);
//    }

    @Bean
    @Qualifier("redisRepositoryOrder")
    public RedisRepositoryOrder redisRepositoryOrder(JedisPool jedisPool){
        return new RedisRepositoryOrder(jedisPool);
    }

    @Bean
    public JedisClient jedisClient(
            JedisPool jedisPool) {
        return new JedisClientSingle(jedisPool);
    }
    @Bean
    public JedisPool jedisPool(
            JedisPoolConfig jedisPoolConfig,
            RedisProperties redisProperties){
        return new JedisPool(jedisPoolConfig,redisProperties.getHost(),redisProperties.getPort(),(int)redisProperties.getTimeout().toMillis(),redisProperties.getPassword());
    }
    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        return new JedisPoolConfig();
    }
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.redis")
    public RedisProperties redisProperties(){
        RedisProperties redis =  new RedisProperties();
        return redis;
    }


}
