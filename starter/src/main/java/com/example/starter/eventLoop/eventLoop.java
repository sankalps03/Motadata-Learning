package com.example.starter.eventLoop;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

import java.util.concurrent.TimeUnit;

public class eventLoop extends AbstractVerticle {

  public static void main(String[] args) {
    var vertx = Vertx.vertx(
      new VertxOptions()
        .setMaxEventLoopExecuteTime(500)
        .setMaxEventLoopExecuteTimeUnit(TimeUnit.MILLISECONDS)
        .setBlockedThreadCheckInterval(1)
        .setBlockedThreadCheckIntervalUnit(TimeUnit.SECONDS)
        .setEventLoopPoolSize(4)
    );
    vertx.deployVerticle(eventLoop.class.getName(),
      new DeploymentOptions().setInstances(4)
    );
  }

  @Override
  public void start(final Promise<Void> startPromise) throws Exception {
    System.out.println("Start {}"+ getClass().getName());
    startPromise.complete();
    // Do not do this inside a verticle
    Thread.sleep(5000);
  }
}
