package gendhiramona.spring.core.configuration;

import gendhiramona.spring.core.data.Foo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Slf4j
@Configuration
public class FooConfiguration {

    @Bean
    @Primary
    public Foo foo() {
        return new Foo();
    }

    @Bean
    public Foo foo2() {
        return new Foo();
    }

    @Bean
    public Foo foo3() {
        return new Foo();
    }
}
