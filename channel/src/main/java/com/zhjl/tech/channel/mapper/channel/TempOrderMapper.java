package com.zhjl.tech.channel.mapper.channel;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.zhjl.tech.channel.model.channel.TempOrder;

/**
 *
 * TempOrder 表数据库控制层接口
 *
 */
public interface TempOrderMapper extends AutoMapper<TempOrder> {

    TempOrder getTempOrderByChannelOrdersn(String channelOrdersn);

    TempOrder getTempOrderByState(String state);
}