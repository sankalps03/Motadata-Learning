package com.example.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;

public class test extends AbstractVerticle {

  public static void main(String[] args) {
    var vertx = Vertx.vertx();
    vertx.deployVerticle(test.class.getName());
  }

  @Override
  public void start() throws Exception {
    int poolSize = 5; // Number of threads in the worker thread pool
    WorkerExecutor executor = vertx.createSharedWorkerExecutor("my-worker-pool", poolSize);

    vertx.setPeriodic(2000, handler ->
      executeBlockingCode(executor)
    );
  }

  private void executeBlockingCode(WorkerExecutor executor) {
    executor.executeBlocking(promise -> {
      System.out.println("Executing blocking code " + Thread.currentThread().getName());
      try {
        Thread.sleep(5000);
        promise.complete();
      } catch (InterruptedException e) {
        e.printStackTrace();
        promise.fail(e);
      }
    }, result -> {
      if (result.succeeded()) {
        System.out.println("Blocking call done.");
      } else {
        System.out.println("Blocking call failed due to:" + result.cause());
      }
    });
  }
}
