package com.kiibos.micoservice.kiibos_8_jvm.stringIntern;

/**
 * @ClassName StringTest
 * @Description TODO
 * @Author cl
 * @Date 2019/3/5 下午1:29
 **/
public class StringTest {
    public static String name = "1111";


    public static void main(String[] args) {
        String str1 = "String";
        String str2 = new String("String");
        String str3 = str2.intern();
        System.out.println(str1 == str2);
        System.out.println(str1 == str3);
        System.out.println(name==Demo.name);
    }



    private static class Demo{
        public static String name = "1111";
    }

}
