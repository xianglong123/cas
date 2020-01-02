package com.cas.utils.patternUtils;

/**
 * @author: xianglong
 * @date: 2019/9/22 15:06
 * @version: V1.0
 * @review: xiang_long
 */
public enum PatternMenu {
    PATTERN_NUMBER("数字","^[0-9]*$"),
    PATTERN_ZERO("零和非零开头的数字","^(0|[1-9][0-9]*)$"),
    PATTERN_NO_ZERO_HEAD("非零开头的最多带两位小数的数字","^([1-9][0-9]*)+(.[0-9]{1,2})?$"),
    PATTERN_HAVE_TEO_DECIMAL("有两位小数的正实数","^[0-9]+(.[0-9]{2})?"),
    PARRERN_GET_MID_VALUE("获取两个字段中间的值","<>");

    private String code;
    private String pattern;


    PatternMenu(String code, String pattern){
        this.code = code;
        this.pattern = pattern;
    }

    public String getCode(){
        return this.code;
    }

    public String getPattern(){
        return this.pattern;
    }


}
