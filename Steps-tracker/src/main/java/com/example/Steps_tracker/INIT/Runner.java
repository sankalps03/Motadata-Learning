package com.example.Steps_tracker.INIT;

import com.example.Steps_tracker.Activity.activity;
import com.example.Steps_tracker.Database.h2Database;
import com.example.Steps_tracker.publicAPI.publicApiVerticle;
import com.example.Steps_tracker.userProfile.userProfile;
import io.vertx.core.Vertx;

public class Runner {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(publicApiVerticle.class.getName());
    vertx.deployVerticle(userProfile.class.getName());
    vertx.deployVerticle(activity.class.getName());
    vertx.deployVerticle(h2Database.class.getName());
  }
}
