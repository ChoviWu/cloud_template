/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.github..redis.jedis;

import org.github..common.util.JsonUtils;
import org.github..redis.RedisRepositoryUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.nio.charset.Charset;
import java.util.Set;

/**
 * JedisClientSingle.
 */
public class JedisClientSingle implements JedisClient {

    private JedisPool jedisPool;


    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisRepositoryUser.class);

    /**
     * 默认编码
     */
    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");


    public JedisClientSingle(final JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    public String set(final String key, final String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.set(key, value);
        }
    }

    @Override
    public String set(final String key, final byte[] value) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.set(key.getBytes(), value);
        }
    }

    @Override
    public String set(byte[] key, byte[] value) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.set(key, value);
        }

    }



    @Override
    public Long del(final String... keys) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.del(keys);
        }
    }

    @Override
    public String get(final String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.get(key);
        }
    }

    @Override
    public <T> T get(String key, Class<T> clazz) {
        try (Jedis jedis = jedisPool.getResource()){
            String value = jedis.get(key);
            if(value!=null){
                return JsonUtils.json2Object(value,clazz);
            }
            return null;
        }
    }

    @Override
    public byte[] get(final byte[] key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.get(key);
        }
    }

    @Override
    public Set<byte[]> keys(final byte[] pattern) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.keys(pattern);
        }
    }

    @Override
    public Set<String> keys(final String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.keys(key);
        }
    }

    @Override
    public Long hset(final String key, final String item, final String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.hset(key, item, value);
        }
    }

    @Override
    public String hget(final String key, final String item) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.hget(key, item);
        }
    }

    @Override
    public Long hdel(final String key, final String item) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.hdel(key, item);
        }
    }

    @Override
    public Long incr(final String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.incr(key);
        }
    }

    @Override
    public Long decr(final String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.decr(key);
        }
    }

    @Override
    public Long expire(final String key, final int second) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.expire(key, second);
        }
    }

    @Override
    public Set<String> zrange(final String key, final long start, final long end) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.zrange(key, start, end);
        }
    }

    @Override
    public Long leftPush(String key, String... list) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.lpush(key,list);
        }
    }

    @Override
    public String  leftPop(String key) {
        try (Jedis jedis = jedisPool.getResource()){
            return jedis.lpop(key);
        }
    }

}
