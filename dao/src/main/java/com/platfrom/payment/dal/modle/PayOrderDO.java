package com.platfrom.payment.dal.modle;

import com.platfrom.payment.dal.pojo.BaseDO;

import javax.persistence.*;
import java.util.Date;

@Table(name = "platform_pay_order")
public class PayOrderDO extends BaseDO {

    /**
     * 业务编号
     */
    @Column(name = "biz_id")
    private String bizId;

    /**
     * 业务类型
     */
    @Column(name = "biz_type")
    private String bizType;

    /**
     * 货币金额
     */
    @Column(name = "currency_amt")
    private Long currencyAmt;

    /**
     * 币种
     */
    @Column(name = "currency_type")
    private String currencyType;

    /**
     * 支付渠道
     */
    @Column(name = "pay_channel")
    private String payChannel;

    /**
     * 支付环境
H5 PC APP
     */
    @Column(name = "pay_env")
    private String payEnv;

    /**
     * 支付号
     */
    @Column(name = "pay_no")
    private String payNo;

    /**
     * 状态
        创建 CREATE
        成功 SUCCESS
        失败 FAILED
     */
    @Column(name = "state")
    private String state;

    /**
     * 支付创建时间
     */
    @Column(name = "pay_create_time")
    private Date payCreateTime;

    /**
     * 支付完成时间
     */
    @Column(name = "pay_finish_time")
    private Date payFinishTime;

    /**
     * 摘要
     */
    @Column(name = "pay_digest")
    private String payDigest;

    /**
     * 业务拓展字段
     */
    @Column(name = "biz_ext")
    private String bizExt;



    /**
     * 获取业务编号
     *
     * @return biz_id - 业务编号
     */
    public String getBizId() {
        return bizId;
    }

    /**
     * 设置业务编号
     *
     * @param bizId 业务编号
     */
    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    /**
     * 获取业务类型
     *
     * @return biz_type - 业务类型
     */
    public String getBizType() {
        return bizType;
    }

    /**
     * 设置业务类型
     *
     * @param bizType 业务类型
     */
    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    /**
     * 获取货币金额
     *
     * @return currency_amt - 货币金额
     */
    public Long getCurrencyAmt() {
        return currencyAmt;
    }

    /**
     * 设置货币金额
     *
     * @param currencyAmt 货币金额
     */
    public void setCurrencyAmt(Long currencyAmt) {
        this.currencyAmt = currencyAmt;
    }

    /**
     * 获取币种
     *
     * @return currency_type - 币种
     */
    public String getCurrencyType() {
        return currencyType;
    }

    /**
     * 设置币种
     *
     * @param currencyType 币种
     */
    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    /**
     * 获取支付渠道
     *
     * @return pay_channel - 支付渠道
     */
    public String getPayChannel() {
        return payChannel;
    }

    /**
     * 设置支付渠道
     *
     * @param payChannel 支付渠道
     */
    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    /**
     * 获取支付环境
H5 PC APP
     *
     * @return pay_env - 支付环境
H5 PC APP
     */
    public String getPayEnv() {
        return payEnv;
    }

    /**
     * 设置支付环境
H5 PC APP
     *
     * @param payEnv 支付环境
H5 PC APP
     */
    public void setPayEnv(String payEnv) {
        this.payEnv = payEnv;
    }

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
     * 获取状态
已登机 CREATE
成功 SUCCESS
失败 FAILED
     *
     * @return state - 状态
已登机 CREATE
成功 SUCCESS
失败 FAILED
     */
    public String getState() {
        return state;
    }

    /**
     * 设置状态
已登机 CREATE
成功 SUCCESS
失败 FAILED
     *
     * @param state 状态
已登机 CREATE
成功 SUCCESS
失败 FAILED
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取支付创建时间
     *
     * @return pay_create_time - 支付创建时间
     */
    public Date getPayCreateTime() {
        return payCreateTime;
    }

    /**
     * 设置支付创建时间
     *
     * @param payCreateTime 支付创建时间
     */
    public void setPayCreateTime(Date payCreateTime) {
        this.payCreateTime = payCreateTime;
    }

    /**
     * 获取支付完成时间
     *
     * @return pay_finish_time - 支付完成时间
     */
    public Date getPayFinishTime() {
        return payFinishTime;
    }

    /**
     * 设置支付完成时间
     *
     * @param payFinishTime 支付完成时间
     */
    public void setPayFinishTime(Date payFinishTime) {
        this.payFinishTime = payFinishTime;
    }

    /**
     * 获取摘要
     *
     * @return pay_digest - 摘要
     */
    public String getPayDigest() {
        return payDigest;
    }

    /**
     * 设置摘要
     *
     * @param payDigest 摘要
     */
    public void setPayDigest(String payDigest) {
        this.payDigest = payDigest;
    }

    /**
     * 获取业务拓展字段
     *
     * @return biz_ext - 业务拓展字段
     */
    public String getBizExt() {
        return bizExt;
    }

    /**
     * 设置业务拓展字段
     *
     * @param bizExt 业务拓展字段
     */
    public void setBizExt(String bizExt) {
        this.bizExt = bizExt;
    }

}