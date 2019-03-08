package com.kiibos.micoservice.kiibos_8_jvm.sort;

import java.math.BigDecimal;

/**
 * @ClassName BubbleSort
 * @Description 冒泡排序
 * @Author cl
 * @Date 2019/3/6 下午5:21
 **/
public class BubbleSort {

    /**
     * @Author kiibos
     * @Description // 排序
     * @Date 下午5:45 2019/3/6
     * @param [target]
     * @return void
     **/
    public void sort(int[] target){
        if(target==null||target.length==0) return;
        for (int i=0;i<target.length-1;i++){//要比多少次
            boolean sorted = true;
            for (int j=0;j<target.length-1-i;j++){//每次比都是从头开始往后比
                if(target[j]>target[j+1]){//大的完后排
                    swap(target,j,j+1);
                    sorted = false;
                }
            }
            if(sorted) return;
        }
    }

    /**
     * @Author kiibos
     * @Description //交换数组中2个位置的数据
     * @Date 下午5:45 2019/3/6
     * @param [target, i, j]
     * @return void
     **/
    public void swap(int[] target,int i,int j){
        int tmp = target[i];
        target[i]=target[j];
        target[j]=tmp;

    }

    public static void main(String[] args) {
        Double dou = Double.parseDouble("1000.00");
        Long cent = Long.parseLong("1000");
        String dd = changeY2F(1000L);
        System.out.println(cent);
        System.out.println(dou);
        System.out.println(dd);
    }

    public static String changeY2F(Long amount){
        return BigDecimal.valueOf(amount).multiply(new BigDecimal(100)).toString();
    }


}
