package com.platfrom.payment.domain.eft;

import com.platfrom.payment.common.enums.PaymentErrorEnum;
import com.platfrom.payment.common.exception.PaymentException;
import com.platfrom.payment.domain.config.AppConfig;
import com.platfrom.payment.domain.eft.dto.EftRequest;
import com.platfrom.payment.domain.eft.dto.EftResponse;
import com.platfrom.payment.domain.eft.dto.EftSignature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.util.*;

@Slf4j
public class EftSignatureUtils {


    private static final String EOPG_KEY = "EOPG_KEY";

    private static final String SPLITER = "=";

    private static final String SPLITER_JOIN = "&";


    public static void encrypt(EftRequest request) {
        String signature = buildSignature(request);
        request.setSignature(signature);
    }


    public static void decrypt(EftResponse response) {
        String signature = buildSignature(response);
        if (!signature.equalsIgnoreCase(response.getSignature())) {
            throw new PaymentException(PaymentErrorEnum.SIGNATURE_VERIFIED_FAILED);
        }
    }


    private static String buildSignature(Object obj) {
        Map<String, String> signatureMap = buildSignatureMap(obj);

        List<String> keys = new ArrayList<>(signatureMap.keySet());


        keys.sort(Comparator.comparing(String::toString));

        StringBuffer signatureKey = new StringBuffer(AppConfig.get(EOPG_KEY));


        keys.forEach(key -> signatureKey.append(key).append(SPLITER).append(signatureMap.get(key)).append(SPLITER_JOIN));

        signatureKey.deleteCharAt(signatureKey.length() - 1);

        return doHash(signatureKey.toString());

    }

    private static String doHash(String key) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(key.getBytes("UTF-8"));
            return byte2Hex(messageDigest.digest());
        } catch (Exception e) {
            throw new PaymentException(PaymentErrorEnum.SHA_256_ERROR);
        }
    }

    private static String byte2Hex(byte[] digest) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < digest.length; i++) {
            temp = Integer.toHexString(digest[i] & 0xFF);
            if (temp.length() == 1) {
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }


    private static Map<String, String> buildSignatureMap(Object obj) {
        Map<String, String> signatureMap = new HashMap<>();
        Field[] fields = FieldUtils.getAllFields(obj.getClass());
        try {

            for (Field field : fields) {
                EftSignature eftSignature = field.getAnnotation(EftSignature.class);
                if (eftSignature == null) {
                    continue;
                }
                field.setAccessible(true);
                Object value = field.get(obj);
                if (value == null) {
                    continue;
                }
                if (value instanceof String) {
                    signatureMap.put(field.getName(), (String) value);
                } else {
                    log.error(" request error, field[{}] is not a String type.", field.getName());
                    throw new PaymentException(PaymentErrorEnum.EFT_REQUEST_ERROR);
                }

            }
        } catch (IllegalAccessException e) {
            log.error(" request error.", e);
            throw new PaymentException(PaymentErrorEnum.EFT_REQUEST_ERROR);
        }
        return signatureMap;
    }

}
