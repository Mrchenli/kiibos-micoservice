package com.kiibos.micoservice.kiibos_8_jvm.stringIntern;

/**
 * @ClassName InternTest
 * @Description TODO
 * @Author cl
 * @Date 2019/3/5 下午1:49
 **/
public class InternTest {
    /**
     * @Author kiibos
     * @Description
     * JDK 1.7 后的intern方法在实现上发生了比较大的改变，JDK 1.7后，
     * intern方法还是会先去查询常量池中是否有已经存在，如果存在，则返回常量池中的引用，
     * 这一点与之前没有区别，区别在于，
     * 如果在常量池找不到对应的字符串，则不会再将字符串拷贝到常量池，
     * 而只是在常量池中生成一个对原字符串的引用。
     * @Date 下午1:56 2019/3/5
     * @param args
     * @return void
     **/
    public static void main(String[] args) {
        String str2 = new String("str")+new String("01");
        str2.intern();//常量池找不到 在常量池产生一个对它的应用
        String str1 = "str01";
        System.out.println(str2==str1);
    }

}
