package com.itdr.config;

import com.itdr.pojo.Product;
import sun.invoke.empty.Empty;

public class ConstCode {
    public static final int DEFAULT_SUCCRSS=200;
    public static final int DEFAULT_FAIL=100;
    public static final String UNLAWFULNESS_ALL ="非法参数";


    public enum UserEnum{
        //状态信息
        ERROR_PASSWORD(1,"密码错误"),
        EMPTY_USERNAME(2,"用户名不能为空"),
        EMPTY_PASSWORD(3,"密码不能为空"),
        EMPTY_QUESTION(4,"问题不能为空"),
        EMPTY_ANSWER(5,"答案不能为空"),
        INEXISTENCE_USER(6,"用户名不存在"),
        EXIST_USER(7,"用户名已存在"),
        EXIST_EMAIL(8,"邮箱已注册"),
        EMPTY_PARAM(9,"注册信息不能为空"),
        EMPTY_PARAM2(10,"更新信息不能为空"),
        SUCCESS_USER(11,"用户注册成功"),
        SUCCESS_MSG(12,"校验成功"),
        NO_LOGIN(13,"用户未登录"),
        NO_QUESTION(14,"该用户未设置找回密码问题"),
        ERROR_ANSWER(15,"问题答案错误"),
        LOSE_EFFICACY(16,"token已经失效"),
        UNLAWFULNESS_TOKEN(17,"非法的token"),
        DEFEACTED_PASSWORDNEW(18,"修改密码操作失败"),
        SUCCESS_PASSWORDNEW(19,"修改密码操作成功"),
        ERROR_PASSWORDOLD(20,"旧密码输入错误"),
        SUCCESS_USERMSG(21,"更新个人信息成功"),
        FORCE_EXIT(22,"用户未登录，无法获取当前用户信息"),
        LOGOUT(23,"退出成功"),
        FAIL_LOGIN(24,"登陆失败"),
        EMPTY_EMAIL(25,"邮箱不能为空"),
        FAIL_USER(26,"用户注册失败"),
        FAIL_UPDATEUSER(27,"用户更新失败"),
        EMPTY_EMAILORNAME(28,"用户名或邮箱不能为空"),
        FAIL_EMAILORNAME(29,"用户名或邮箱已存在"),
        EMPTY_LING(30,"令牌不能为空");



        private int code;
        private String desc;

        private UserEnum(int code,String desc){
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public enum ProductEnum{

        UNLAWFULNESS_PARAMETER(100,"非法参数"),
        NULL_PRODUCT(100,"商品不存在"),
        NULL_PARAMETER(100,"参数不能为空");

        private int code;
        private String desc;

        private ProductEnum(int code, String desc){
            this.code = code;
            this.desc = desc;
        }

        public int getCode(){
            return code;
        }

        public void setCode(int code){
            this.code = code;
        }

        public String getDesc(){
            return desc;
        }

        public void setDesc(String desc){
            this.desc = desc;
        }


    }

    public enum GwcEnum{

        NULL_PRODUCT(100,"商品不存在"),
        FAIL_PRODUCT(100,"添加商品失败"),
        GUO_PRODUCT(100,"商品超出库存"),
        FAIL_DELETE(100,"删除商品失败"),
        FAIL_ORDER(100,"创建订单失败"),
        FAIL_ORDERITER(100,"创建订单详情失败"),
        NULL_USER(100,"该用户购物车没有商品"),
        NO_PRODUCT(100,"该用户购物车没有被选中商品"),
        LEI_ONE(1,"类型"),
        LEI_TOW(2,"类型");



        private int code;
        private String desc;

        private GwcEnum(int code, String desc){
            this.code = code;
            this.desc = desc;
        }

        public int getCode(){
            return code;
        }

        public void setCode(int code){
            this.code = code;
        }

        public String getDesc(){
            return desc;
        }

        public void setDesc(String desc){
            this.desc = desc;
        }

    }

    public enum OrderEnum{

        NULL_ORDER(100,"订单不存在"),
        MATE_ORDER(100,"订单不匹配"),
        FAIL_ORDER(100,"下单失败"),
        FAIL_DELETE(100,"删除商品失败"),
        NULL_ALLORDER(100,"该用户没有订单"),
        LEI_ONE(1,"类型"),
        LEI_TOW(2,"类型");



        private int code;
        private String desc;

        private OrderEnum(int code, String desc){
            this.code = code;
            this.desc = desc;
        }

        public int getCode(){
            return code;
        }

        public void setCode(int code){
            this.code = code;
        }

        public String getDesc(){
            return desc;
        }

        public void setDesc(String desc){
            this.desc = desc;
        }

    }

    public enum ALiPayEnum{

        FAIL_DELETE(100,"验签失败"),
        FAIL_ALI(100,"FAILED");




        private int code;
        private String desc;

        private ALiPayEnum(int code, String desc){
            this.code = code;
            this.desc = desc;
        }

        public int getCode(){
            return code;
        }

        public void setCode(int code){
            this.code = code;
        }

        public String getDesc(){
            return desc;
        }

        public void setDesc(String desc){
            this.desc = desc;
        }

    }


    public enum ShippingEnum{

        NULL_SHIPPING(100,"地址不存在"),
        FAIL_ALI(100,"FAILED");




        private int code;
        private String desc;

        private ShippingEnum(int code, String desc){
            this.code = code;
            this.desc = desc;
        }

        public int getCode(){
            return code;
        }

        public void setCode(int code){
            this.code = code;
        }

        public String getDesc(){
            return desc;
        }

        public void setDesc(String desc){
            this.desc = desc;
        }

    }


    public enum OrderItemEnum{

        NULL_ORDERITEM(100,"该用户没有订单商品详情"),
        FAIL_ALI(100,"FAILED");




        private int code;
        private String desc;

        private OrderItemEnum(int code, String desc){
            this.code = code;
            this.desc = desc;
        }

        public int getCode(){
            return code;
        }

        public void setCode(int code){
            this.code = code;
        }

        public String getDesc(){
            return desc;
        }

        public void setDesc(String desc){
            this.desc = desc;
        }

    }
}

