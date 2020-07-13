//package com.cas.owner.utils;
//
///**
// * @author: xianglong[1391086179@qq.com]
// * @date: 17:26 2020-02-13
// * @version: V1.0
// * @review:
// */
//public class PinYinUtilTest {
//
//
//    public void testPinyinInitial()
//            throws BadHanyuPinyinOutputFormatCombination {
//        String name = "中文拼音";
//        char[] charArray = name.toCharArray();
//        StringBuilder pinyin = new StringBuilder();
//        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
//        // 设置大小写格式
//        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
//        // 设置声调格式：
//        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//        for (int i = 0; i < charArray.length; i++) {
//            // 匹配中文,非中文转换会转换成null
//            if (Character.toString(charArray[i]).matches("[\\u4E00-\\u9FA5]+")) {
//                String[] hanyuPinyinStringArray = PinyinHelper
//                        .toHanyuPinyinStringArray(charArray[i], defaultFormat);
//                if (hanyuPinyinStringArray != null) {
//                    pinyin.append(hanyuPinyinStringArray[0].charAt(0));
//                }
//            }
//        }
//        System.err.println(pinyin);
//
//    }
//}
