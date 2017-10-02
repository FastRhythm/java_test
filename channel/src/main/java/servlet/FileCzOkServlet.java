package servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 源文件存证成功 servlet
 */
@Slf4j
@WebServlet(name = "fileCzOk", urlPatterns = "/fileCzOk")
public class FileCzOkServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ordersn = request.getParameter("ordersn");
        String channelOrdersn = request.getParameter("chennelOrdersn");
        String startTime = request.getParameter("startTimeDate");
        String duration = request.getParameter("duration");


        log.info("文件存证成功.收到的参数信息:"+
                "startTime" + startTime +
                "ordersn=" + ordersn +
                "channelOrdersn=" + channelOrdersn +
                "duration=" + duration
        );
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
