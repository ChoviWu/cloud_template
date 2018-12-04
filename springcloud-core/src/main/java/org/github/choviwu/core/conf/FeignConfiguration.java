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

package org.github..core.conf;

import com.hmily.tcc.springcloud.feign.HmilyFeignHandler;
import com.hmily.tcc.springcloud.feign.HmilyRestTemplateInterceptor;
import feign.Feign;
import feign.InvocationHandlerFactory;
import feign.Request;
import feign.Retryer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * feign configuration for
 */
@Configuration
@ConditionalOnProperty(prefix = "hmily.tcc.tccMongoConfig",name = "username")
public class FeignConfiguration {

    //config trancational hmily templateInterce
    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder() {
        return Feign.builder()
                .requestInterceptor(new HmilyRestTemplateInterceptor())
                .invocationHandlerFactory(invocationHandlerFactory());
    }

    @Bean
    public InvocationHandlerFactory invocationHandlerFactory() {
        return (target, dispatch) -> {
            HmilyFeignHandler handler = new HmilyFeignHandler();
            //handler.setTarget(target);
            handler.setHandlers(dispatch);
            return handler;
        };
    }
    //请求操作配置  包含读/链接时间
    @Bean
    Request.Options feignOptions() {
        return new Request.Options(5000, 5000);
    }
    //feign重试
    @Bean
    Retryer feignRetryer() {
        return Retryer.NEVER_RETRY;
    }
}