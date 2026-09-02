package gendhiramona.spring.core;

import gendhiramona.spring.core.data.MultiFoo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {
        "gendhiramona.spring.core.service",
        "gendhiramona.spring.core.repository",
        "gendhiramona.spring.core.configuration",
})
@Import({
        MultiFoo.class,
})
public class ComponentConfiguration {
}

