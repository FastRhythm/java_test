package com.zhjl.tech.channel.requestServlet;

import com.zhjl.tech.common.dto.AttestDemo;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Service
@Slf4j
public class HashCz {

    /**
     * Hash存证HTTP请求
     * @param URL
     * @param attestDemo
     * @return
     */
    public AttestDemo hash(String URL, AttestDemo attestDemo){
        URI url = null;
        try {
            url = new URIBuilder(URL).
                    setParameter("signType", attestDemo.getSignType()).
                    setParameter("sign", attestDemo.getSign()).
                    setParameter("accessKey", attestDemo.getAccessKey()).
                    setParameter("random", attestDemo.getRandom()).
                    setParameter("requestTime", attestDemo.getRequestTime()).
                    setParameter("channelUserid", attestDemo.getChannelUserid()).
                    setParameter("channelOrdersn", attestDemo.getChannelOrdersn()).
                    setParameter("sourceFileHash", attestDemo.getSourceFileHash()).
                    setParameter("chained", attestDemo.getChained()).
                    setParameter("bizType", attestDemo.getBizType()).
                    setParameter("channelId", attestDemo.getChannelId()).
                    setParameter("sourceFileName", attestDemo.getSourceFileName()).
                    setParameter("sourceFileType", attestDemo.getSourceFileType()).
                    setParameter("sourceFileLength", attestDemo.getSourceFileLength()).
                    setParameter("agentName", attestDemo.getAgentName()).
                    setParameter("agentPhone", attestDemo.getAgentPhone()).
                    setParameter("agentEmail", attestDemo.getAgentEmail()).
                    setParameter("ownerId", attestDemo.getOwnerId()).
                    setParameter("ownerName", attestDemo.getOwnerName()).
                    setParameter("ownerType", attestDemo.getOwnerType()).
                    setParameter("duration", attestDemo.getDuration()).
                    setParameter("description", attestDemo.getDescription()).
                    setParameter("price", attestDemo.getPrice()).
                    build();

            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost request = new HttpPost(url);
            HttpResponse response = httpclient.execute(request);
            /**请求发送成功，并得到响应**/
            System.out.println("状态码："+response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() == 200) {
                /**读取服务器返回数据**/
                String strResult = EntityUtils.toString(response.getEntity(),"utf-8");
                System.out.println(" 响应的内容 : "+ strResult);
                System.out.println("hash存证成功");
            } else {
                System.out.println("hash存证失败");;
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
            System.out.print("Hash文件数据传输失败.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return attestDemo;
    }
}
