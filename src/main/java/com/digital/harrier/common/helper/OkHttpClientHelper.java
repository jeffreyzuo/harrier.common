package com.digital.harrier.common.helper;

import okhttp3.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OkHttpClientHelper {

    private static Map<String,OkHttpClient> okHttpClientMap = new HashMap<>();
    private static final MediaType MEDIA_JSON = MediaType.parse("application/json; charset=utf-8");
    private static final MediaType MEDIA_TEXT = MediaType.parse("text/text; charset=utf-8");
    private static final String DEFAULT_KEY = "auto_easy_http_client";
    private static final Logger logger = Logger.getLogger(OkHttpClientHelper.class);

    public static OkHttpClient getOkHttpClient(String appkey,String secret) {
        OkHttpClient okHttpClient;

        if(StringUtils.isEmpty(appkey)||StringUtils.isEmpty(secret)) {
            okHttpClient = okHttpClientMap.getOrDefault(DEFAULT_KEY,null);
            if(okHttpClient == null) {
                okHttpClient = new OkHttpClient.Builder().build();
                okHttpClientMap.put(DEFAULT_KEY,okHttpClient);
            }
            return okHttpClient;
        }
        okHttpClient = okHttpClientMap.getOrDefault(appkey,null);
        if(okHttpClient != null) return okHttpClient;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.networkInterceptors().add(chain -> {
            String curtime = getNowTimeStamp();
            String nonce = RandomStringUtils.randomAlphanumeric(16).toUpperCase();
            String checksum = DigestUtils.sha1Hex(secret + nonce + curtime);
            Request.Builder reqBuilder = chain.request().newBuilder()
                    .addHeader("appkey",appkey)
                    .addHeader("nonce",nonce)
                    .addHeader("curtime",curtime)
                    .addHeader("checksum",checksum);
            return chain.proceed(reqBuilder.build());
        });

        okHttpClient = builder.build();
        okHttpClientMap.put(appkey,okHttpClient);

        return okHttpClient;
    }

    public static String doGetRequest(OkHttpClient httpClient,String url, Map<String,String> params) {
        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
        if (params != null) {
            for(Map.Entry<String,String> param:params.entrySet()) {
                httpBuilder.addQueryParameter(param.getKey(),param.getValue());
            }
        }
        Request request = new Request.Builder().url(httpBuilder.build()).build();
        return doRequest(httpClient,request);

    }

    /**
     * 发送post FORM 请求
     * @param httpClient OkHttpClient
     * @param url String
     * @param params Map
     * @param headers Map
     * @return string|null
     */
    public static String doPostFormRequest(OkHttpClient httpClient, String url,Map<String,String> params,Map<String,String> headers) {
        return doRequest(httpClient,buildFormPostRequest(url,params,headers));
    }



    /**
     * 发送post json 请求
     * @param httpClient
     * @param url
     * @param json
     * @param headers
     * @return String|NULL
     */
    public static String doPostJsonRequest(OkHttpClient httpClient,String url,String json,Map<String,String> headers) {
        return doRequest(httpClient,buildJsonPostRequest(url,json,headers));
    }

    public static String doPostJson(OkHttpClient httpClient,String url,String jsonBody) {
        return doPostJsonRequest(httpClient,url,jsonBody,null);
    }

    /**
     * 发送post text body 请求
     * @param httpClient
     * @param url
     * @param body
     * @param headers
     * @return String|NULL
     */
    public static String doPostBodyRequest(OkHttpClient httpClient,String url,String body,Map<String,String> headers) {
        return doRequest(httpClient,buildTextBodyPostRequest(url,body,headers));
    }


    public static String doRequest(OkHttpClient httpClient,Request request) {
        try {
            Response response = httpClient.newCall(request).execute();
            if (!response.isSuccessful()) return null;
            try {
                return response.body().string();
            } catch (NullPointerException e) {
                logger.error(e.getMessage());
                return null;
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public static Request buildFormPostRequest(String url,Map<String,String> params,Map<String,String> headers) {
        FormBody.Builder formBuilder = new FormBody.Builder();
        for(String key:params.keySet()) {
            formBuilder.add(key,params.get(key));
        }

        return getRequestBuilder(url,headers).post(formBuilder.build()).build();
    }

    private static Request.Builder getRequestBuilder(String url,Map<String,String> headers) {
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        if (headers!=null) {
            for(Map.Entry<String,String> header:headers.entrySet()) {
                builder.addHeader(header.getKey(),header.getValue());
            }
        }
        return builder;
    }

    public static Request buildJsonPostRequest(String url,String jsonBody,Map<String,String> headers) {
        return getRequestBuilder(url,headers).post(RequestBody.create(MEDIA_JSON,jsonBody)).build();
    }

    public static Request buildTextBodyPostRequest(String url,String body,Map<String,String> headers) {
        return getRequestBuilder(url,headers).post(RequestBody.create(MEDIA_TEXT,body)).build();
    }

    private static String getNowTimeStamp() {
        long time = System.currentTimeMillis();
        return String.valueOf(time/1000);
    }

}
