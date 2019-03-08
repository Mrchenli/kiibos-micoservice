package com.kiibos.micoservice.kiibos_8_jvm.stringIntern;

/**
 * @ClassName InternTest01
 * @Description TODO
 * @Author cl
 * @Date 2019/3/5 下午1:51
 **/
public class InternTest01 {

    public static void main(String[] args) {
        String str1 = "str01";
        String str2 = new String("str")+new String("01");
        String str3 = str2.intern();
        System.out.println(str2==str1);
        System.out.println(str1==str3);
    }

}
