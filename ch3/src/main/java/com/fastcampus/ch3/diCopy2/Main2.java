package com.fastcampus.ch3.diCopy2;


import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


class Car {

}

class SportsCar extends Car {
}

class Truck extends Car {
}

class Engine {
}

class AppContext {
    Map map;

    AppContext() {

        try {
            Properties p = new Properties();
            p.load(new FileReader("config.txt"));

            //Properties 에 있는 내용을 Map 에 저장
            map = new HashMap(p);

            // map 에 저장된 키( 클리스 이름 ) 을 얻어, 객체를 생성 -> 다시 맵에 저장
            for (Object key : map.keySet()) {
                Class clazz = Class.forName((String)map.get(key));
                map.put(key, clazz.newInstance());
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    Object getBean(String key) {
        return map.get(key);
    }
}


public class Main2 {
    public static void main(String[] args) throws Exception {
        AppContext ac = new AppContext();

        Car car = (Car) ac.getBean("car");
        Engine engine = (Engine) ac.getBean("engine");
        System.out.println("car = " + car);
        System.out.println("engine = " + engine);
    }

}
