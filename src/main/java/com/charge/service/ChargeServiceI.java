package com.charge.service;

import com.charge.model.Charge;

/**
 * @author liumw
 * @date 2016/8/11 0011
 */
public interface ChargeServiceI {
    /**根据编码，获取充电桩信息*/
    Charge getChargeByNo(String chargeNo) throws Exception;
}
