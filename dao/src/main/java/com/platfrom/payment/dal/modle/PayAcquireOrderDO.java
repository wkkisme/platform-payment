package com.platfrom.payment.dal.modle;

import com.platfrom.payment.dal.pojo.BaseDO;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "platform_pay_aquire_order")
public class PayAcquireOrderDO extends BaseDO {

    /**
     * 支付号
     */
    @Column(name = "pay_no")
    private String payNo;

    /**
     * 收单号
     */
    @Column(name = "acquire_no")
    private String acquireNo;


    /**
     * 收银台地址
     */
    @Column(name = "redirect_url")
    private String redirectUrl;

    /**
     * 收单渠道
     */
    @Column(name = "channel")
    private String channel;


    /**
     * 获取支付号
     *
     * @return pay_no - 支付号
     */
    public String getPayNo() {
        return payNo;
    }

    /**
     * 设置支付号
     *
     * @param payNo 支付号
     */
    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    /**
     * 获取收单号
     *
     * @return acquire_no - 收单号
     */
    public String getAcquireNo() {
        return acquireNo;
    }

    /**
     * 设置收单号
     *
     * @param acquireNo 收单号
     */
    public void setAcquireNo(String acquireNo) {
        this.acquireNo = acquireNo;
    }

    /**
     * 收银台地址
     *
     * @return redirectUrl - 收银台地址
     */
    public String getRedirectUrl() {
        return redirectUrl;
    }

    /**
     * 收银台地址
     *
     * @param redirectUrl - 收银台地址
     */
    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    /**
     * 获取收单渠道
     *
     * @return channel - 收单渠道
     */
    public String getChannel() {
        return channel;
    }

    /**
     * 设置收单渠道
     *
     * @param channel 收单渠道
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }
}