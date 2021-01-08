package com.cas.enums;

import java.util.function.Function;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 22:02 2020-03-01
 * @version: V1.0
 * @review: 枚举实现策略模式
 *
 */
public enum AuthStatusEnum {
    PROD_TYPE_O1(ProdTypeEnum.PROD_TYPE_01, OperateImpl.TYPE_01),
    PROD_TYPE_O2(ProdTypeEnum.PROD_TYPE_02, OperateImpl.TYPE_02),
    PROD_TYPE_O3(ProdTypeEnum.PROD_TYPE_03, OperateImpl.TYPE_03),
    PROD_TYPE_O4(ProdTypeEnum.PROD_TYPE_04, OperateImpl.TYPE_04),
    PROD_TYPE_O5(ProdTypeEnum.PROD_TYPE_05, OperateImpl.TYPE_05),
    PROD_TYPE_O6(ProdTypeEnum.PROD_TYPE_06, OperateImpl.TYPE_06),
    PROD_TYPE_O7(ProdTypeEnum.PROD_TYPE_07, OperateImpl.TYPE_07),
    PROD_TYPE_O8(ProdTypeEnum.PROD_TYPE_08, OperateImpl.TYPE_08),
    PROD_TYPE_O9(ProdTypeEnum.PROD_TYPE_09, OperateImpl.TYPE_09),
    PROD_TYPE_10(ProdTypeEnum.PROD_TYPE_10, OperateImpl.TYPE_10),
    PROD_TYPE_11(ProdTypeEnum.PROD_TYPE_11, OperateImpl.TYPE_11);

    private ProdTypeEnum code;
    private OperateImpl typeEnum;

    public ProdTypeEnum getCode() {
        return code;
    }

    public OperateImpl getTypeEnum() {
        return typeEnum;
    }

    AuthStatusEnum(ProdTypeEnum code, OperateImpl typeEnum) {
        this.code = code;
        this.typeEnum = typeEnum;
    }

