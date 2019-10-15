package com.jyjx.yxdl.common.util;

public class Singleton {
    private static Singleton ourInstance;

    public static Singleton getInstance() {
        if(ourInstance == null){
            ourInstance = new Singleton();
        }

        return ourInstance;

    }

    private Singleton() {
    }

    public static void main(String[] args) {

    }
}


