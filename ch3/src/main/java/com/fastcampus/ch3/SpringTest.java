//package com.fastcampus.ch3;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.GenericXmlApplicationContext;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//
//@Component()
//class Door {
//}
//
//@Component("engine") //<bean id="engine" class="com.fastcampus.ch3.Engine" /> <-- 컴포넌트 태그의 역할
//class Engine {
//}
//
//@Component()
//class SuperEngine extends Engine {
//}
//
//@Component()
//class TurboEngine extends Engine {
//}
//
//
//
//@Component()
//class Car {
//
//    @Value("red")
//    String color;
//
//    @Value("100")
//    int oil;
//
//    //@Resource(name="superEngine") -> By Name 아래 두 줄을 대신할 수 있음
//    @Autowired // by Type -> 타입으로 먼저 검색하고, 여러 개면 이름을 찾음 // 근데 이름도 없을 수 있다면? -> @Qualifier 사용
//    @Qualifier("superEngine")
//    Engine engine;
//    @Autowired
//    Door[] doors;
//
//    public String getColor() {
//        return color;
//    }
//
//    public void setColor(String color) {
//        this.color = color;
//    }
//
//    public int getOil() {
//        return oil;
//    }
//
//    public void setOil(int oil) {
//        this.oil = oil;
//    }
//
//    public Engine getEngine() {
//        return engine;
//    }
//
//    public void setEngine(Engine engine) {
//        this.engine = engine;
//    }
//
//    public Door[] getDoors() {
//        return doors;
//    }
//
//    public void setDoors(Door[] doors) {
//        this.doors = doors;
//    }
//
//    @Override
//    public String toString() {
//        return "Car{" +
//                "color='" + color + '\'' +
//                ", oil=" + oil +
//                ", engine='" + engine + '\'' +
//                ", doors=" + Arrays.toString(doors) +
//                '}';
//    }
//}
//
//
//
//public class SpringTest {
//    public static void main(String[] args) {
//        ApplicationContext ac = new GenericXmlApplicationContext("config.xml");
//
////        Car car = (Car) ac.getBean("car");
//        //아래와 같이 하면 형변환 안해도됨
//        Car car = ac.getBean("car", Car.class);
//        Car car2 = ac.getBean(Car.class); // By Type
//        Engine engine = (Engine) ac.getBean("engine");
//        Door door = (Door) ac.getBean("door");
//
////        car.setColor("red");
////        car.setOil(50);
////        car.setEngine(engine);
////        car.setDoors(new Door[]{
////                ac.getBean("door", Door.class),
////                ac.getBean("door", Door.class)
////        });
//
//        System.out.println("car = " + car);
//
//
//    }
//}