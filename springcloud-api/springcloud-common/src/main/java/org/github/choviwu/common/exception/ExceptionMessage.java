package org.github..common.exception;

/**
 * @author
 * @date 2018/7/17
 * Description :
 */
public interface ExceptionMessage {

    Integer getCode();
    String getMsg();

    enum User implements ExceptionMessage{
        USER_IS_NULL("用户不存在", 10000),
        USER_ROLE_IS_NULL("授权失败，原因：角色为空", 10001),
        USER_PASSWORD_ERROR("密码错误", 10002),
        USER_NAME_ERROR("用户名错误", 10003),
        USER_NAME_IS_NOT_NULL("用户名不能为空", 10004),
        USER_PASSWORD_IS_NOT_NULL("密码不能为空", 10005),
        USER_TYPE_IS_NOT_NULL("用户类型不能为空", 10006),
        USER_ALEADY_IS_EXSIST("用户已存在", 10007),
        USER_QR_IS_NO_SCAN("NO_SCAN", 10008),
        USER_QR_IS_NO_CONFIRM("NO_CONFIRM", 10009),
        USER_QR_ALREADY_SCAN("ALREADY_SCAN", 10010),




        ;
        private String msg;
        private Integer code;

        User(String msg, Integer code) {
            this.msg = msg;
            this.code = code;
        }

        @Override
        public Integer getCode() {
            return this.code;
        }

        @Override
        public String getMsg() {
            return this.msg;
        }
    }

    enum Sys implements ExceptionMessage {
        SYS_ERROR("系统异常", 99999),
        SYS_INSERT_ERROR("插入异常", 99998),
        SYS_DELETE_ERROR("删除异常", 99997),
        SYS_UPDATE_ERROR("更新异常", 99996),
        SYS_NOT_FOUND_ERROR("系统未找到资源异常", 99997),
        SYS_PARAM_ERROR("参数异常", 99998),

        ;
        private String msg;
        private Integer code;

        Sys() {
        }

        Sys(String msg, Integer code) {
            this.msg = msg;
            this.code = code;
        }

        @Override
        public Integer getCode() {
            return this.code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        @Override
        public String getMsg() {
            return this.msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
    //订单
    enum Order implements ExceptionMessage {
        ORDER_IS_NULL("订单不能为空", 29999),
        SECKILL_ALREADY_END("秒杀已经结束", 29998),
        GOODS_NUM_IS_ENOUGH("库存不足", 29997),
        GOODS_NOT_FOUNT("未找到商品", 29996),
        ;
        private String msg;
        private Integer code;

        Order() {
        }

        Order(String msg, Integer code) {
            this.msg = msg;
            this.code = code;
        }

        @Override
        public Integer getCode() {
            return this.code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        @Override
        public String getMsg() {
            return this.msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    //Sign
    enum Sign implements ExceptionMessage {
        SIGN_NOT_FOUND("未发现签名", 77777),
        SIGN_ERROR("签名不匹配", 77776),
        TOEKN_ERROR("系统不能跨域", 77775),;
        private String msg;
        private Integer code;


        Sign(String msg, Integer code) {
            this.msg = msg;
            this.code = code;
        }

        @Override
        public Integer getCode() {
            return this.code;
        }

        @Override
        public String getMsg() {
            return this.msg;
        }
    }


    //Permission
    enum Permission implements ExceptionMessage {
        PERMISSION_ERROR("未授权", 66666),
        PERMISSION_NOT_ENOUGH("Sorry,您的权限不足！", 66665),
        PLEASE_LOGIN_FIRST("Sorry,请先登录！", 66664),


        ;
        private String msg;
        private Integer code;


        Permission(String msg, Integer code) {
            this.msg = msg;
            this.code = code;
        }

        @Override
        public Integer getCode() {
            return this.code;
        }

        @Override
        public String getMsg() {
            return this.msg;
        }
    }

    //Redis
    enum Redis implements ExceptionMessage {
        REDIS_TIME_OUT("缓存已过期，请重新登录", 55555),;
        private String msg;
        private Integer code;


        Redis(String msg, Integer code) {
            this.msg = msg;
            this.code = code;
        }

        @Override
        public Integer getCode() {
            return this.code;
        }

        @Override
        public String getMsg() {
            return this.msg;
        }
    }
}
