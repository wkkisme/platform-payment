package com.platfrom.payment.dal.modle;

import com.platfrom.payment.dal.pojo.BaseDO;

import javax.persistence.*;

@Table(name = "platform_pay_channel_config")
public class PayChannelConfigDO extends BaseDO {

    /**
     * 渠道
     */
    private String channel;

    /**
     * 状态  启用 enable 禁用 disable
     */
    private String state;


    /**
     * 获取渠道
     *
     * @return acquirers - 渠道
     */
    public String getChannel() {
        return channel;
    }

    /**
     * 设置渠道
     *
     * @param channel 渠道
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * 获取状态  启用 enable 禁用 disable
     *
     * @return state - 状态  启用 enable 禁用 disable
     */
    public String getState() {
        return state;
    }

    /**
     * 设置状态  启用 enable 禁用 disable
     *
     * @param state 状态  启用 enable 禁用 disable
     */
    public void setState(String state) {
        this.state = state;
    }
}