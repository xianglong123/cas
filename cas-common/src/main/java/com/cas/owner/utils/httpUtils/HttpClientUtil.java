package com.cas.owner.utils.httpUtils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.net.URISyntaxException;
import java.util.List;

/**
 * @author: xianglong
 * @date: 2019/8/19 11:57
 * @version: V1.0
 * @review: xiang_long
 */
@Slf4j
public class HttpClientUtil {


    //发送POST请求(普通表单格式)
    public static String postFrom(String path, List<NameValuePair> parametersBody) throws Exception {
        HttpEntity entity = new UrlEncodedFormEntity(parametersBody, Charsets.UTF_8);
        return postRequest(path, "application/x-www-form-urlencoded", entity);
    }

    //发送POST请求(JSON格式)
    public static String postJSON(String path, String json) throws Exception {
        HttpEntity entity = new StringEntity(json, Charsets.UTF_8);
        return postRequest(path, "application/json", entity);

    }

    //发送post请求
    public static String postRequest(String path, String mediaType, HttpEntity entity) throws Exception {
        log.debug("[postRequest] resourceUrl: {}", path);
        HttpPost post = new HttpPost(path);
        post.addHeader("Content-Type", mediaType);
        post.addHeader("Accept", "application/json");
        post.setEntity(entity);
        HttpResponse response = null;
        try {
            HttpClient client = HttpClientBuilder.create().build();
            response = client.execute(post);
            int code = response.getStatusLine().getStatusCode();
            if (code >= 400)
                throw new  ClientProtocolException("POST请求失败,CODE: "+code);
            return EntityUtils.toString(response.getEntity());
        } catch (ClientProtocolException e) {
            throw new Exception("POST请求失败");
        } finally {
            post.releaseConnection();
        }

    }

    // 发送GET请求
    public static String getRequest(String path, List<NameValuePair> parametersBody) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(path);
        uriBuilder.setParameters(parametersBody);
        HttpGet get = new HttpGet(uriBuilder.build());
        HttpClient client = HttpClientBuilder.create().build();
        try {
            HttpResponse response = client.execute(get);
            int code = response.getStatusLine().getStatusCode();
            if (code >= 400)
                throw new RuntimeException((new StringBuilder()).append("Could not access protected resource. Server returned http code: ").append(code).toString());
            return EntityUtils.toString(response.getEntity());
        }
        catch (Exception e) {
            log.info("postRequest -- IO error!");
        }
        finally {
            get.releaseConnection();
        }
        return null;
    }
}

