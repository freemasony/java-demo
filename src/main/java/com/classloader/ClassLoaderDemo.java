package com.classloader;

/**
 * Created by Administrator on 2017/7/18.
 */
public class ClassLoaderDemo {

    public static void main(String[] args){
        ClassLoader classloader = ClassLoaderDemo.class.getClassLoader();
        System.out.println("---"+classloader.toString());
        System.out.println("---"+classloader.getParent().toString());
        System.out.println("---"+classloader.getParent().getParent().toString());
    }
}
