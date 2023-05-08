package com.example.starter.verticles;


import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import io.vertx.core.AbstractVerticle;

import io.vertx.core.Promise;

public class VerticleAA extends AbstractVerticle {

//  private static final Logger LOG = LoggerFactory.getLogger(VerticleAA.class);

  @Override
  public void start(final Promise<Void> startPromise) throws Exception {

    System.out.println("Start {}"+ getClass().getName());

    startPromise.complete();
  }

  @Override
  public void stop(final Promise<Void> stopPromise) throws Exception {

    System.out.println("Stop {}" + getClass().getName());

    stopPromise.complete();
  }
}
