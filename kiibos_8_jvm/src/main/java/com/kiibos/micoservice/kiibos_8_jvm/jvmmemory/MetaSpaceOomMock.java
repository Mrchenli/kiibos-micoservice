package com.kiibos.micoservice.kiibos_8_jvm.jvmmemory;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MetaSpaceOomMock
 * @Description TODO
 * @Author cl
 * @Date 2019/3/5 下午2:21
 **/
public class MetaSpaceOomMock {

    public static void main(String[] args) {
        URL url = null;
        List<ClassLoader> classLoaderList = new ArrayList<>();
        try{
            url = new File("/Users/cl/ideaProjects/kiibos-micoservice/kiibos_8_jvm/src/main/java/com/kiibos/micoservice/kiibos_8_jvm/jvmmemory").toURI().toURL();
            System.out.println("url ==>"+url);
            URL[] urls = {url};
            while (true){
                ClassLoader loader = new URLClassLoader(urls);
                classLoaderList.add(loader);
                Class clz= loader.loadClass("com.kiibos.micoservice.kiibos_8_jvm.jvmmemory.StackErrorMock");
                System.out.println(clz.getSimpleName());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }




}
