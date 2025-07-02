package com.emre.controller;


public class RestBaseController {

    public <T> com.emre.controller.RootEntity<T> ok(T payload){
        return  com.emre.controller.RootEntity.ok(payload);
    }

    public <T> com.emre.controller.RootEntity<T> error(String errorMessage){
        return com.emre.controller.RootEntity.error(errorMessage);
    }

}
