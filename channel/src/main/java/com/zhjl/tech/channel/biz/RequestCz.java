package com.zhjl.tech.channel.biz;

import com.zhjl.tech.channel.configs.Configs;
import com.zhjl.tech.channel.genData.GenTestData;
import com.zhjl.tech.channel.genData.GenXqTestData;
import com.zhjl.tech.channel.requestServlet.FileCz;
import com.zhjl.tech.channel.requestServlet.FileXq;
import com.zhjl.tech.channel.requestServlet.HashCz;
import com.zhjl.tech.common.dto.AttestXqDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

@Slf4j
@Service
public class RequestCz {
    Configs configs = new Configs();

    @Resource
    GenTestData genTestData;

    @Resource
    GenXqTestData genXqTestData;

    @Resource
    FileCz fileCz;

    @Resource
    HashCz hashCz;

    @Resource
    FileXq fileXq;

    /**
     * Hash存证
     * @param genUrl
     */
    public void fileHashCz(String genUrl) throws IOException {
        genTestData.gen(configs.testFileTmpDir);  //生成参数信息

        hashCz.hash(genUrl, null);
    }

    /**
     * 文件续期存证
     * @param genUrl
     */
    public void XqCz(String genUrl) throws IOException {
        AttestXqDTO attestXqDTO = genXqTestData.gen(configs.testFileTmpDir);
        genTestData.gen(configs.testFileTmpDir);  //生成参数信息

        fileXq.fileXq(genUrl,attestXqDTO);
    }


}
