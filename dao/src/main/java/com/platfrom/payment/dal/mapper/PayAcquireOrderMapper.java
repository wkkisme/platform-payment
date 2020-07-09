package com.platfrom.payment.dal.mapper;

import com.platfrom.payment.dal.modle.PayAcquireOrderDO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface PayAcquireOrderMapper extends Mapper<PayAcquireOrderDO> {

//    PayAcquireOrderDO selectByPayNo(@Param("payNo") String payNo);
}