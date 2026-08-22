package gendhiramona.spring.core;

import gendhiramona.spring.core.configuration.BarConfiguration;
import gendhiramona.spring.core.configuration.FooConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        FooConfiguration.class,
        BarConfiguration.class,
})
public class MainConfiguration {
}
