package org.github..common.tool;

import org.junit.Test;

/**
 * @author
 * @date 2018/9/5
 * Description :
 */
public class BeanBuilder<T> {

    public static Builder builder(){
        return new Builder();
    }




    static class Builder<T>{

        public  Builder getBean(T t){
            return this;
        }
    }

}
