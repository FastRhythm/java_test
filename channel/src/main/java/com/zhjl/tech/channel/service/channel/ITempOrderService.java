package com.zhjl.tech.channel.service.channel;

import com.baomidou.framework.service.ISuperService;
import com.zhjl.tech.channel.model.channel.TempOrder;
import org.springframework.stereotype.Service;

/**
 *
 * TempOrder 表数据服务层接口
 *
 */
@Service
public interface ITempOrderService extends ISuperService<TempOrder> {
    TempOrder getTempOrderByChannelOrdersn(String channelOrdersn);
    TempOrder getTempOrderByState(String state);

}