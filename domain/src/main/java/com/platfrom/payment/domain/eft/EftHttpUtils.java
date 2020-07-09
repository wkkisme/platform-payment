package com.platfrom.payment.domain.eft;

import com.alibaba.fastjson.JSON;
import com.platfrom.payment.common.enums.PaymentErrorEnum;
import com.platfrom.payment.common.exception.PaymentException;
import com.platfrom.payment.common.util.HttpResponse;
import com.platfrom.payment.common.util.HttpUtils;
import com.platfrom.payment.domain.eft.dto.EftRequest;
import com.platfrom.payment.domain.eft.dto.EftResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
public class EftHttpUtils {


    public static <T extends EftResponse> T doGet(String url, EftRequest request, Class<T> clazz) {

        EftSignatureUtils.encrypt(request);

        Map<String, String> param = buildRequestMap(request);

        HttpResponse httpResponse;
        try {
            httpResponse = HttpUtils.get(url, param);
        } catch (IOException e) {
            log.error(" connect failed.", e);
            throw new PaymentException(PaymentErrorEnum.EFT_CONNECT_FAILED);
        }

        T response = JSON.parseObject(httpResponse.getResponse(), clazz);

//        EftSignatureUtils.decrypt(response);

        return response;

    }

    public static <T extends EftResponse> T doGet(String url, EftRequest request, Function<HttpResponse, T> parseResponse) {

        EftSignatureUtils.encrypt(request);

        Map<String, String> param = buildRequestMap(request);

        HttpResponse responseStr = null;
        try {
            responseStr = HttpUtils.get(url, param);
        } catch (IOException e) {
            log.error(" connect failed.", e);
            throw new PaymentException(PaymentErrorEnum.EFT_CONNECT_FAILED);
        }

        T response = parseResponse.apply(responseStr);

//        EftSignatureUtils.decrypt(response);

        return response;

    }

    private static Map<String, String> buildRequestMap(EftRequest request) {
        Map<String, String> param = new HashMap<>();
        Field[] fields = FieldUtils.getAllFields(request.getClass());

        try {

            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(request);
                if (value == null) {
                    continue;
                }
                if (value instanceof String) {
                    param.put(field.getName(), (String) value);
                } else {
                    log.error(" request error, field[{}] is not a String type.", field.getName());
                    throw new PaymentException(PaymentErrorEnum.EFT_REQUEST_ERROR);
                }

            }
        } catch (IllegalAccessException e) {
            log.error(" request error.", e);
            throw new PaymentException(PaymentErrorEnum.EFT_REQUEST_ERROR);
        }
        return param;
    }
}
