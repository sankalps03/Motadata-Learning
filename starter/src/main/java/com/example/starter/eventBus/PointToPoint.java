package com.example.starter.eventBus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.metrics.MetricsOptions;
import io.vertx.core.spi.metrics.VertxMetrics;

import java.util.concurrent.atomic.AtomicInteger;

public class PointToPoint {

  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx(new VertxOptions().setMetricsOptions(new MetricsOptions().setEnabled(true)));

    vertx.deployVerticle(new Sender());

    vertx.deployVerticle(new Receiver());

    vertx.deployVerticle(new Receiver2());

  }

  public static class Sender extends AbstractVerticle {
    @Override
    public void start(final Promise<Void> startPromise) throws Exception {

      Vertx vertx = Vertx.vertx(new VertxOptions().setMetricsOptions(new MetricsOptions().setEnabled(true)));

      AtomicInteger count = new AtomicInteger();

      vertx.setPeriodic(1000, id -> {

        count.getAndIncrement();
        // Send a message every second
        vertx.eventBus().send(Sender.class.getName(), "Sending a message..." + count);
      });

      startPromise.complete();
    }
  }

  public static class Receiver extends AbstractVerticle {

    @Override
    public void start(final Promise<Void> startPromise) throws Exception {

      vertx.eventBus().<String>consumer(Sender.class.getName(), message -> {
        System.out.println("R1 Received: {}"+message.body());
      });

      startPromise.complete();
    }
  }

  public static class Receiver2 extends AbstractVerticle {

    @Override
    public void start(final Promise<Void> startPromise) throws Exception {

      startPromise.complete();

      vertx.eventBus().<String>consumer(Sender.class.getName(), message -> {
        System.out.println(" R2 Received: {}"+message.body());
      });
    }
  }
}
