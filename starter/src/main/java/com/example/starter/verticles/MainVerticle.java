package com.example.starter.verticles;

import java.util.UUID;

import io.vertx.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.json.JsonObject;

public class MainVerticle extends AbstractVerticle {

//  private static final Logger LOG = LoggerFactory.getLogger(MainVerticle.class);

  public static void main(String[] args) {

    final Vertx vertx = Vertx.vertx();

    vertx.deployVerticle(new MainVerticle());
  }

  @Override
  public void init(Vertx vertx, Context context) {
    System.out.println("is this called first");

    super.init(vertx, context);
  }

  @Override
  public void start(final Promise<Void> startPromise) throws Exception {

    System.out.println("Start {}"+ getClass().getName());

    vertx.deployVerticle(new VerticleA());

    vertx.deployVerticle(new VerticleB());

    vertx.deployVerticle(VerticleN.class.getName(),

      new DeploymentOptions()
        .setInstances(4)
        .setConfig(new JsonObject()
          .put("id", UUID.randomUUID().toString())
          .put("name", VerticleN.class.getSimpleName())
        )
    );
    startPromise.complete();
  }
}
