package com.cas.utils.patternUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: xianglong
 * @date: 2019/9/3 17:35
 * @version: V1.0
 * @review: xiang_long
 */
public class PatternUtil {

    /**
     * 字符匹配工具
     * @param matchStr
     * @param patternMenu
     * @return
     */
    public static String match(String matchStr, PatternMenu patternMenu){
        Pattern pattern = Pattern.compile(patternMenu.getPattern());
        Matcher matcher = pattern.matcher(matchStr);
        return matcher.find()?matcher.group():"";
    }

}
