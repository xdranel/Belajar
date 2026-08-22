package gendhiramona.webmvc.service.impl;

import gendhiramona.webmvc.model.entity.Hello;
import gendhiramona.webmvc.service.HelloService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

@Service
@Validated
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        if (name == null || name.isEmpty()) {
            return "Hello Guest";
        } else {
            return "Hello " + name;
        }
    }

    @Override
    public String helloWithAge(Hello hello) {
        // isBlank() checks for null-safe alternative combined with ternaries or Optional
        String name = (hello.getName() == null || hello.getName().isBlank())
                ? "Guest"
                : hello.getName();

//        int age = Objects.requireNonNullElse(hello.getAge(), 0);
//        int age = hello.getAge();

        return "Hello " + name + " with age " + hello.getAge();
    }
}
