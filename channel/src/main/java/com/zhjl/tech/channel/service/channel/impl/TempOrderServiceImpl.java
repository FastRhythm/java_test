package com.zhjl.tech.channel.service.channel.impl;

import com.baomidou.framework.service.impl.SuperServiceImpl;
import com.zhjl.tech.channel.mapper.channel.TempOrderMapper;
import com.zhjl.tech.channel.model.channel.TempOrder;
import com.zhjl.tech.channel.service.channel.ITempOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 * TempOrder 表数据服务层接口实现类
 *
 */
@Service
public class TempOrderServiceImpl extends SuperServiceImpl<TempOrderMapper, TempOrder> implements ITempOrderService {

    @Resource
    TempOrderMapper tempOrderMapper;

    @Override
    public TempOrder getTempOrderByChannelOrdersn(String channelOrdersn) {
        return tempOrderMapper.getTempOrderByChannelOrdersn(channelOrdersn);
    }

    @Override
    public TempOrder getTempOrderByState(String state) {
        return tempOrderMapper.getTempOrderByState(state);
    }
}