package com.cas.owner.service;

import com.alibaba.fastjson.JSONObject;
import com.cas.domain.grippe.GrippeProv;
import com.cas.owner.utils.patternUtils.PatternMenu;
import com.cas.owner.utils.patternUtils.PatternUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 15:17 2020-01-23
 * @version: V1.0
 * @review:
 */
public class WenyiServiceTest {

    private static final String[] cmds = {"curl", "https://3g.dxy.cn/newh5/view/pneumonia"};

    public static void main(String[] args) throws Exception {
        ProcessBuilder process = new ProcessBuilder(cmds);
        Process p;
        try {
            p = process.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
            }
            String result = builder.toString()
                    .replace("<link rel=\"stylesheet\" typeHandler=\"text/css\" href=\"//assets.dxycdn.com/gitrepo/bbs-mobile/dist/p__Pneumonia.async.c192f2a2.css\">", "")
                    .replaceAll("<link.*", "")
                    .replaceAll("https:[0-9a-zA-Z/._\\-#]*", "")
                    .replaceAll("http:[0-9a-zA-Z/._\\-#]*", "")
                    .replaceAll("<img.*?>", "")
                    .replaceAll("<meta.*?>", "")
                    .replaceAll("<link.*?>", "")
                    .replace("<head>", "")
                    .replace("<br>", "")
                    .replace("<img class=\"mapImg___3LuBG\" src=\"\"><div class=\"descBox___3dfIo\">", "")
                    .replace("&", "");
            //正则获取流感数据
            String match = PatternUtil.match(result, PatternMenu.PATTERN_DATA);
            System.out.println(match);
            String data = match
                    .replace("<script id=\"getAreaStat\">try { window.getAreaStat = ", "")
                    .replace("}catch(e){}</script>", "");
            System.out.println(data);
            List<GrippeProv> list = JSONObject.parseArray(data, GrippeProv.class);
            //数据流感-->数据获取成功！！！
            //{"confirmedCount":549,"curedCount":0,"cities":[{"confirmedCount":495,"curedCount":0,"cityName":"武汉","deadCount":0,"suspectedCount":0},{"confirmedCount":22,"curedCount":0,"cityName":"孝感","deadCount":0,"suspectedCount":0},{"confirmedCount":12,"curedCount":0,"cityName":"黄冈","deadCount":0,"suspectedCount":0},{"confirmedCount":8,"curedCount":0,"cityName":"荆州","deadCount":0,"suspectedCount":0},{"confirmedCount":8,"curedCount":0,"cityName":"荆门","deadCount":0,"suspectedCount":0},{"confirmedCount":2,"curedCount":0,"cityName":"仙桃","deadCount":0,"suspectedCount":0},{"confirmedCount":1,"curedCount":0,"cityName":"宜昌","deadCount":0,"suspectedCount":0},{"confirmedCount":1,"curedCount":0,"cityName":"十堰","deadCount":0,"suspectedCount":0}],"provinceShortName":"湖北","comment":"疑似数据待确认，治愈 28 例，死亡 24 例","provinceName":"湖北省","deadCount":0,"suspectedCount":0}
            System.out.println(list);
        } catch (IOException e) {
            System.out.print("error");
            e.printStackTrace();
        }
    }


}
