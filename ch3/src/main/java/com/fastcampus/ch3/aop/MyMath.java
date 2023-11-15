package com.fastcampus.ch3.aop;


import org.springframework.stereotype.Component;


// 부가기능(Advice) 를 적용할 타겟
@Component()
public class MyMath {
    public int add(int x, int y){
        return x + y;
    }

    public int add(int x, int y, int z){
        return x + y + z;
    }
    public int subtract(int x, int y){
        return x - y;
    }
    public int multiply (int x, int y){
        return x * y;
    }


}
