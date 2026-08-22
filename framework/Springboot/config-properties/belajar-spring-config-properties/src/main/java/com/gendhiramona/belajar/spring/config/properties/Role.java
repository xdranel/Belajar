package com.gendhiramona.belajar.spring.config.properties;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Role {

    private String id;

    private String name;

    private List<Role> defaultRoles;

    private Map<String, Role> roles;
}
