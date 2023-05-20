package com.example.Steps_tracker.Activity;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.RowSet;

public class activity extends AbstractVerticle {

  private static final Logger logger = LoggerFactory.getLogger(activity.class);

  public void start(Promise<Void> startPromise){


  }

  private void totalSteps(JsonObject message){

    String deviceId = message.getString("deviceId");

    // db call
  }

  private void monthlySteps(JsonObject message){

    String deviceId = message.getString("deviceId");

    // get date , year , month

    // Db call
  }
  private void dailySteps(JsonObject object){

//    String deviceId = message.getString("deviceId");

    // get date, year , month

    // Db call


  }

  private void DailyRanking( ){

  }
}
