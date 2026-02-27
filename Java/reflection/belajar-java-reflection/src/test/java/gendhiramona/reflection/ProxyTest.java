package gendhiramona.reflection;

import gendhiramona.reflection.data.Car;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {

    @Test
    void testProxy() {

        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {

                if (method.getName().equals("getName")) {
                    return "Car Proxy";
                } else if (method.getName().equals("run")) {
                    System.out.println("Car is running");
                }
                return null;
            }
        };

        Car car = (Car) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                new Class[]{Car.class},
                invocationHandler
        );

        System.out.println(car.getClass());
        System.out.println(car.getName());

        car.run();
    }
}
