package com.fastcampus.ch3.aop;

import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AopMain {

    public static void main(String[] args) throws Exception {

        MyAdvice myAdvice = new MyAdvice();

        Class myClass = Class.forName("com.fastcampus.ch3.aop.MyClass");
        Object obj = myClass.newInstance();

        for (Method m : myClass.getDeclaredMethods()) {
            myAdvice.invoke(m, obj, null);

        }
    }
}

class MyAdvice {

    Pattern p = Pattern.compile("a.*");

    boolean matches(Method m){
        Matcher matcher = p.matcher(m.getName()); // 이름을 가져와서 패턴에 맞는 것만 invoke 하기
        return matcher.matches();
    }


    void invoke(Method m, Object obj, Object... args) throws Exception {
        if (m.getAnnotation(Transactional.class) != null ){ // 어노테이션이 붙어있을 때만
            System.out.println("[before]");
        }
        m.invoke(obj, args); // 인자 메소드 호출
        if (m.getAnnotation(Transactional.class) != null) {
            System.out.println("[after]");
        }
    }
}

class MyClass {

    @Transactional
    public void aaa() {
        System.out.println("aaa() is called");
    }

    void aaa2() {
        System.out.println("aaa2() is called");
    }

    void bbb() {
        System.out.println("bbb() is called");
    }

}
