package com.zhjl.tech.channel.configs;

import org.springframework.stereotype.Service;

@Service
public class Configs {

    //测试生成文件的保存目录
    public static String testFileTmpDir = "D:\\AttestFile";

    public static String channelId = "001";

    public static String publicKey = "AvzhTMbnUQfQ3sA5kHGyWjucs7MOUTBoGgR5t1coiywUDmvo2c9wv39/tJAefhh7SzpOSVjZ12YxOsKi8ytsNA==";
    public static String accessKey ="8cfLPyeWH4o2SXdMwHd8qDaou8Ta7F7p5HJnvdhm6QCz";
    public static String sourceFileHash = "ywWeiIDC1V60QhRLxp9exH21mVSQLAmcvP2LcbeywyI=";
    public static String signType = "MD5";
    public static String price = "150";


    public static String genUrl ="http://127.0.0.1:8080/inter/inters/fileAttest/0.9";
    public static String uploadUrl ="http://127.0.0.1:8082/store/stores/uploadFileStream/0.9";
    public static String FileHashUrl ="http://127.0.0.1:8080/inters/HashAttest/0.9";
    public static String FileXqUrl ="http://127.0.0.1:8080/inters/AttestContinue/0.9";
    public static int count= 10;

    public static String data_url = "yyyyMMdd:HHmmss";
}
