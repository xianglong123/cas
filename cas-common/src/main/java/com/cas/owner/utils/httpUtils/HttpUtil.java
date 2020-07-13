package com.cas.owner.utils.httpUtils;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * @author: xianglong
 * @date: 2019/8/17 14:34
 * @version: V1.0
 * @review: xiang_long
 */
public class HttpUtil {
    /**
     * 发送post请求，数据json
     * @param url
     * @param json
     * @return
     * @throws IOException
     */
    public static String httpPostWithjson(String url, String json) throws IOException {
        String result = "";
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            BasicResponseHandler handler = new BasicResponseHandler();
            StringEntity entity = new StringEntity(json, "utf-8");//解决中文乱码问题
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            result = httpClient.execute(httpPost, handler);
            return result;
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }






}
