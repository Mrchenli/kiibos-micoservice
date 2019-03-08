package com.kiibos.micoservice.kiibos_8_jvm.jvmmemory;

/**
 * @ClassName MemoryModel
 * @Description TODO
 * @Author cl
 * @Date 2019/3/5 下午2:05
 **/
public class MemoryModel {

    /**
     * @Author kiibos
     * @Description //
     *
     * 本地方法栈
     * 虚拟机栈
     * pc寄存器
     * 方法区
     * 堆
     * 32位pc
     * 0                                        2G                                                       4G
     *【内核内存】【jvm代码段】【jvm数据段】【jvm堆【新生代】【老年代】】【metaspace】【directBuffer】【thread stack】
     * @Date 下午2:06 2019/3/5
     * @param
     * @return
     **/


    /**
     * @Author kiibos
     * @Description //
     *
     * 1.符号引用 代码 symbols （native heap）
     * 2.class statics（heap）
     * 3.字面量 "abc" （heap）
     * 4.对象 object (heap)
     * @Date 下午2:19 2019/3/5
     * @param
     * @return
     **/

}
