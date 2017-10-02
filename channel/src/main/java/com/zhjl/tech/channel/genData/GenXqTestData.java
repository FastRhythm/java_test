package com.zhjl.tech.channel.genData;

import com.alibaba.fastjson.JSON;
import com.zhjl.tech.channel.Tool.Tool;
import com.zhjl.tech.channel.service.channel.ITempOrderService;
import com.zhjl.tech.channel.model.channel.TempOrder;
import com.zhjl.tech.channel.configs.Configs;
import com.zhjl.tech.channel.dto.AttestDemo;
import com.zhjl.tech.common.dto.AttestXqDTO;
import com.zhjl.tech.common.encrypt.digest.Hash;
import lombok.extern.slf4j.Slf4j;
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
public class GenXqTestData {

    @Resource
    ITempOrderService tempOrderService;
    /**
     * 自动生成数据
     * @param path 临时文件保存目录
     * @return
     * @throws IOException
     */
    public AttestXqDTO gen(String path) throws IOException {
        Configs configs = new Configs();
        AttestXqDTO attestXqDTO = new AttestXqDTO();
        Random rd = new Random();

        //请求参数
        attestXqDTO.setAccessKey(configs.accessKey);
        attestXqDTO.setRandom( UUID.randomUUID().toString().replaceAll("-",""));
        attestXqDTO.setChannelId(configs.channelId);

        //生成请求时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String s = simpleDateFormat.format(date);
        attestXqDTO.setRequestTime(s);
        //生成chained
        attestXqDTO.setChained(String.valueOf(rd.nextInt(2) + 1));
        //生成BizType
        attestXqDTO.setBizType(String.valueOf(rd.nextInt(6) + 1));
        //sourceFileName
        attestXqDTO.setSourceFileName("file" + genChannelOrdersn());
        //sourceFileType
        attestXqDTO.setSourceFileType(String.valueOf(rd.nextInt(4) + 1));
        //agentName
        attestXqDTO.setAgentName((UUID.randomUUID().toString()).replaceAll("-", "").substring(0, 5));
        //agentPhone
        attestXqDTO.setAgentPhone(String.valueOf(rd.nextInt(4) + 1));
        //agentEmail
        StringBuffer sb = new StringBuffer();
        String n = (UUID.randomUUID().toString()).replaceAll("-", "").substring(0, 7);
        String addr = (UUID.randomUUID().toString()).replaceAll("-", "").substring(0, 4);
        String com = ".com";
        attestXqDTO.setAgentEmail(n.concat(addr).concat("@").concat(com));
        //ownerName
//        attestXqDTO.setOwnerName((UUID.randomUUID().toString()).replaceAll("-", "").substring(0, 5));
        String title = (UUID.randomUUID().toString()).replaceAll("-","").substring(0,4);
        attestXqDTO.setOwnerName("测试人员"+ title);
        //ownerId
        attestXqDTO.setOwnerId(String.valueOf(rd.nextInt(18) + 1));
        //ownerType
        attestXqDTO.setOwnerType(String.valueOf(rd.nextInt(2) + 1));
        //duration
        attestXqDTO.setDuration(String.valueOf(rd.nextInt(5) + 1));
        //description
        attestXqDTO.setDescription(genChannelOrdersn() + JSON.toJSONString(attestXqDTO));
        //price
        attestXqDTO.setPrice(configs.price);
        //channelOrdersn
        attestXqDTO.setChannelOrdersn(genChannelOrdersn());
        //channelUserid
        attestXqDTO.setChannelUserid(genChannelUserid());

        //生成文件
        GenXqTestData genTestData = new GenXqTestData();
        genTestData.genFile(attestXqDTO.getChannelOrdersn(),attestXqDTO,path);

        Hash hash = new Hash();
        try {
            attestXqDTO.setSourceFileHash(Base64.getEncoder().encodeToString(hash.getFileHash(path + "/" + attestXqDTO.getChannelOrdersn())));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("文件不存在,无法计算摘要.");
            e.printStackTrace();
        }

        attestXqDTO.setSourceFileLength(""+JSON.toJSONString(attestXqDTO).getBytes("utf-8").length);
        System.out.println(JSON.toJSONString(attestXqDTO));

        //生成sign/signType
        attestXqDTO.setSignType(configs.signType);
        Tool tool = new Tool();
        String sign = tool.genXqSign(attestXqDTO,configs.publicKey);
        attestXqDTO.setSign(sign);

        TempOrder tempOrder = new TempOrder();
        tempOrder.setState("保存订单数据.");
        tempOrderService.insert(tempOrder);
        log.info("保存临时订单数据.");
        return attestXqDTO;
    }

    /**
     * 生成channelUserid
     * @return
     */
    public String genChannelUserid() {
        AttestDemo attestDemo = new AttestDemo();
        String channelUserid = (UUID.randomUUID().toString()).replaceAll("-", "").substring(0, 4);
        attestDemo.setChannelUserid(channelUserid);
        return channelUserid;
    }

    /**
     * 自动生成channelOrdersn
     * @return
     */
    public String genChannelOrdersn() {
        AttestDemo attestDemo = new AttestDemo();
        String channelOrdersn = "";
        for (int i = 0; i < 16; i++) {
            channelOrdersn += String.valueOf((int) (10 * (Math.random())));
        }
        attestDemo.setChannelOrdersn(channelOrdersn);
        return channelOrdersn;
    }

    /**
     * 自动生成文件
     * @param channelOrdersn
     * @param attestXqDTO
     * @param path
     * @throws IOException
     */
    public void genFile(String channelOrdersn, AttestXqDTO attestXqDTO, String path) throws IOException {
        FileOutputStream fop = null;
        File file;
        File file1 = new File(path);
        if (file1.exists()) {
            //判断file1是否为一个目录
            if (file1.isDirectory()) {
                System.out.println("是一个目录");
            } else {
                System.out.println("不是一个目录");
            }
        } else {
            System.out.println("未找到目录,重新创建一个新目录.");
            file1.mkdir();
        }
        try {
            file = new File(path,channelOrdersn);
            fop = new FileOutputStream(file);
            if (!file.exists()) {
                file.createNewFile();
            }
            byte[] contentInBytes = JSON.toJSONString(attestXqDTO).getBytes();
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

    /**
     * 插入多条数据
     */
    public void add(){
    }
}
