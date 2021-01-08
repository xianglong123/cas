package com.cas.components.beanAware.grippeService;

import com.alibaba.fastjson.JSONObject;
import com.cas.dao.mapper.GrippeInfoMapper;
import com.cas.domain.grippe.GrippeCity;
import com.cas.domain.grippe.GrippeProv;
import com.cas.utils.patternUtils.PatternMenu;
import com.cas.utils.patternUtils.PatternUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 12:47 2020-01-24
 * @version: V1.0
 * @review: 疫情开始数据爬虫
 */
@Slf4j
@Service
public class GrippeService {//收集数据

    @Autowired
    private GrippeInfoMapper grippeInfoMapper;

    //丁香网站
    private static final String[] cmds = {"curl", "https://3g.dxy.cn/newh5/view/pneumonia"};

    public void collectDate() {
        log.info("跑批次开始");
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
            String data = PatternUtil.match(result, PatternMenu.PATTERN_DATA)
                    .replace("<script id=\"getAreaStat\">try { window.getAreaStat = ", "")
                    .replace("}catch(e){}</script>", "");
            log.debug(data);
            List<GrippeProv> list = JSONObject.parseArray(data, GrippeProv.class);
            //数据流感-->数据获取成功！！！
            //{"confirmedCount":549,"curedCount":0,"cities":[{"confirmedCount":495,"curedCount":0,"cityName":"武汉","deadCount":0,"suspectedCount":0},{"confirmedCount":22,"curedCount":0,"cityName":"孝感","deadCount":0,"suspectedCount":0},{"confirmedCount":12,"curedCount":0,"cityName":"黄冈","deadCount":0,"suspectedCount":0},{"confirmedCount":8,"curedCount":0,"cityName":"荆州","deadCount":0,"suspectedCount":0},{"confirmedCount":8,"curedCount":0,"cityName":"荆门","deadCount":0,"suspectedCount":0},{"confirmedCount":2,"curedCount":0,"cityName":"仙桃","deadCount":0,"suspectedCount":0},{"confirmedCount":1,"curedCount":0,"cityName":"宜昌","deadCount":0,"suspectedCount":0},{"confirmedCount":1,"curedCount":0,"cityName":"十堰","deadCount":0,"suspectedCount":0}],"provinceShortName":"湖北","comment":"疑似数据待确认，治愈 28 例，死亡 24 例","provinceName":"湖北省","deadCount":0,"suspectedCount":0}
            System.out.println(list);
            insertDate(list);
            log.info("时间：" + new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date()) + "数据收集完毕!!! 状态：Success");
        } catch (IOException e) {
            log.info("丁香数据处理异常：" + e);
            log.info("时间：" + new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date()) + "数据收集完毕!!! 状态：Failed");
        }
    }

    //持久化数据
    private void insertDate(List<GrippeProv> list) {
        log.info("持久化开始……");
        //循环持久化
        for(GrippeProv grippeProv : list) {
            grippeInfoMapper.inertGrippeProvInfo(grippeProv);
            //持久化市数据
            List<GrippeCity> cities = grippeProv.getCities();
            for(GrippeCity grippeCity : cities) {
                grippeCity.setProvinceName(grippeProv.getProvinceName());
                grippeInfoMapper.inertGrippeCityInfo(grippeCity);
            }
        }
    }

}
