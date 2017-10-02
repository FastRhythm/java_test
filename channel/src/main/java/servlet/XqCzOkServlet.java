package servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 续期存证servlet
 */
@Slf4j
@WebServlet(name = "xqCzOk", urlPatterns = "/xqCzOk")
public class XqCzOkServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        receiveFileCzResult(request,response);
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }


    public void receiveFileCzResult(HttpServletRequest request,HttpServletResponse response){
        String signType = request.getParameter("signType");
        String sign = request.getParameter("sign");
        String random = request.getParameter("random");
        String ordersn = request.getParameter("ordersn");
        String startTime = request.getParameter("startTimeDate");
        String duration = request.getParameter("duration");

        log.info("存证续期成功.收到的参数信息:signType="+signType+
                "sign="+sign+
                "random="+random+
                "ordersn="+ordersn+
                "startTimeDate="+startTime+
                "duration="+duration
        );
    }
}
