package com.example.starter.Worker;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class WorkerVerticle extends AbstractVerticle {

  @Override
  public void start(final Promise<Void> startPromise) throws Exception {

    vertx.executeBlocking(event ->{
    System.out.println("Deployed as worker verticle."+ Thread.currentThread().getName());
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      System.out.println("Blocking operation done.");
    startPromise.complete();
  });
  }
}
