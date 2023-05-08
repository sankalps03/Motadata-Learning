package com.example.starter.Worker;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class WorkerVerticle extends AbstractVerticle {

  @Override
  public void start(final Promise<Void> startPromise) throws Exception {
    System.out.println("Deployed as worker verticle.");
    startPromise.complete();
    Thread.sleep(5000);
    System.out.println("Blocking operation done.");
  }
}
