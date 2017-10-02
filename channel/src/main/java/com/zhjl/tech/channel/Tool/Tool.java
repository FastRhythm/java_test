package com.zhjl.tech.channel.Tool;

import com.zhjl.tech.channel.dto.AttestDemo;
import com.zhjl.tech.common.dto.AttestXqDTO;
import com.zhjl.tech.common.encrypt.digest.Hash;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Slf4j
@Service
public class Tool {

    /**
     * 生成请求sign
     */
    public String createUploadFileSign(String ordersn,String fileToken,String random,String accesskey ,String publicKey){
        Map<String,String> parameters = new HashMap<>();
        parameters.put( "random",random);
        parameters.put( "accessKey",accesskey);
        parameters.put( "ordersn",ordersn);
        parameters.put( "fileToken",fileToken);

        List<String> arrayList = new ArrayList<>(parameters.keySet());
        Collections.sort( arrayList );

        String fianlStr = "";
        for( String s :arrayList){
            fianlStr = fianlStr + "&" + s + "=" + parameters.get(s);
        }
        fianlStr = fianlStr + "&publicKey=" + publicKey;

        System.out.println("finalstr = " + fianlStr );

        String sign = null;
        try {
            sign = Base64.getEncoder().encodeToString(new Hash().getHashMD5(fianlStr.getBytes("utf-8")));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("sign = " + sign );

        return sign;
    }



    /**
     * 校验存证请求sign
     *
     * @param attestDemo
     */
    public String genSign(AttestDemo attestDemo, String publicKey) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("requestTime", attestDemo.getRequestTime());
        parameters.put("channelId", attestDemo.getChannelId());
        parameters.put("channelUserid", attestDemo.getChannelUserid());
        parameters.put("channelOrdersn", attestDemo.getChannelOrdersn());
        parameters.put("chained", attestDemo.getChained());
        parameters.put("bizType", attestDemo.getBizType());

        parameters.put("sourceFileName", attestDemo.getSourceFileName());
        parameters.put("sourceFileType", attestDemo.getSourceFileName());
        parameters.put("sourceFileLength", attestDemo.getSourceFileLength());
        parameters.put("sourceFileHash", attestDemo.getSourceFileType());

        parameters.put("ownerId", attestDemo.getOwnerType());
        parameters.put("ownerType", attestDemo.getOwnerType());
        parameters.put("ownerName", attestDemo.getOwnerName());

        parameters.put("agentName", attestDemo.getAgentName());
        parameters.put("agentPhone", attestDemo.getAgentPhone());
        parameters.put("agentEmail", attestDemo.getAgentEmail());

        parameters.put("duration", attestDemo.getDuration());
        parameters.put("description", attestDemo.getDescription());
        parameters.put("price", attestDemo.getPrice());
        parameters.put("accessKey", attestDemo.getAccessKey());

        List<String> arrayList = new ArrayList<>(parameters.keySet());
        Collections.sort(arrayList);

        String fianlStr = "";
        StringBuilder sb = new StringBuilder();
        for (String s : arrayList) {
            sb.append("&").append(s).append("=").append(parameters.get(s));
        }
        sb.append("&publicKey=").append(publicKey);
        fianlStr = sb.toString();

        log.info("finalstr = {}", fianlStr);

        String sign = null;
        try {
            byte[] bs = fianlStr.getBytes("utf-8");
            sign = Base64.getEncoder().encodeToString(new Hash().getHashMD5(bs));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        log.info("sign = {}", sign);

        return sign;
    }


    /**
     * 续期请求sign
     *
     * @param attestXqDTO
     */
    public String genXqSign(AttestXqDTO attestXqDTO, String publicKey) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("requestTime", attestXqDTO.getRequestTime());
        parameters.put("channelId", attestXqDTO.getChannelId());
        parameters.put("channelUserid", attestXqDTO.getChannelUserid());
        parameters.put("channelOrdersn", attestXqDTO.getChannelOrdersn());
        parameters.put("originalOrdersn", attestXqDTO.getOriginalOrdersn());
        parameters.put("chained", attestXqDTO.getChained());
        parameters.put("bizType", attestXqDTO.getBizType());

        parameters.put("sourceFileName", attestXqDTO.getSourceFileName());
        parameters.put("sourceFileType", attestXqDTO.getSourceFileType());
        parameters.put("sourceFileLength", attestXqDTO.getSourceFileLength());
        parameters.put("sourceFileHash", attestXqDTO.getSourceFileHash());

        parameters.put("ownerId", attestXqDTO.getOwnerId());
        parameters.put("ownerType", attestXqDTO.getOwnerType());
        parameters.put("ownerName", attestXqDTO.getOwnerName());

        parameters.put("agentName", attestXqDTO.getAgentName());
        parameters.put("agentPhone", attestXqDTO.getAgentPhone());
        parameters.put("agentEmail", attestXqDTO.getAgentEmail());

        parameters.put("duration", attestXqDTO.getDuration());
        parameters.put("description", attestXqDTO.getDescription());
        parameters.put("price", attestXqDTO.getPrice());
        parameters.put("accessKey", attestXqDTO.getAccessKey());

        List<String> arrayList = new ArrayList<>(parameters.keySet());
        Collections.sort(arrayList);

        String fianlStr = "";
        StringBuilder sb = new StringBuilder();
        for (String s : arrayList) {
            sb.append("&").append(s).append("=").append(parameters.get(s));
        }
        sb.append("&publicKey=").append(publicKey);
        fianlStr = sb.toString();

        log.info("finalstr = {}", fianlStr);

        String sign = null;
        try {
            byte[] bs = fianlStr.getBytes("utf-8");
            sign = Base64.getEncoder().encodeToString(new Hash().getHashMD5(bs));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        log.info("sign = {}", sign);

        return sign;
    }
}
