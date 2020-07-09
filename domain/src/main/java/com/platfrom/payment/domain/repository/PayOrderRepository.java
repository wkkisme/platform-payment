package com.platfrom.payment.domain.repository;

import com.alibaba.fastjson.JSON;
import com.platfrom.payment.common.model.Identity;
import com.platfrom.payment.dal.mapper.PayOrderMapper;
import com.platfrom.payment.dal.modle.PayOrderDO;
import com.platfrom.payment.domain.model.enums.PayChannelEnum;
import com.platfrom.payment.domain.model.enums.PayCurrencyTypeEnum;
import com.platfrom.payment.domain.model.enums.PayEnvEnum;
import com.platfrom.payment.domain.model.enums.PayOrderStateEnum;
import com.platfrom.payment.domain.model.order.PayCurrency;
import com.platfrom.payment.domain.model.order.PayOrder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.HashMap;

@Repository
public class PayOrderRepository {

    @Resource
    private PayOrderMapper payOrderMapper;


    public void store(PayOrder payOrder) {
        PayOrderDO payOrderDO = convert2DO(payOrder);
        payOrderMapper.insertSelective(payOrderDO);
        payOrder.setId(payOrderDO.getId());
    }


    public PayOrder loadByBiz(Identity biz) {
        Example example = new Example(PayOrderDO.class);

        example.createCriteria()
                .andEqualTo("bizId", biz.getId())
                .andEqualTo("bizType", biz.getType());

        PayOrderDO payOrderDO = payOrderMapper.selectOneByExample(example);

        if (payOrderDO == null) {
            return null;
        }
        return convertDO2PayOrder(payOrderDO);
    }


    public int updateBizExt(PayOrder payOrder) {
        PayOrderDO payOrderDO = new PayOrderDO();
        payOrderDO.setBizExt(JSON.toJSONString(payOrder.getBizExt()));
        payOrderDO.setId(payOrder.getId());
        return payOrderMapper.updateByPrimaryKeySelective(payOrderDO);
    }

    private PayOrder convertDO2PayOrder(PayOrderDO payOrderDO) {
        PayOrder payOrder = new PayOrder();
        payOrder.setId(payOrderDO.getId());
        payOrder.setBiz(new Identity(payOrderDO.getBizId(), payOrderDO.getBizType()));
        payOrder.setPayCurrency(new PayCurrency(payOrderDO.getCurrencyAmt(), PayCurrencyTypeEnum.findByType(payOrderDO.getCurrencyType())));
        payOrder.setChannel(PayChannelEnum.findByChannel(payOrderDO.getPayChannel()));
        payOrder.setEnv(PayEnvEnum.findByEnv(payOrderDO.getPayEnv()));
        payOrder.setPayNo(payOrderDO.getPayNo());
        payOrder.setState(PayOrderStateEnum.findByState(payOrderDO.getState()));
        payOrder.setPayCreateTime(payOrderDO.getPayCreateTime());
        payOrder.setPayFinishTime(payOrderDO.getPayFinishTime());
        payOrder.setPayDigest(payOrderDO.getPayDigest());
        if (StringUtils.isBlank(payOrderDO.getBizExt())) {
            payOrder.setBizExt(new HashMap<>());
        } else {
            payOrder.setBizExt(JSON.parseObject(payOrderDO.getBizExt(), HashMap.class));
        }
        return payOrder;

    }

    private PayOrderDO convert2DO(PayOrder payOrder) {
        PayOrderDO payOrderDO = new PayOrderDO();
        payOrderDO.setId(payOrder.getId());
        payOrderDO.setBizId(payOrder.getBiz().getId());
        payOrderDO.setBizType(payOrder.getBiz().getType());
        payOrderDO.setCurrencyAmt(payOrder.getPayCurrency().getAmount());
        payOrderDO.setCurrencyType(payOrder.getPayCurrency().getCurrencyTypeEnum().getType());
        payOrderDO.setPayChannel(payOrder.getChannel().getChannel());
        payOrderDO.setPayEnv(payOrder.getEnv().getEnv());
        payOrderDO.setPayNo(payOrder.getPayNo());
        payOrderDO.setState(payOrder.getState().getState());
        payOrderDO.setPayCreateTime(payOrder.getPayCreateTime());
        payOrderDO.setPayFinishTime(payOrder.getPayFinishTime());
        payOrderDO.setPayDigest(payOrder.getPayDigest());
        payOrderDO.setBizExt(JSON.toJSONString(payOrder.getBizExt()));
        return payOrderDO;
    }
}
