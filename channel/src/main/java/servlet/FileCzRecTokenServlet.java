package servlet;

import com.zhjl.tech.channel.model.channel.TempOrder;
import com.zhjl.tech.channel.service.channel.ITempOrderService;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 接收filetoken  servlet
 */
@Slf4j
@WebServlet(name = "fileCzRecToken", urlPatterns = "/fileCzRecToken")
public class FileCzRecTokenServlet extends HttpServlet {

    @Resource
    ITempOrderService tempOrderService;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //源文件存证请求
        log.info("文件接收完成.收到请求信息...");
        String signType = request.getParameter("signType");
        String sign = request.getParameter("sign");
        String random = request.getParameter("random");
        String ordersn = request.getParameter("ordersn");
        String channelOrdersn = request.getParameter("channelOrdersn");
        String fileToken = request.getParameter("fileToken");

        TempOrder tempOrder = tempOrderService.getTempOrderByChannelOrdersn(channelOrdersn);
        tempOrder.setState("接收fileToken完成.");
        tempOrderService.updateById(tempOrder);
        log.info("收到的请求信息:signType="+signType+"&sign"+sign+"&random="+random+"&ordersn="+ordersn+"&channelOrdersn="+channelOrdersn+"&filetoken="+fileToken);
        log.info("sign:{}",sign);

        //上传源文件
//        String Random = (UUID.randomUUID().toString()).replaceAll("-","");
//        String Sign = tool.createUploadFileSign(ordersn,fileToken,Random,configs.publicKey,configs.accessKey);   //生成sign
//        String uploadPath = configs.testFileTmpDir +"/"+channelOrdersn;
//        fileUpload.uploadFile(configs.uploadUrl,signType,Sign,configs.publicKey,Random,ordersn,fileToken,uploadPath);
//        log.info("发送信息:signType={},sign={},publickey={},random={},ordersn={},fileToken={},testFileTmpDir={}",signType,configs.publicKey,Random,ordersn,fileToken,uploadPath);
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
        log.info("get收到请求信息...");
    }

}
