package com.cas.owner.service.webssh.constant;


import java.util.HashMap;
import java.util.Map;

/**
 * 基础枚举常量类
 * @ClassName BaseEnums
 * @Author luo jin jiang
 * @Date 2019/6/27 21:27
 * @Version 1.0
 */
public enum BaseEnums implements BaseEnum<String,String> {

    /**
     * 客户端返回信息定义
     */
    SUCCESS("0", "请求成功"),

    FAILURE("1", "请求失败"),

    OPERATION_SUCCESS("0", "操作成功"),

    OPERATION_FAILURE("1", "操作失败"),

    ERROR("500", "服务器发生错误，请检查服务器"),

    NOT_FOUND("404", "发出的请求针对的是不存在的记录"),

    FORBIDDEN("406", "没有权限访问"),

    METHOD_NOT_ALLOWED("405", "方法不被允许"),

    BAD_REQUEST("400", "错误请求"),

    RUNTIME_ERROR("1000","运行时错误"),

    NULL_POINTER_ERROR("1001","空指针错误"),

    CLASS_CAST_ERROR("1002","类型转换错误"),

    IO_ERROR("1003","IO错误"),

    NO_SUCH_METHOD("1004","未知方法错误"),

    INDEX_OUT_OF_BOUNDS_ERROR("1005","数组越界错误"),

    JSON_PARSE_ERROR("1006","JSON解析错误"),

    SQL_ERROR("10011", "sql执行错误"),

    VALIDATION_ERROR("10010", "数据验证错误"),

    REMOTE_ERROR("10020","调用外部接口异常"),

    SIGNATURE_EXCEPTION("100030","生成签名失败");

    private String code;

    private String desc;

    private static Map<String, String> allMap = new HashMap<>();

    BaseEnums(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    static {
        for(BaseEnums enums : BaseEnums.values()){
            allMap.put(enums.code, enums.desc);
        }
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String desc() {
        return desc;
    }

    public String desc(String code){
        return allMap.get(code);
    }
}
