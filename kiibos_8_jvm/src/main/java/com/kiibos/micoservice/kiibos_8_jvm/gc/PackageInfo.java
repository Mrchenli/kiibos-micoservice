package com.kiibos.micoservice.kiibos_8_jvm.gc;

import java.math.BigDecimal;

/**
 * @ClassName PackageInfo
 * @Description
 *
 * 1.分配
 * 2.释放对象
 * 3.压缩治理
 *
 * 程序线程  垃圾搜集线程
 *
 * 衡量指标==>latency + throughput
 * stop-the-world（对象再内存里面的地址会移动 当然要stop the world）
 *
 * 分代垃圾搜集器
 * 【新生代[eden][survivor]】【老年代】
 *
 *
 * @Author cl
 * @Date 2019/3/6 上午11:18
 **/
public class PackageInfo {

    /**
     * @Author kiibos
     * @Description //TODO  Minor GC
     * 当新生代被填满时候 垃圾搜集器会暂停所有的应用线程，回收新生代空间，不再使用的对象会被回收 仍然使用的对象被移动到其他地方。
     * minor gc 处理的是新生代 latency时间更短
     * 新生代的对象分配方式。 对象分配于Eden空间，垃圾收集时候eden被清空，其中存活的对象 要么被移走到survivor空间 要么被移动到老年代
     *
     * @Date 上午11:35 2019/3/6
     * @param
     * @return
     **/


    /**
     * @Author kiibos
     * @Description //TODO Full GC
     *
     *  简单的垃圾收集算法
     *  直接停掉所有的应用线程，找出不再使用的对象，对其进行回收，接着对堆空间进行整理
     *  这个过程叫 Full GC,通常导致应用程序线程长时间停顿。
     *
     *  另一方面，通过更复杂的计算，我们还有可能在应用线程运行的同时找出不再使用的对象；
     *  CMS和G1搜集器就是通过这种方式进行垃圾收集的。优势是低延迟
     *  代价是 消耗更多的cpu降低了吞吐率(吞吐率可以从进程上面来提升)
     *
     *
     *
     *  serial 垃圾收集器 -XX:+UseSerialGC 当线程清理堆  <100M
     *
     *  throughput（parallel）jdk1.7之后默认的收集器(cpu少这个可能会好点)
     *  停掉所有应用线程--> 找出无效对象 --> 回收内存 -->压缩整理
     *  cms
     *  不停掉应用程序-->找出无效对象-->回收 --->没有压缩整理
     *  -->等到过度碎片化的时候或者获取不到cpu时候-->退化到serial-gc
     *
     *  G1 尽量缩短处理超大堆(大于4GB)时产生的停顿。
     *  老年代划分不同的区域 可以压缩整理 大部分工作是不停掉应用线程的
     *
     * @Date 上午11:47 2019/3/6
     * @param
     * @return
     **/


    
    /**
     * @Author kiibos
     * @Description //TODO GC调优基础
     * todo 1.确定堆的空间大小
     *  a.jvm会根据它运行的机器，尝试估算合适的最大最小堆
     *  b.如果应用程序需要比默认值更大的堆，那么
     *  一个经验法则是完成Full GC后 ，应该释放出70%的空间(30%空间仍然占用)。
     *  为了衡量这个结果，你可以持续运行应用程序，直到其到达稳定态配置：
     *  这时它已经载入了需要缓存的所有对象，或者已经创建了最多的客户端连接数，等
     *  之后，使用jconsole连接应用程序，强制进行Full GC,观察Full GC结束后还有
     *  多少内存被占用(此外，对于Throughput垃圾收集器，如果有日志的话，你可以通过
     *  查询gc日志得到对应的数据)
     *
     *  -Xms2048m -Xmx2048m
     *
     * todo 2. 代空间的调整
     *  新生代 -->大 minor gc频率就低 但是老年代就相对小 full gc会频繁
     *  -XX:NewRatio = N  默认是2 ==> young gen size = initial heapsize/(1+new Ratio)
     *  设置新生代和老年代的空间占用比例
     *  -XX:NewSize = N ==>优先级比NewRatio计算的高
     *  设置新生代空间的初始大小
     *  -XX:MaxNewSize=N
     *  设置新生代空间的最大大小
     *  -XmnN
     *  将NewSize和MaxNewSize设置为同一个值的快捷方法。
     *
     *  metaspace：
     *  -XX:MetaspaceSize=N 默认20.75M
     *  -XX:MaxMetaspaceSize=N 无限制
     *
     * gc
     *  -XX:+UseParallelGC
     *  -XX:+UseParallelOldGC
     *
     *
     *
     *
     *
     * @Date 下午12:26 2019/3/6
     * @param 
     * @return 
     **/
    
    
    
    
    
    

}
