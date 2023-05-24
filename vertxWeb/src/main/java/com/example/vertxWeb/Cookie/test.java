package com.example.vertxWeb.Cookie;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class test {

  public static void main(String[] args) {

    EventBus eventBus = Vertx.vertx().eventBus();

//    Vertx.vertx().setPeriodic(1000,handle->{

    eventBus.send("sankalp",new JsonObject().put("ip","1.2.3.4"));

    eventBus.send("sankalp",new JsonObject().put("port","8080"));

    eventBus.consumer("sankalp",handler->{

      System.out.println(handler.body().toString());

    });



//    });


  }
}
