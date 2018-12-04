package org.github..core.conf;

import org.github..common.bean.*;
import org.github..core.encrypt.CredentialsMatcher;
import org.github..core.encrypt.Encrypt;
import org.github..core.encrypt.MD5Matcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author
 * @date 2018/7/10
 * Description : resource Factory Config
 */
@Configuration
public class CreatorConfig {

    private final String aseKey = "HTTPSIFCERTORGCN";

    @Bean
    ResourceFactory resourceFactory() {
        return new DefineResourceFactory();
    }

    @Bean
    PermissionResourceCreater permissionBeanCreater() {
        return new PermissionResourceCreater();
    }

    @Bean
    UserPermission userPermission() {
        return new UserPermission();
    }


    @Bean
    UserDetails userDetails() {
        return new UserDetails();
    }

    @Bean
    UserLogin userLogin() {
        return new UserLogin();
    }

    @Bean
    Encrypt encrypt() {
        return new Encrypt(aseKey);
    }

    @Bean
    CredentialsMatcher md5Matcher() {
        return new MD5Matcher();
    }

}
