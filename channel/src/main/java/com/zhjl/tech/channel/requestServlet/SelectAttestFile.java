package com.zhjl.tech.channel.requestServlet;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class SelectAttestFile {

    /**
     * 源文件存证HTTP请求
     * @param URL
     * @return
     */
    public void select(String URL,String ordersn){
        URI url = null;
        try {
            url = new URIBuilder(URL).
                    setParameter("ordersn",ordersn).build();
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost request = new HttpPost(url);
            HttpResponse response = httpclient.execute(request);
            /**请求发送成功，并得到响应**/
            System.out.println("状态码："+response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() == 200) {
                /**读取服务器返回数据**/
                String strResult = EntityUtils.toString(response.getEntity(),"utf-8");
                System.out.println("响应的内容 : "+ strResult);

            } else {
                System.out.println("请求未响应。");;
            }
            httpclient.close();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            System.out.print("数据传输异常");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
