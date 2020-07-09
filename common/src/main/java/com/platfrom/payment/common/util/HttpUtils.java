package com.platfrom.payment.common.util;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
public class HttpUtils {

    private final static OkHttpClient client = new OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).build();

    public static HttpResponse get(String url, Map<String, String> param) throws IOException {
        String uri = "";
        if (param != null && !param.isEmpty()) {
            StringBuffer sb = new StringBuffer();
            param.forEach((k, v) -> sb.append(k).append("=").append(v).append("&"));
            sb.deleteCharAt(sb.length() - 1);
            uri = "?" + sb;
        }
        url = url + uri;
        log.info("http【GET】,url={}", url);
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();

        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setParam(param);
        httpResponse.setResponse(response.body().string());
        httpResponse.setUrl(url);
        return httpResponse;
    }

    public static HttpResponse get(String url) throws IOException {
        return get(url, null);
    }

    public static String post(String url, Map<String, String> param) throws IOException {
        RequestBody body = setRequestBody(param);
        Request.Builder requestBuilder = new Request.Builder();
        Request request = requestBuilder.post(body).addHeader("text/xml", "application/xml").url(url).build();
        Response response = client.newCall(request).execute();
        return response.body().bytes().toString();
    }

    private static RequestBody setRequestBody(Map<String, String> param) {
        RequestBody body = null;
        okhttp3.FormBody.Builder formEncodingBuilder = new okhttp3.FormBody.Builder();
        if (param != null) {
            param.forEach((k, v) -> formEncodingBuilder.add(k, v));
        }
        body = formEncodingBuilder.build();
        return body;

    }

}
