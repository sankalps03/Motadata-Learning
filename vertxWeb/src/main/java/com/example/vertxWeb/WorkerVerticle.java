package com.example.vertxWeb;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public class WorkerVerticle extends AbstractVerticle
{

  public static void main(String[] args) {
    Vertx vertx1= Vertx.vertx();

    vertx1.deployVerticle(WorkerVerticle.class.getName());
  }
  @Override
  public void start(Promise<Void> startPromise)
  {
    System.out.println("Thread on which worker verticle is running : "+Thread.currentThread().getName() + " "+ System.currentTimeMillis());

    vertx.setPeriodic(2000, handler -> {

      System.out.println("Periodic Timer thread is running on : "+Thread.currentThread().getName()+" " +System.currentTimeMillis());

      try
      {
        Thread.sleep(5000);
      }
      catch (Exception e)
      {

      }
    });

    vertx.setTimer(6000, handler ->
    {
      System.out.println("Timer task thread is : "+Thread.currentThread().getName() +" "+ System.currentTimeMillis());
    });
    startPromise.complete();
  }

  @Override
  public void stop(Promise<Void> stopPromise)
  {

  }
}
