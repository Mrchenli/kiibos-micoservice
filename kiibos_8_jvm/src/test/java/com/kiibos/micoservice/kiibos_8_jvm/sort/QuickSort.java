package com.kiibos.micoservice.kiibos_8_jvm.sort;

import java.util.Arrays;

/**
 * @ClassName QuickSort
 * @Description TODO
 * @Author cl
 * @Date 2019/3/6 下午5:57
 **/
public class QuickSort {

    private final int CUT_OFF = 4;

    public void quickSort(int[] target){
        quickSort(target,0,target.length-1);
    }


    private void quickSort(int[] target,int left,int right){
        if(right-left+1>CUT_OFF){//大于阀值的时候用快排
            //1.选取主元
            int pivot = median3(target,left,right);
            int i=left;
            int j = right-1;
            for (;;){
                while (target[++i]<pivot){}//左边移动
                while (target[--j]>pivot){}//右边移动
                if(i<j){//没有结束就交换
                    swap(target,i,j);
                }else{//结束了就交换
                    break;
                }
            }
            swap(target,i,right-1);//把主元调到正确的位置
            quickSort(target,left,i-1);
            quickSort(target,i+1,right);
        }else{//小于阀值的时候用冒泡排序
            insertSort(target,left,right);
        }
    }

    /**
     * @Author kiibos
     * @Description //选取主元
     * @Date 下午6:07 2019/3/6
     * @param target, left, right
     * @return int
     **/
    private int median3(int[] target,int left,int right){
        int median = (left+right)>>1;
        if(target[left]>target[median]) swap(target,left,median);
        if(target[median]>target[right]) swap(target,median,right);
        if(target[left]>target[right]) swap(target,left,right);
        swap(target,median,right-1);
        return target[right-1];
    }

    /**
     * @Author kiibos
     * @Description //交换
     * @Date 下午6:09 2019/3/6
     * @param
     * @return void
     **/
    private void swap(int[] target,int i,int j){
        if (target==null||target.length<i+1||target.length<j+1)
            throw new IllegalArgumentException();
        int tmp = target[i];
        target[i]=target[j];
        target[j]=tmp;
    }

    public void  insertSort(int[] target,int left,int right){
        for (int p =left+1;p<=right;p++){
            //起一张牌
            int tmp = target[p];
            int i ;
            for (i=p;i>left&&target[i-1]>tmp;i--){
                target[i]= target[i-1];//挪位置
            }
            target[i]= tmp;//落牌
        }
    }


    public static void main(String[] args) {
        int[] data = new int[]{12,1,9,22,19,3,24,9882,7,34,36,91};
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(data);
        System.out.println(Arrays.toString(data));
    }



}
