package com.zhjl.tech.channel.genData;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhjl.tech.channel.Tool.Tool;
import com.zhjl.tech.channel.configs.Configs;
import com.zhjl.tech.channel.dto.AttestDemo;
import com.zhjl.tech.channel.model.channel.TempOrder;
import com.zhjl.tech.channel.service.channel.ITempOrderService;
import com.zhjl.tech.common.encrypt.digest.Hash;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * ps:创建表的时候,可以考虑加上测试名称
 *
 * 根据本次测试的名称[testName]
 * 1 生成n条测试
 * 2 生成对应的文件
 * 3 将对应的数据插入到tables
 */
@Slf4j
@Service
public class GenTestData {

    @Resource
    ITempOrderService tempOrderService;

    @Resource
    Tool tool;

    public static void main(String[] args) {

        //生成请求时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(Configs.data_url);
        String s = sdf.format(date);
        System.out.append(s);
    }
    /**
     * 自动生成数据
     * @param userid 临时文件保存目录
     * @return
     * @throws IOException
     */
    public void gen(String userid) throws IOException {
        AttestDemo attestDemo = new AttestDemo();
        Random rd = new Random();

        attestDemo.setAccessKey(Configs.accessKey);
        attestDemo.setRandom( UUID.randomUUID().toString().replaceAll("-",""));

        //生成请求时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(Configs.data_url);
        String s = sdf.format(date);
        attestDemo.setRequestTime(s);

        //渠道标识
        attestDemo.setChannelId(Configs.channelId);

        //channelUserid
        attestDemo.setChannelUserid(userid);

        String channelOrdersn = "ordersn" + UUID.randomUUID().toString().replaceAll("-","").substring(0,9);
        attestDemo.setChannelOrdersn(channelOrdersn);

        //生成chained
        attestDemo.setChained(String.valueOf(rd.nextInt(2) + 1));
        //生成BizType
        attestDemo.setBizType(String.valueOf(rd.nextInt(6) + 1));
        //sourceFileName,此处与ordersn一样
        attestDemo.setSourceFileName("fname-" + channelOrdersn);
        //sourceFileType
        attestDemo.setSourceFileType("."+UUID.randomUUID().toString().replaceAll("-","").substring(0,3));

        //////////////////////生成文件
        //filesize
        //filehash
        GenTestData genTestData = new GenTestData();
        genTestData.genFile(attestDemo.getChannelOrdersn(), attestDemo);

        attestDemo.setSourceFileLength("" + JSON.toJSONString(attestDemo).getBytes().length);
        Hash hash = new Hash();
        try {
            attestDemo.setSourceFileHash(Base64.getEncoder().encodeToString(hash.getFileHash(Configs.testFileTmpDir + "/" + attestDemo.getChannelOrdersn())));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("文件不存在,无法计算摘要.");
            e.printStackTrace();
        }

        attestDemo.setSourceFileLength(""+JSON.toJSONString(attestDemo).getBytes("utf-8").length);
        System.out.println(JSON.toJSONString(attestDemo));

        //////////////////////生成文件

        //ownerType
        attestDemo.setOwnerType(String.valueOf(rd.nextInt(2) + 1));
        //ownerId
        attestDemo.setOwnerId( "ownerid"+ String.valueOf(rd.nextInt(11) + 1));
        attestDemo.setOwnerName("权属人"+ (UUID.randomUUID().toString()).replaceAll("-","").substring(0,4));
        //agentName
        attestDemo.setAgentName("agent"+(UUID.randomUUID().toString()).replaceAll("-", "").substring(0, 5));
        //agentPhone
        attestDemo.setAgentPhone(String.valueOf(rd.nextInt(9999999) + 1));
        //agentEmail
        String n = (UUID.randomUUID().toString()).replaceAll("-", "").substring(0, 7);
        String addr = (UUID.randomUUID().toString()).replaceAll("-", "").substring(0, 4);
        attestDemo.setAgentEmail(n.concat(addr).concat("@").concat(".com"));

        //duration
        attestDemo.setDuration(String.valueOf(rd.nextInt(5) + 1));

        //description
        attestDemo.setDescription("描述:"+JSON.toJSONString(attestDemo).substring(0,150));

        //price
        attestDemo.setPrice(Configs.price);

        //生成sign/signType
        attestDemo.setSignType(Configs.signType);
        String sign = tool.genSign(attestDemo, Configs.publicKey);
        attestDemo.setSign(sign);

        TempOrder tempOrder = new TempOrder();
        BeanUtils.copyProperties(attestDemo,tempOrder);
        tempOrder.setState("创建订单1");
        tempOrderService.insert(tempOrder);
        log.info("保存临时订单数据.{}", JSONObject.toJSONString(tempOrder));
    }

    /**
     * 自动生成文件
     * @param channelOrdersn 作为文件名
     * @param attestDemo
     * @throws IOException
     */
    public void genFile(String channelOrdersn, AttestDemo attestDemo) throws IOException {
        FileOutputStream fop = null;
        File file;

        try {
            file = new File(Configs.testFileTmpDir ,channelOrdersn);
            fop = new FileOutputStream(file);
            if (!file.exists()) {
                file.createNewFile();
            }
            byte[] contentInBytes = JSON.toJSONString(attestDemo).getBytes();
            fop.write(contentInBytes);
            fop.flush();
            fop.close();
            System.out.println("文件生成成功.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
