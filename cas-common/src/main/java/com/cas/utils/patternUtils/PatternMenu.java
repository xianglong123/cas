package com.cas.utils.patternUtils;

/**
 * @author: xianglong
 * @date: 2019/9/22 15:06
 * @version: V1.0
 * @review: 正则枚举
 * 除了.*?这种匹配方式,非贪婪式匹配还包括：
 * .+?　　　　匹配一次
 * .??　　　　不匹配
 * .{m,n}?   匹配m次
 */
public enum PatternMenu {
    PATTERN_CHINA("中文", "[\\u4e00-\\u9fa5]"),
    PATTERN_GREAP_CHINA("grep匹配中文", "[\\u4e00-\\u9fa5]"),
    PATTERN_NUMBER("数字","^[0-9]*$"),
    PATTERN_ZERO("零和非零开头的数字","^(0|[1-9][0-9]*)$"),
    PATTERN_NO_ZERO_HEAD("非零开头的最多带两位小数的数字","^([1-9][0-9]*)+(.[0-9]{1,2})?$"),
    PATTERN_HAVE_TEO_DECIMAL("有两位小数的正实数","^[0-9]+(.[0-9]{2})?"),
    PARRERN_GET_MID_VALUE("获取两个字段中间的值","<>"),
    PATTERN_GET_BODY("获取curl的body", "<body>.*</body>"),
    PATTERN_MENU("删除https和http的地址", "https://[0-9a-zA-Z/._#]*"),
    PATTERN_DATA("流感核心数据json", "<script id=\"getAreaStat\">.*?</script>");

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

    public static void main(String[] args) {
        String str = "iiiiihttps://m.weibo.cn/2656274875/4463902358582553";
//        String match = PatternUtil.match(str, PatternMenu.PATTERN_DATA);
//        System.out.println(match.replaceAll("https://[0-9a-zA-Z/._#]*",""));
        System.out.println(str.replaceAll("https://[0-9a-zA-Z/._#]*",""));
    }

}
