package com.java.test;


import java.io.*;

public class save_reader_object {

    public static void main(String[] args) {
//        Attest attest = new Attest();
//        Calendar cd = Calendar.getInstance();//取得Calendar实例
//        Date requestTime = cd.getTime();//取得当前时间
//        cd.set(2011,11,11);
//        Date originTime = cd.getTime();
//        attest.setRequestTime(requestTime);
//        attest.setOriginTime(originTime);
//        cd.set(2015,12,11);
//        Date startTime = cd.getTime();
//        attest.setStartTime(startTime);
//        attest.setSign("2221100123123219");
//        attest.setAccessKey("123456123");
//        attest.setRandom("111111111");
//        attest.setChannelUserid("001");
//        attest.setChannelOrdersn("211");
//        attest.setPublicKey("88888888888888888888");
//        attest.setVersion("zhjl-1.0");
//        attest.setAttestSign("010101010101010101010");
//        attest.setAttestType("1");
//        attest.setSourchFilehash("64654213321sadkfjasd");
//        attest.setChained("0");
//        attest.setWalletAddr("wfwedsfsadf");
//        attest.setBizType("1");
//        attest.setChannelId("QQ");
//        attest.setProvinderId("22");
//        attest.setFileName("帝国大业");
//        attest.setFileSize("1000");
//        attest.setFileType("文件类型");
//        attest.setEncrypted("0");
//        attest.setAgentName("代理人张三");
//        attest.setAgentPhone("110");
//        attest.setAgentEmail("zhangsan@haha.com");
//        attest.setOwnerId("1111232");
//        attest.setOwnerName("李四1111");
//        attest.setOwnerPhone("1220");
//        attest.setOwnerType("2");
//        attest.setOwnerEmail("lisi@haha.com");
//        attest.setDuration("50");
//        attest.setDescLength("5");
//        attest.setDescription("这是存证信息描述");
//
//        FileOutputStream fos = null;
//        ObjectOutputStream oos = null;
//        File f = new File("D://y.txt");
//        try {
//            fos = new FileOutputStream(f);
//            oos = new ObjectOutputStream(fos);
//            oos.writeObject(attest);    //括号内参数为要保存java对象
//            System.out.println();
//            int length=3434;
//            long xxcx=2342432;
//            String hash = "";
//
//            // length --- 4byte
//            byte l1 =(byte)(( length & (0xff << 24))>>24);
//
//
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally{
//            try {
//                oos.close();
//                fos.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//

    save_reader_object t= new save_reader_object();
    t.save();
    }

    private void save(){
       A a = new A();
       a.setId(1);
       a.setName("张三");
       //写入
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("D:/a.txt"));
            os.writeObject(a);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //读取
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("D:/a.txt"));
            try {
                A temp = (A) is.readObject();
                System.out.print(temp.getId());
                System.out.print(temp.getName());
                is.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
