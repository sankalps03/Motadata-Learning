package com.example.starter.eventBus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public class pubSub {

  public static void main(String[] args) {

    var vertx = Vertx.vertx();

    vertx.deployVerticle(new Publish());

    vertx.deployVerticle(new Subscriber1());

    vertx.deployVerticle(Subscriber2.class.getName(), new DeploymentOptions().setInstances(2));
  }

  public static class Publish extends AbstractVerticle {
    @Override
    public void start(final Promise<Void> startPromise) throws Exception {

      startPromise.complete();

      vertx.setPeriodic(1000, id ->
        vertx.eventBus().publish(Publish.class.getName(), "A message for everyone!")
      );
    }
  }

  public static class Subscriber1 extends AbstractVerticle {

    @Override
    public void start(final Promise<Void> startPromise) throws Exception {

      vertx.eventBus().<String>consumer(Publish.class.getName(), message -> {
        System.out.println("sub1 Received: {} " +  message.body());
      });

      startPromise.complete();
    }
  }

  public static class Subscriber2 extends AbstractVerticle {

    @Override
    public void start(final Promise<Void> startPromise) throws Exception {

      vertx.eventBus().<String>consumer(Publish.class.getName(), message -> {
        System.out.println("sub2 Received: {} " + message.body());
      });

      startPromise.complete();
    }
  }
}
