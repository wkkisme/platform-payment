package com.platfrom.payment.common.util;

import com.platfrom.payment.common.enums.PaymentErrorEnum;
import com.platfrom.payment.common.exception.PaymentException;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetUtil {
	
	public final static String IP_DEFAULT = "127.0.0.1";

	public static String getIp(String defaultIP) {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return defaultIP;
		}
	}


	public static String requiredGetIp() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			throw new PaymentException(PaymentErrorEnum.NETWORK_ERROR);
		}
	}
	
	public static long getIpId() {
		String ip = NetUtil.getIp(IP_DEFAULT);
		String id = ip.substring(ip.lastIndexOf(".") + 1);
		return Long.parseLong(id);
	}
}
