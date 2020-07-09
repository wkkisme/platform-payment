package com.platfrom.payment.common.util;

/**
 * @author
 */
public class SnowFlakeWorker {

    // 时间戳基线  2017-01-01 00:00:00
    private final static long twepoch = 1483200000000l;

    // 8bit Ip末尾地址  0~255
    private final static long ipBits = 8L;

    // 14bit 自增序列
    private final static long seqBits = 14L;

    private final static long seqMax = ~(-1L << seqBits);

    // 64位的数字：1bit 符号位  41bit时间  8bitIP标志 14bit序列号
    private final static long ipIdLeftShift = seqBits;
    private final static long timeLeftShift = seqBits + ipBits;

    // IP标识(0~255)
    private static long ipId = NetUtil.getIpId();

    // 毫秒内序列(0~16384)
    private static long seq = 0L;

    // 上次生成ID的时间截
    private static long lastTime = -1L;

    public synchronized static long nextId() {
        long nowTime = System.currentTimeMillis();

        if (nowTime < lastTime) {
            throw new RuntimeException(
                    String.format("clock is moving backwards...  Rejecting requests until %d", lastTime));
        }

        if (nowTime == lastTime) {
            seq = (seq + 1) & seqMax;
            if (seq == 0) {
                nowTime = tilNextMillis();
            }
        } else {
            seq = 0L;
        }

        lastTime = nowTime;

        return ((nowTime - twepoch) << timeLeftShift) | (ipId << ipIdLeftShift) | seq;
    }

    private static long tilNextMillis() {
        long nowTime = System.currentTimeMillis();
        while (nowTime <= lastTime) {
            nowTime = System.currentTimeMillis();
        }
        return nowTime;
    }

}
