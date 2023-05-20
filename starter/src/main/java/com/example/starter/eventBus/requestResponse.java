package com.example.starter.eventBus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;

public class requestResponse {

  public static void main(String[] args) {
    var vertx = Vertx.vertx();

    vertx.deployVerticle(new RequestVerticle());

    vertx.deployVerticle(new ResponseVerticle());

    vertx.deployVerticle(new ResponseVerticle1());
  }

  public static class RequestVerticle extends AbstractVerticle {

    static final String ADDRESS = "my.request.address";

    @Override
    public void start(final Promise<Void> startPromise) throws Exception {

      startPromise.complete();

      var eventBus = vertx.eventBus();

      final String message = "Hello World!";

      System.out.println("Sending: {}"+ message);

      vertx.setPeriodic(1000,send ->{

      eventBus.<String>request(ADDRESS, message, reply -> {
        System.out.println("Response: {}"+reply.result().body()+Thread.currentThread().getName());
      });
      });
    }
  }

  public static class ResponseVerticle extends AbstractVerticle {

    @Override
    public void start(final Promise<Void> startPromise) throws Exception {

      startPromise.complete();

      DeliveryOptions opt = new DeliveryOptions().setSendTimeout(3000);

      vertx.eventBus().<String>consumer(RequestVerticle.ADDRESS, message -> {
        System.out.println("Received Message: {}"+ message.body()+Thread.currentThread().getName());
        message.reply("Received your message. Thanks!");
      });
    }
  }

  public static class ResponseVerticle1 extends AbstractVerticle {

    @Override
    public void start(final Promise<Void> startPromise) throws Exception {

      startPromise.complete();

      vertx.eventBus().<String>consumer(RequestVerticle.ADDRESS, message -> {
        System.out.println(" RV1 Received Message: {}"+ message.body()+Thread.currentThread().getName());
//        message.reply("RV1 Received your message. Thanks!");
      });
    }
  }
}
