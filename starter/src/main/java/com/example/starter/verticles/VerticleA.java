package com.example.starter.verticles;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import io.vertx.core.AbstractVerticle;

import io.vertx.core.Promise;

public class VerticleA extends AbstractVerticle {

//  private static final Logger LOG = LoggerFactory.getLogger(VerticleA.class);

  @Override
  public void start(final Promise<Void> startPromise) throws Exception {

    System.out.println("Start {}"+ getClass().getName());

    vertx.deployVerticle(new VerticleAA(), whenDeployed -> {
      System.out.println("Deployed {}"+VerticleAA.class.getName());
      vertx.undeploy(whenDeployed.result());
    });

    vertx.deployVerticle(new VerticleAB(), whenDeployed -> {
      System.out.println("Deployed {}"+ VerticleAB.class.getName());
      // Do not undeploy
    });

    startPromise.complete();
  }
}
