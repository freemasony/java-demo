package com.guava;

import com.google.common.base.Splitter;

/**
 * Created by zhoujian on 2018/11/2.
 */
public class GuavaMain {

    public static void main(String[] args){
        String str="a,b,c,d,e,f";
        System.out.println(Splitter.on(",").splitToList(str));
    }
}
