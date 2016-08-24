package com.charge.service.front;

import com.charge.config.vo.Json;

/**
 * 电桩采集--接口
 * @author liumw
 * @date 2016/8/12 0012
 */
public interface CollectServiceI {
    /**添加电桩采集信息*/
    Json addCollect(String username, String info);
}
