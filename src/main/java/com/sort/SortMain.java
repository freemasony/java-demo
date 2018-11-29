package com.sort;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhoujian on 2018/11/28.
 */
public class SortMain {
    public static void main(String[] args){
//        BinarySearch();
//        bubbleSort0();
//        bubbleSort1();
//        bubbleSort2();
//        simpleSelectionSort();
//        stockMinMax();
//        shellSort();
        quickSort();
    }


    /**
     * 折半查找
     */
    public static int  BinarySearch(){
        int[] sort={2,4,1,5,86,24,25,45,87,76,34,16,14,49,97,31};
        Arrays.sort(sort);
        System.out.println(Arrays.toString(sort));

        int key=24;

        int low=0,high=sort.length-1,mid=0;

        while(low<=high){
            mid=low+(high-low)*(key-sort[low])/(sort[high]-sort[low]);
//            mid=(low+high)/2;
            if(key<sort[mid])
                high=mid-1;
            else if(key>sort[mid])
                low=mid+1;
            else
                break;
        }
        System.out.println(mid);
        return mid;
    }


    /**
     * 数组位置交换
     * @param sort
     * @param i
     * @param j
     */
    public static void swap(int[] sort,int i,int j){
        int temp=sort[i];
        sort[i]=sort[j];
        sort[j]=temp;
    }


    /**
     * 冒泡排序第一种
     */
    public static void bubbleSort0(){
        int[] sort={2,4,1,5,86,24,25,45,87,76,34,16,14,49,97,31};
        for(int i=0;i<sort.length;i++){
            for (int j = i+1; j <sort.length; j++) {
                if(sort[j]>sort[i]){
                    swap(sort,i,j);
                }
            }
        }
        System.out.println(Arrays.toString(sort));
    }


    /**
     * 冒泡排序第二种
     */
    public static void bubbleSort1(){
//        int[] sort={2,4,1,5,86,24,25,45,87,76,34,16,14,49,97,31};
        int[] sort={2,1,4,5,23,26,36,37,84,88,94};
        for (int i = 0; i < sort.length; i++) {
            for (int j = sort.length-2 ; j >=i; j--) {
                if(sort[j]>sort[j+1]){
                    swap(sort,j,j+1);
                }
            }
        }
        System.out.println(Arrays.toString(sort));
    }


    /**
     * 冒泡排序第三种
     */
    public static void bubbleSort2(){
        int[] sort={2,1,4,5,23,26,36,37,84,88,94};
        AtomicInteger integer=new AtomicInteger(0);
        boolean flag=true;
        for (int i = 0; i < sort.length && flag; i++) {
            flag=false;
            for (int j = sort.length-2;j>=i; j--) {
                integer.incrementAndGet();
                if(sort[j]>sort[j+1]){
                    swap(sort,j,j+1);
                    flag=true;
                }
            }
        }
        System.out.println(integer.get()+":"+Arrays.toString(sort));
    }


    /**
     * 简单选择排序
     */
    public static void simpleSelectionSort(){
        int[] sort={2,4,1,5,86,24,25,45,87,76,34,16,14,49,97,31};
        for (int i = 0; i < sort.length; i++) {
            int min=i;
            for (int j = i+1; j < sort.length; j++) {
                if(sort[min]>sort[j]){
                    min=j;
                }
            }
            if(i!=min){
                swap(sort,i,min);
            }
        }
        System.out.println(Arrays.toString(sort));
    }


    /**
     * 股票最优选择，找出最小买入点和最大卖出点
     * 买入点的日期<卖出点的日期
     */
    public static void stockMinMax(){
        int[] stock={47,23,26,16,18,20,21,25,22,19,35,14,33,27};
        int min=0,max=0,indexMin=0,indexMax=0;

        for (int i = 0; i < stock.length; i++) {
            for (int j = i+1; j < stock.length; j++) {
                 if(max<stock[j]-stock[i]){
                     max=stock[j]-stock[i];
                     indexMax=j;
                     indexMin=i;
                 }
            }
        }
        System.out.println(max+":"+stock[indexMin]+":"+stock[indexMax]);
    }


    /**
     * 希尔排序
     */
    public static void shellSort(){
        int[] sort={47,23,26,16,18,20,21,25,22,19,35,14,33,27};
        int temp=0,j=0 ;
        int increment=sort.length;
        while (increment>1){
            increment=increment/3+1;
            for (int i = increment; i <sort.length; i++) {
                if(sort[i]<sort[i-increment]){
                    temp=sort[i];
                    for (j = i-increment; j >=0 && temp<sort[j]; j-=increment) {
                        sort[j+increment]=sort[j];
                    }
                    sort[j+increment]=temp;
                }
            }
        }

        System.out.println(Arrays.toString(sort));
    }


    /**
     * 快速排序
     */
    public static void quickSort(){
        int[] sort={47,23,26,16,18,20,21,25,22,19,35,14,33,27};
        Qsort(sort,0,sort.length-1);
        System.out.println(Arrays.toString(sort));
    }

    public static void Qsort(int[] sort,int low,int high){
        int privot=0;
        if(low<high){
            privot=partition(sort,low,high);
            Qsort(sort,low,privot-1);
            Qsort(sort,privot+1,high);
        }
    }


    public static int partition(int[] sort,int low,int high){
        int privot=sort[low];
        while (low<high){
            while (low<high && sort[high]>=privot)
                high--;
            swap(sort,low,high);
            while (low<high && sort[low]<=privot)
                low++;
            swap(sort,low,high);
        }

        return low;
    }
}


