    public static OperateImpl getProdTypeByCode(String code) {
        for (AuthStatusEnum statusEnum : AuthStatusEnum.values()) {
            if (code.equals(statusEnum.code.getCode())) {
                return statusEnum.getTypeEnum();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            System.out.println(AuthStatusEnum.getProdTypeByCode("01").operate("1"));
        } catch (NullPointerException e) {
            System.out.println("空指针");
        }
    }

    /**
     * 通过类型调用特定方法 转换器
     */
    public enum OperateImpl {
        TYPE_01(ProdType01Enum::getOutCodeByCode),
        TYPE_02(ProdType02Enum::getOutCodeByCode),
        TYPE_03(ProdType03Enum::getOutCodeByCode),
        TYPE_04(ProdType04Enum::getOutCodeByCode),
        TYPE_05(ProdType05Enum::getOutCodeByCode),
        TYPE_06(ProdType06Enum::getOutCodeByCode),
        TYPE_07(ProdType07Enum::getOutCodeByCode),
        TYPE_08(ProdType08Enum::getOutCodeByCode),
        TYPE_09(ProdType09Enum::getOutCodeByCode),
        TYPE_10(ProdType10Enum::getOutCodeByCode),
        TYPE_11(ProdType11Enum::getOutCodeByCode);

        private Function<String, String> operator;

        OperateImpl(Function<String, String>  operator) {
            this.operator = operator;
        }

        public String operate(String code) {
            return this.operator.apply(code);
        }
    }

    /**
     * 内定状态枚举
     */
    private enum StatusEnum {
        AUDIT_NO("00", "未审核"),
        AUDIT_ING("01", "审核中"),
        AUDIT_REJECTED("02", "审核驳回"),
        AUDIT_PASS("03", "审核通过"),
        AUDIT_END("04", "已结束"),
        AUDIT_FAILURE("05", "已失效"),
        AUDIT_RISK_NO("06", "待风控审核"),
        AUDIT_RISK_ING("07", "风控审核中");

        private String code;
        private String msg;

        public String getCode() {
            return code;
        }
        StatusEnum(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }


    /**
     * 836 审核状态类型
     */
    private enum ProdType01Enum {
        _01_STATUS_1("1", StatusEnum.AUDIT_NO),
        _01_STATUS_4("4", StatusEnum.AUDIT_NO),
        _01_STATUS_2("2", StatusEnum.AUDIT_ING),
        _01_STATUS_3("3", StatusEnum.AUDIT_ING),
        _01_STATUS_7("7", StatusEnum.AUDIT_REJECTED),
        _01_STATUS_10("10", StatusEnum.AUDIT_REJECTED),
        _01_STATUS_5("5", StatusEnum.AUDIT_PASS),
        _01_STATUS_9("9", StatusEnum.AUDIT_PASS),
        _01_STATUS_6("6", StatusEnum.AUDIT_RISK_NO),
        _01_STATUS_8("8", StatusEnum.AUDIT_RISK_ING),
        _01_STATUS_13("13", StatusEnum.AUDIT_END);


        private String code;
        private StatusEnum statusEnum;

        public String getCode() {
            return code;
        }

        public StatusEnum getStatusEnum() {
            return statusEnum;
        }

        ProdType01Enum(String code, StatusEnum statusEnum) {
            this.code = code;
            this.statusEnum = statusEnum;
        }

        private static String getOutCodeByCode(String code) {
            for (ProdType01Enum prodEnum : ProdType01Enum.values()) {
                if (prodEnum.getCode().equals(code)) {
                    return prodEnum.getStatusEnum().code;
                }
            }
            return null;
        }
    }

    /**
     *
     */
    private enum ProdType02Enum {
        _02_STATUS_00("00", StatusEnum.AUDIT_NO),
        _02_STATUS_01("01", StatusEnum.AUDIT_PASS),
        _02_STATUS_02("02", StatusEnum.AUDIT_REJECTED);

        private String code;
        private StatusEnum statusEnum;

        public String getCode() {
            return code;
        }

        public StatusEnum getStatusEnum() {
            return statusEnum;
        }

        ProdType02Enum(String code, StatusEnum statusEnum) {
            this.code = code;
            this.statusEnum = statusEnum;
        }

        private static String getOutCodeByCode(String code) {
            for (ProdType02Enum prodEnum : ProdType02Enum.values()) {
                if (prodEnum.getCode().equals(code)) {
                    return prodEnum.getStatusEnum().code;
                }
            }
            return null;
        }
    }

    /**
     *
     */
    private enum ProdType03Enum {
        _03_STATUS_00("00", StatusEnum.AUDIT_NO),
        _03_STATUS_01("01", StatusEnum.AUDIT_PASS),
        _03_STATUS_02("02", StatusEnum.AUDIT_REJECTED);

        private String code;
        private StatusEnum statusEnum;

        public String getCode() {
            return code;
        }

        public StatusEnum getStatusEnum() {
            return statusEnum;
        }

        ProdType03Enum(String code, StatusEnum statusEnum) {
            this.code = code;
            this.statusEnum = statusEnum;
        }

        private static String getOutCodeByCode(String code) {
            for (ProdType03Enum prodEnum : ProdType03Enum.values()) {
                if (prodEnum.getCode().equals(code)) {
                    return prodEnum.getStatusEnum().code;
                }
            }
            return null;
        }
    }

    /**
     *
     */
    private enum ProdType04Enum {
        _04_STATUS_01("01", StatusEnum.AUDIT_NO),
        _04_STATUS_02("02", StatusEnum.AUDIT_PASS),
        _04_STATUS_03("03", StatusEnum.AUDIT_REJECTED);

        private String code;
        private StatusEnum statusEnum;

        public String getCode() {
            return code;
        }

        public StatusEnum getStatusEnum() {
            return statusEnum;
        }

        ProdType04Enum(String code, StatusEnum statusEnum) {
            this.code = code;
            this.statusEnum = statusEnum;
        }

        private static String getOutCodeByCode(String code) {
            for (ProdType04Enum prodEnum : ProdType04Enum.values()) {
                if (prodEnum.getCode().equals(code)) {
                    return prodEnum.getStatusEnum().code;
                }
            }
            return null;
        }
    }

    /**
     *
     *
     */
    private enum ProdType05Enum {
        _05_STATUS_01("01", StatusEnum.AUDIT_NO),
        _05_STATUS_03("03", StatusEnum.AUDIT_PASS),
        _05_STATUS_04("04", StatusEnum.AUDIT_REJECTED);

        private String code;
        private StatusEnum statusEnum;

        public String getCode() {
            return code;
        }

        public StatusEnum getStatusEnum() {
            return statusEnum;
        }

        ProdType05Enum(String code, StatusEnum statusEnum) {
            this.code = code;
            this.statusEnum = statusEnum;
        }

        private static String getOutCodeByCode(String code) {
            for (ProdType05Enum prodEnum : ProdType05Enum.values()) {
                if (prodEnum.getCode().equals(code)) {
                    return prodEnum.getStatusEnum().code;
                }
            }
            return null;
        }
    }

    /**
     *
     * MPOS
     */
    private enum ProdType06Enum {
        _06_STATUS_1("1", StatusEnum.AUDIT_NO),
        _06_STATUS_2("2", StatusEnum.AUDIT_PASS),
        _06_STATUS_3("3", StatusEnum.AUDIT_REJECTED);

        private String code;
        private StatusEnum statusEnum;

        public String getCode() {
            return code;
        }

        public StatusEnum getStatusEnum() {
            return statusEnum;
        }

        ProdType06Enum(String code, StatusEnum statusEnum) {
            this.code = code;
            this.statusEnum = statusEnum;
        }

        private static String getOutCodeByCode(String code) {
            for (ProdType06Enum prodEnum : ProdType06Enum.values()) {
                if (prodEnum.getCode().equals(code)) {
                    return prodEnum.getStatusEnum().code;
                }
            }
            return null;
        }
    }

    /**
     *
     * MPOS
     */
    private enum ProdType07Enum {
        _07_STATUS_01("01", StatusEnum.AUDIT_NO),
        _07_STATUS_03("03", StatusEnum.AUDIT_PASS),
        _07_STATUS_04("04", StatusEnum.AUDIT_REJECTED);

        private String code;
        private StatusEnum statusEnum;

        public String getCode() {
            return code;
        }

        public StatusEnum getStatusEnum() {
            return statusEnum;
        }

        ProdType07Enum(String code, StatusEnum statusEnum) {
            this.code = code;
            this.statusEnum = statusEnum;
        }

        private static String getOutCodeByCode(String code) {
            for (ProdType07Enum prodEnum : ProdType07Enum.values()) {
                if (prodEnum.getCode().equals(code)) {
                    return prodEnum.getStatusEnum().code;
                }
            }
            return null;
        }
    }

    /**
     *
     * POS
     */
    private enum ProdType08Enum {
        _08_STATUS_1("1", StatusEnum.AUDIT_NO),
        _08_STATUS_2("2", StatusEnum.AUDIT_PASS),
        _08_STATUS_3("3", StatusEnum.AUDIT_REJECTED);

        private String code;
        private StatusEnum statusEnum;

        public String getCode() {
            return code;
        }

        public StatusEnum getStatusEnum() {
            return statusEnum;
        }

        ProdType08Enum(String code, StatusEnum statusEnum) {
            this.code = code;
            this.statusEnum = statusEnum;
        }

        private static String getOutCodeByCode(String code) {
            for (ProdType08Enum prodEnum : ProdType08Enum.values()) {
                if (prodEnum.getCode().equals(code)) {
                    return prodEnum.getStatusEnum().code;
                }
            }
            return null;
        }
    }


    /**
     *
     *
     */
    private enum ProdType09Enum {
        _09_STATUS_01("01", StatusEnum.AUDIT_NO),
        _09_STATUS_03("03", StatusEnum.AUDIT_PASS),
        _09_STATUS_02("02", StatusEnum.AUDIT_REJECTED),
        _09_STATUS_04("04", StatusEnum.AUDIT_FAILURE);

        private String code;
        private StatusEnum statusEnum;

        public String getCode() {
            return code;
        }

        public StatusEnum getStatusEnum() {
            return statusEnum;
        }

        ProdType09Enum(String code, StatusEnum statusEnum) {
            this.code = code;
            this.statusEnum = statusEnum;
        }

        private static String getOutCodeByCode(String code) {
            for (ProdType09Enum prodEnum : ProdType09Enum.values()) {
                if (prodEnum.getCode().equals(code)) {
                    return prodEnum.getStatusEnum().code;
                }
            }
            return null;
        }
    }


    /**
     *
     *
     */
    private enum ProdType10Enum {
        _10_STATUS_01("01", StatusEnum.AUDIT_NO),
        _10_STATUS_02("02", StatusEnum.AUDIT_ING),
        _10_STATUS_03("03", StatusEnum.AUDIT_PASS),
        _10_STATUS_04("04", StatusEnum.AUDIT_REJECTED),
        _10_STATUS_05("05", StatusEnum.AUDIT_END);

        private String code;
        private StatusEnum statusEnum;

        public String getCode() {
            return code;
        }

        public StatusEnum getStatusEnum() {
            return statusEnum;
        }

        ProdType10Enum(String code, StatusEnum statusEnum) {
            this.code = code;
            this.statusEnum = statusEnum;
        }

        private static String getOutCodeByCode(String code) {
            for (ProdType10Enum prodEnum : ProdType10Enum.values()) {
                if (prodEnum.getCode().equals(code)) {
                    return prodEnum.getStatusEnum().code;
                }
            }
            return null;
        }
    }


    /**
     *
     *
     */
    private enum ProdType11Enum {
        _11_STATUS_01("01", StatusEnum.AUDIT_NO),
        _11_STATUS_02("02", StatusEnum.AUDIT_ING),
        _11_STATUS_03("03", StatusEnum.AUDIT_PASS),
        _11_STATUS_04("04", StatusEnum.AUDIT_REJECTED),
        _11_STATUS_05("05", StatusEnum.AUDIT_END);

        private String code;
        private StatusEnum statusEnum;

        public String getCode() {
            return code;
        }

        public StatusEnum getStatusEnum() {
            return statusEnum;
        }

        ProdType11Enum(String code, StatusEnum statusEnum) {
            this.code = code;
            this.statusEnum = statusEnum;
        }

        private static String getOutCodeByCode(String code) {
            for (ProdType11Enum prodEnum : ProdType11Enum.values()) {
                if (prodEnum.getCode().equals(code)) {
                    return prodEnum.getStatusEnum().code;
                }
            }
            return null;
        }
    }







}
