package com.platfrom.payment.domain.repository;

import com.platfrom.payment.dal.mapper.PayAcquireOrderMapper;
import com.platfrom.payment.dal.modle.PayAcquireOrderDO;
import com.platfrom.payment.domain.model.acquire.AcquireOrder;
import com.platfrom.payment.domain.model.enums.PayChannelEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

@Repository
public class AcquireOrderRepository {

    @Resource
    private PayAcquireOrderMapper payAcquireOrderMapper;


    public void store(AcquireOrder acquireOrder) {
        PayAcquireOrderDO payAcquireOrderDO = convert2DO(acquireOrder);
        payAcquireOrderMapper.insertSelective(payAcquireOrderDO);
        acquireOrder.setId(payAcquireOrderDO.getId());
    }


    public AcquireOrder loadByPayNo(String payNo) {
        Example example = new Example(PayAcquireOrderDO.class);

        example.createCriteria()
                .andEqualTo("payNo", StringUtils.trimToEmpty(payNo));

        PayAcquireOrderDO payAcquireOrderDO = payAcquireOrderMapper.selectOneByExample(example);

        if (payAcquireOrderDO == null) {
            return null;
        }
        return convertDO2AcquireOrder(payAcquireOrderDO);
    }


    private AcquireOrder convertDO2AcquireOrder(PayAcquireOrderDO payAcquireOrderDO) {
        AcquireOrder acquireOrder = new AcquireOrder();
        acquireOrder.setId(payAcquireOrderDO.getId());
        acquireOrder.setPayNo(payAcquireOrderDO.getPayNo());
        acquireOrder.setAcquireNo(payAcquireOrderDO.getAcquireNo());
        acquireOrder.setChannel(PayChannelEnum.findByChannel(payAcquireOrderDO.getChannel()));
        acquireOrder.setRedirectUrl(payAcquireOrderDO.getRedirectUrl());
        return acquireOrder;

    }

    private PayAcquireOrderDO convert2DO(AcquireOrder acquireOrder) {
        PayAcquireOrderDO payAcquireOrderDO = new PayAcquireOrderDO();
        payAcquireOrderDO.setId(acquireOrder.getId());
        payAcquireOrderDO.setPayNo(acquireOrder.getPayNo());
        payAcquireOrderDO.setAcquireNo(acquireOrder.getAcquireNo());
        payAcquireOrderDO.setChannel(acquireOrder.getChannel().getChannel());
        payAcquireOrderDO.setRedirectUrl(acquireOrder.getRedirectUrl());
        return payAcquireOrderDO;
    }
}
