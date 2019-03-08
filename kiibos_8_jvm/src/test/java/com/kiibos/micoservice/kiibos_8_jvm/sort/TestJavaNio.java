package com.kiibos.micoservice.kiibos_8_jvm.sort;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Stack;

/**
 * @ClassName TestJavaNio
 * @Description TODO
 * @Author cl
 * @Date 2019/3/6 下午8:01
 **/
public class TestJavaNio {


    static void testReadAndWriteNIO() throws IOException {
        String pathName = "/Users/cl/ideaProjects/kiibos-micoservice/kiibos_8_jvm/src/main/resources/StringTest01.txt";
        FileInputStream fin = null;
        String fileName = "test-out.txt";

        FileOutputStream fos = null;
        try {
            fin = new FileInputStream(new File(pathName));
            FileChannel channel = fin.getChannel();

            int capacity = 100;//字节
            ByteBuffer bf = ByteBuffer.allocateDirect(capacity);
            System.out.println("限制是：" + bf.limit() + "容量是：" + bf.capacity()+ "位置是：" + bf.position());
            int length = -1;
            fos = new FileOutputStream(new File(fileName));
            FileChannel outchannel = fos.getChannel();

            while (-1!=(length=channel.read(bf))){
                //将当前位置设置为limit,然后当前位置设置为0
                bf.flip();
                int outlength =0;
                while (0!=(outlength=outchannel.write(bf))){
                    System.out.println("读，"+length+"写,"+outlength);
                }
                //将当前位置设置为0 limit为容量
                bf.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            fin.close();
            fos.close();
        }
    }


    public static void main(String[] args) throws IOException {
        HashMap map = new HashMap();
        testReadAndWriteNIO();
    }


}
