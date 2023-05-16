package com.example.starter.Worker;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public class Worker extends AbstractVerticle {

  public static void main(String[] args) {
    var vertx = Vertx.vertx();
    vertx.deployVerticle(Worker.class.getName());
  }

  @Override
  public void start(final Promise<Void> startPromise) throws Exception {
//    vertx.deployVerticle(WorkerVerticle.class.getName(),
//      new DeploymentOptions()
//        .setWorkerPoolSize(10)
//        .setWorkerPoolName("my-worker-verticle").setInstances(5)
//    );
    executeBlockingCode();
    System.out.println("after 1");
    executeBlockingCode();
    System.out.println("after 2");
    executeBlockingCode();


    startPromise.complete();
  }

  private void executeBlockingCode() {
    vertx.executeBlocking(event -> {
      System.out.println("Executing blocking code " + Thread.currentThread().getName());
      try {
        Thread.sleep(5000);
        event.complete();
      } catch (InterruptedException e) {
        e.printStackTrace();
        event.fail(e);
      }
    }, result -> {
      if (result.succeeded()) {
        System.out.println("Blocking call done.");
      } else {
        System.out.println("Blocking call failed due to:"+ result.cause());
      }
    });
  }
}
