package com.gendhiramona.belajar.spring.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties("application")
public class ApplicationProperties {

    private Date expireDate;

    private Duration defaultTimeout;

    private String name;

    private String version;

    private boolean productionMode;

    @NestedConfigurationProperty
    private DatabaseProperties database;

    @NestedConfigurationProperty
    private Role role;

}
