package com.zhjl.tech.channel.test;

import com.zhjl.tech.channel.base.BaseTest;
import com.zhjl.tech.channel.biz.RequestCz;
import com.zhjl.tech.channel.service.channel.ITempOrderService;
import com.zhjl.tech.channel.model.channel.TempOrder;
import com.zhjl.tech.channel.configs.Configs;
import com.zhjl.tech.channel.dto.AttestDemo;
import com.zhjl.tech.channel.genData.GenTestData;
import com.zhjl.tech.channel.requestServlet.FileCz;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URISyntaxException;

@Slf4j
public class Gen extends BaseTest {

    private static int count = 5;

    @Resource
    ITempOrderService tempOrderService;

    @Resource
    Configs configs;

    @Resource
    RequestCz requestCz;

    @Resource
    GenTestData genTestData;

    @Resource
    FileCz fileCz;

    @Test
    public void genData() throws IOException {

        for(int i=0;i< count;i++) { genTestData.gen(Configs.testFileTmpDir);  //生成参数信息
        }
        log.info("数据生成完毕!");

    }

    /**
     * 文件存证请求
     */
    @Test
    public void sendRequest() throws URISyntaxException, IOException, InterruptedException {
        genData();
        for (int i = 0; i < count; i++) {
            TempOrder tempOrder = tempOrderService.getTempOrderByState("创建订单1");
            AttestDemo attestDemo = new AttestDemo();
            BeanUtils.copyProperties(tempOrder, attestDemo);
            tempOrder.setState("开始存证2");
            tempOrderService.updateById(tempOrder);

            int j = i;
            //todo 取得一个带传送的对象
            Thread thread1 = new Thread();
            thread1.start();
            fileCz.fileCz(attestDemo);
//              {
//                  public void run() {
//                      try {
//                          fileCz.fileCz(attestDemo);
//                      } catch (URISyntaxException e) {
//                          e.printStackTrace();
//                      } catch (IOException e) {
//                          e.printStackTrace();
//                      }
//                      System.out.println(j);
//                  }
//              };
//              thread.start();
        }
    }

    class Thread1 extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("Thread-----:" + i);
            }
        }
    }

    /**
     * 文件存证请求
     */
    @Test
    public void Hash() {
        try {
            requestCz.fileHashCz(configs.FileHashUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件存证请求
     */
    @Test
    public void XqCz() {
        try {
            requestCz.XqCz(configs.FileXqUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
