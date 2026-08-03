package gendhiramona.webmvc.service;

import gendhiramona.webmvc.model.entity.Hello;
import jakarta.validation.Valid;

public interface HelloService {

    String hello(String name);

    String helloWithAge(@Valid Hello hello);
}
