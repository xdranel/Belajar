package gendhiramona.spring.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "gendhiramona.spring.core.configuration"
})
public class ScanConfiguration {
}
