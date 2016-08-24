package com.charge.service.front.impl;

import com.charge.dao.ChargeMapper;
import com.charge.model.Charge;
import com.charge.service.front.ChargeServiceI;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liumw
 * @date 2016/8/11 0011
 */
@Service("chargeService")
public class ChargeServiceImpl implements ChargeServiceI {
    private static final Logger logger = Logger.getLogger(ChargeServiceImpl.class);
    @Autowired
    private ChargeMapper chargeMapper;

    /**
     * 根据编码，获取充电桩信息
     * @param chargeNo
     * @return
     */
    @Override
    public Charge getChargeByNo(String chargeNo)  throws Exception{
        return chargeMapper.selectByChargeNo(chargeNo);
    }
}
