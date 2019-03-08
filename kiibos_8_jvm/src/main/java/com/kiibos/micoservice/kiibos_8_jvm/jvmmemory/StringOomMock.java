package com.kiibos.micoservice.kiibos_8_jvm.jvmmemory;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName StringOomMock
 * @Description TODO
 * @Author cl
 * @Date 2019/3/5 下午2:31
 **/
public class StringOomMock {

    static String base = "string";

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i=0;i<Integer.MAX_VALUE;i++){
            String str = base + base;
            base = str;
            list.add(str.intern());//常量池里面加
        }
    }


}
