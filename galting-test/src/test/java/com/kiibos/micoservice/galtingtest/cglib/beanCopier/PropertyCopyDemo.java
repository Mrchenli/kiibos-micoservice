package com.kiibos.micoservice.galtingtest.cglib.beanCopier;

import lombok.Data;
import org.springframework.cglib.beans.BeanCopier;

/**
 * @ClassName PropertyCopyDemo
 * @Description TODO
 * @Author cl
 * @Date 2019/3/20 下午3:18
 **/
public class PropertyCopyDemo {

    public static void main(String[] args) {
        Other other = new Other();
        other.setUsername("test");
        other.setPassword("123");
        Myth myth=new Myth();
        System.out.println(other);
        System.out.println(myth);

        BeanCopier copier = BeanCopier.create(Other.class,
                Myth.class,false);
        copier.copy(other,myth,null);
        System.out.println(other);
        System.out.println(myth);
    }

}

@Data
class Other {
    private String username;
    private String password;
    private int age;
}

@Data
class Myth {
    private String username;
    private String password;
    private String remark;
}