package com.charge.service.front.impl;

import com.charge.config.vo.Json;
import com.charge.config.vo.ReturnMsg;
import com.charge.dao.CollectMapper;
import com.charge.model.Collect;
import com.charge.service.front.CollectServiceI;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liumw
 * @date 2016/8/12 0012
 */
@Service("collectServiceI")
public class CollectServiceImpl implements CollectServiceI {
    private static final Logger logger = Logger.getLogger(CollectServiceImpl.class);

    @Autowired
    private CollectMapper collectMapper;

    /**
     * 添加电桩采集信息
     * @param username
     * @param info
     * @return
     */
    @Override
    public Json addCollect(String username, String info) {
        Json json = new Json();
        Collect collect = new Collect();
        if (username != null){
            collect.setUsername(username);
        }
        collect.setInfo(info);
        collectMapper.insert(collect);

        json.setSuccess(true);
        json.setResult_code(ReturnMsg.SUCCESS);
        json.setMsg("提交成功");
        logger.info("添加电桩采集信息成功");

        return json;
    }
}
