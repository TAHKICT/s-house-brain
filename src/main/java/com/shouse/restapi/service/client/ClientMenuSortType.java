package com.shouse.restapi.service.client;

import com.shouse.restapi.service.StringConstants;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum  ClientMenuSortType {
    TYPE(StringConstants.type),
    LOCATION(StringConstants.location);

    private String name;

    ClientMenuSortType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static List<String> getTypeNames(){
       return Arrays.asList(values()).stream().map(e -> e.getName()).collect(Collectors.toList());
    }

}
