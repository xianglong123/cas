package com.cas.enums;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 11:05 2020-03-06
 * @version: V1.0
 * @review:
 */
public enum ProdTypeEnum {
    PROD_TYPE_01("01", "836商户录入、修改审核"),
    PROD_TYPE_02("02", "二代手刷实名认证审核"),
    PROD_TYPE_03("03", "大POS自动秒到审核"),
    PROD_TYPE_04("04", "业务员认证信息审核"),
    PROD_TYPE_05("05", "鑫联盟用户实名认证信息审核"),
    PROD_TYPE_06("06", "MPOS商户认证审核"),
    PROD_TYPE_07("07", "MPOS商户认证审核（新）"),
    PROD_TYPE_08("08", "POS商户app实名认证审核"),
    PROD_TYPE_09("09", "营销活动审核流程"),
    PROD_TYPE_10("10", "跨境商户录入、修改审核"),
    PROD_TYPE_11("11", "合作平台审核流程");

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    ProdTypeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public static ProdTypeEnum getProdTypeEnumByCode(String code) {
        for (ProdTypeEnum prodTypeEnum : ProdTypeEnum.values()) {
            if (code.equals(prodTypeEnum.getCode())) {
                return prodTypeEnum;
            }
        }
        return null;
    }


}
