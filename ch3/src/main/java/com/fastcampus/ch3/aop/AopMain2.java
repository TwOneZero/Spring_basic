package com.fastcampus.ch3.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class AopMain2 {
    public static void main(String[] args) {
        ApplicationContext ac =
                new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context_aop.xml");
        MyMath mm =  ac.getBean(MyMath.class);
        mm.add(3,6);
        mm.add(3, 6, 6);
        mm.multiply(3, 6);
        mm.subtract(9 ,3);

    }
}
