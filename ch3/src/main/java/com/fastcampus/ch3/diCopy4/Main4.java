package com.fastcampus.ch3.diCopy4;


import com.google.common.reflect.ClassPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@Component
class Car {
    @Autowired
    Engine engine;
    @Autowired
    Door door;

    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine +
                ", door=" + door +
                '}';
    }
}
@Component class Door {}


@Component
class SportsCar extends Car {
}

@Component
class Truck extends Car {
}

@Component
class Engine {
}

class AppContext {
    Map map;

    AppContext() {
        map = new HashMap();
        doComponentScan();
        doAutowired();
    }

    private void doAutowired() {
        //map 에 저장된 객체의 iv 중에 @Autowired 가 있으면
        //map 에서 iv Type 에 맞는 객체를 연결 (객체의 주소를 iv 에 연결)
        try {
            for (Object bean : map.values()) {
                for (Field fld : bean.getClass().getDeclaredFields()) {
                    if (fld.getAnnotation(Autowired.class) != null) {
                        fld.set(bean, getBean(fld.getType())); // car.engine = obj
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private void doComponentScan() {
        try {
            //패키지 내의 클래스 목록을 가져온다
            ClassLoader classLoader = AppContext.class.getClassLoader();
            ClassPath classPath = ClassPath.from(classLoader);
            Set<ClassPath.ClassInfo> set = classPath.getTopLevelClasses("com.fastcampus.ch3.diCopy4");

            for (ClassPath.ClassInfo classInfo : set) {
                Class clazz = classInfo.load();
                Component component = (Component) clazz.getAnnotation(Component.class);
                if (component != null) {
                    String id = StringUtils.uncapitalize(classInfo.getSimpleName());
                    map.put(id, clazz.newInstance());
                }

            }

            // 반본문으로 클래스를 읽어서 @Component 이 붙어 있는 지 확인
            // 붙어 있다면 객체 생성 -> Map에 저장
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    Object getBean(String key) { // by Name 으로 객체 검색
        return map.get(key);
    }

    Object getBean(Class clazz) { // by Type 으로 객체 검색
        for (Object obj : map.values()) {
            if (clazz.isInstance(obj)) {
                return obj;
            }
        }
        return null;
    }

}


public class Main4 {
    public static void main(String[] args) throws Exception {
        AppContext ac = new AppContext();

        Car car = (Car) ac.getBean("car");
        Engine engine = (Engine) ac.getBean("engine");
        Door door = (Door) ac.getBean(Door.class);

        //수동으로 연결
//        car.engine = engine;
//        car.door =door;

        System.out.println("car = " + car);
        System.out.println("engine = " + engine);
        System.out.println("door = " + door);


    }

}
