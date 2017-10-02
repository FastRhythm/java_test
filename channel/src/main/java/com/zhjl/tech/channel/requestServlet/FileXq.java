package com.zhjl.tech.channel.requestServlet;

import com.zhjl.tech.common.dto.AttestXqDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
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
public class FileXq {

    public void fileXq(String URL, AttestXqDTO attestXqDTO){
        URI url = null;
        try {
            url = new URIBuilder(URL).
                    setParameter("signType", attestXqDTO.getSignType()).
                    setParameter("sign", attestXqDTO.getSign()).
                    setParameter("accessKey", attestXqDTO.getAccessKey()).
                    setParameter("random", attestXqDTO.getRandom()).

                    setParameter("requestTime", attestXqDTO.getRequestTime()).
                    setParameter("channelUserid", attestXqDTO.getChannelUserid()).
                    setParameter("channelOrdersn", attestXqDTO.getChannelOrdersn()).
                    setParameter("originalOrdersn", attestXqDTO.getOriginalOrdersn()).
                    setParameter("chained", attestXqDTO.getChained()).
                    setParameter("bizType", attestXqDTO.getBizType()).
                    setParameter("channelId", attestXqDTO.getChannelId()).

                    setParameter("sourceFileHash", attestXqDTO.getSourceFileHash()).
                    setParameter("sourceFileName", attestXqDTO.getSourceFileName()).
                    setParameter("sourceFileType", attestXqDTO.getSourceFileType()).
                    setParameter("sourceFileLength", attestXqDTO.getSourceFileLength()).

                    setParameter("agentName", attestXqDTO.getAgentName()).
                    setParameter("agentPhone", attestXqDTO.getAgentPhone()).
                    setParameter("agentEmail", attestXqDTO.getAgentEmail()).

                    setParameter("ownerId", attestXqDTO.getOwnerId()).
                    setParameter("ownerName", attestXqDTO.getOwnerName()).
                    setParameter("ownerType", attestXqDTO.getOwnerType()).

                    setParameter("duration", attestXqDTO.getDuration()).
                    setParameter("description", attestXqDTO.getDescription()).
                    setParameter("price", attestXqDTO.getPrice()).
                    build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost request = new HttpPost(url);
            HttpResponse response = httpclient.execute(request);
            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回数据**/
                String strResult = EntityUtils.toString(response.getEntity(), "utf-8");
                log.info("存证续期请求内容 : " +strResult);
            } else {
                log.info("续期失败");
                return;
            }
                httpclient.close();
            } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
