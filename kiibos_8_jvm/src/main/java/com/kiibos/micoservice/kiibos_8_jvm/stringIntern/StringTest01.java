package com.kiibos.micoservice.kiibos_8_jvm.stringIntern;

/**
 * @ClassName StringTest01
 * @Description TODO
 * @Author cl
 * @Date 2019/3/5 下午1:31
 **/
public class StringTest01 {


    private String demo;
    private static String hello  ="hello";

    public synchronized void test(String name){

    }

    public static void main(String[] args) {
        String baseStr = "baseStr";
        final  String baseFinalStr = "baseStr";

        String str1 = "baseStr01";
        String str2 = "baseStr"+"01";//其实在JAVA 1.6之后，常量字符串的“+”操作，编译阶段直接会合成为一个字符串。
        String str3 = baseStr + "01";//stringBuilder.append()
        String str4 = baseFinalStr+"01";//对于final字段，编译期直接进行了常量替换，而对于非final字段则是在运行期进行赋值处理的。
        String str5 = new String("baseStr01").intern();

        System.out.println(str1==str2);
        System.out.println(str1==str3);
        System.out.println(str1==str4);
        System.out.println(str1==str5);

        StringTest01 test01 =new StringTest01();
        test01.demo = "";
    }


}
