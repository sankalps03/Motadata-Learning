package com.example.Steps_tracker.Activity;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.jdbcclient.JDBCConnectOptions;
import io.vertx.jdbcclient.JDBCPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class activity extends AbstractVerticle {

  private static final Logger logger = LoggerFactory.getLogger(activity.class);

  private JDBCPool pool;

  EventBus eventBus;

  public void start(Promise<Void> startPromise){

     pool = JDBCPool.pool(vertx,
      new JDBCConnectOptions().setJdbcUrl("jdbc:h2:~/test").setUser("sankalp").setPassword("sankalp"),
      new PoolOptions().setMaxSize(16).setName("H2Pool")
    );

     eventBus = vertx.eventBus();

    eventBus.consumer("totalSteps").handler(this::totalSteps);

    eventBus.consumer("dailySteps").handler(this::dailySteps);

    eventBus.consumer("monthlySteps").handler(this::monthlySteps);

    eventBus.consumer("yearlySteps").handler(this::yearlySteps);

    eventBus.consumer("incomingSteps").handler(this::insertSteps);

    vertx.setPeriodic(10000,handler->{

      dailyRanking();

    });


  }

  private void insertSteps(Message<Object> message) {

    JsonObject steps = (JsonObject) message.body();

    String deviceId = steps.getString("deviceId");

    int step = steps.getInteger("steps");


    pool.preparedQuery("INSERT INTO stepcount VALUES($1, $2,current_timestamp)")
      .execute(Tuple.of(deviceId,step)).
      onComplete(inserted ->{

        if (inserted.succeeded()){

          logger.info("steps inserted for "+ deviceId);
        }

        else {

          logger.error(inserted.cause().toString());
        }
      });


  }

  private void totalSteps(Message message){

    String deviceId = message.body().toString();

    pool.preparedQuery("SELECT sum(steps) FROM stepcount WHERE" +
        "(deviceid = $1)").
      execute(Tuple.of(deviceId))
      .onComplete(stepCount ->{

        if ((stepCount.succeeded())){

          Row totalStep = (stepCount.result().iterator().next());

          message.reply(totalStep.getLong(0));

          logger.info("total step for "+ deviceId+" :"+ totalStep.getLong(0));
        }else {

          message.reply(stepCount.cause());

          logger.error("total step "+deviceId+ " :"+stepCount.cause());
        }

      });

  }

  private void monthlySteps(Message message){

    JsonObject monthly = (JsonObject) message.body();

    String deviceId = monthly.getString("deviceId");

    LocalDateTime dateTime = LocalDateTime.of(Integer.parseInt(monthly.getString("year")),
      Integer.parseInt(monthly.getString("month")),1,0,0);

    pool.preparedQuery("SELECT sum(steps) FROM stepcount WHERE" +
        "(deviceid = $1) AND" +
        "(date_trunc('month', inserttimestamp) = $2::timestamp)").
      execute(Tuple.of(deviceId,dateTime)).
      onComplete(rowSetAsyncResult -> {

        if(rowSetAsyncResult.succeeded()){

          Row monthlyStep = rowSetAsyncResult.result().iterator().next();

          message.reply(monthlyStep.getLong(0));

          logger.info("monthly step for "+deviceId+ " :"+ monthlyStep.getLong(0));
        }
        else {

          message.fail(rowSetAsyncResult.cause().hashCode(),rowSetAsyncResult.cause().toString());

          logger.error("monthly step for "+deviceId+ " :"+rowSetAsyncResult.cause());
        }
      });



  }
  private void dailySteps(Message message){

    JsonObject daily = (JsonObject) message.body();

    String deviceId = daily.getString("deviceId");

    LocalDateTime dateTime = LocalDateTime.of(Integer.parseInt(daily.getString("year")),
      Integer.parseInt(daily.getString("month")),Integer.parseInt(daily.getString("day")),0,0);

    pool.preparedQuery("SELECT sum(steps) FROM stepcount WHERE" +
                             "(deviceid = $1) AND" +"(date_trunc('day', inserttimestamp) = $2::timestamp)")
      .execute(Tuple.of(deviceId,dateTime)).
      onComplete(rowSetAsyncResult -> {

        if(rowSetAsyncResult.succeeded()){

          Row dailyStep = rowSetAsyncResult.result().iterator().next();

          message.reply(dailyStep.getLong(0));

          logger.info("daily step for"+deviceId+ " :"+dailyStep.getLong(0));
        }
        else {

          message.fail(rowSetAsyncResult.cause().hashCode(),rowSetAsyncResult.cause().toString());

          logger.error("daily step for"+deviceId+ " :"+rowSetAsyncResult.cause().toString());
        }
      });



  }

  private void yearlySteps (Message message){

    JsonObject yearly = (JsonObject) message.body();

    String deviceId = yearly.getString("deviceId");

    LocalDateTime dateTime = LocalDateTime.of(
      Integer.parseInt(yearly.getString("year")),1,1,0,0);

    pool.preparedQuery("SELECT sum(steps) FROM stepcount WHERE" +
        "(deviceid = $1) AND" +
        "(date_trunc('year', inserttimestamp) = $2::timestamp)").
      execute(Tuple.of(deviceId,dateTime)).
      onComplete(rowSetAsyncResult -> {

        if(rowSetAsyncResult.succeeded()){

          Row yearlyStep = rowSetAsyncResult.result().iterator().next();

          message.reply(yearlyStep.getLong(0));

          logger.info("yearly step for "+ deviceId+ " :"+yearlyStep.getLong(0));
        }
        else {

          message.fail(rowSetAsyncResult.cause().hashCode(),rowSetAsyncResult.cause().toString());

          logger.error("yearly step for "+deviceId+ " :"+rowSetAsyncResult.cause());
        }
      });


  }

  private void dailyRanking(){


    pool.preparedQuery("SELECT deviceid, SUM(steps) as steps FROM stepcount WHERE" +
        "(now() - inserttimestamp <= (INTERVAL '1' DAY))" +
        "GROUP BY DEVICEID ORDER BY steps DESC")
      .execute().onComplete(rank->{

        if(rank.succeeded()){

          JsonArray rankings = new JsonArray();

          RowSet<Row> ranks = rank.result();

          for (Row row : ranks){

            rankings.add(new JsonObject()
              .put("deviceId",row.getValue(0))
              .put("steps",row.getValue(1)));

          }

          eventBus.publish("dailyRankings",rankings);

          logger.info("dailyRankings "+ rankings);

        }else {
          logger.error(rank.cause().toString());
        }
      }
        );

  }

  private void todayActivity(Message message){

    JsonObject daily = (JsonObject) message;

    String deviceId = daily.getString("deviceId");

    LocalDateTime dateTime = LocalDateTime.of(Integer.parseInt(daily.getString("year")),
      Integer.parseInt(daily.getString("month")),Integer.parseInt(daily.getString("day")),0,0);

    pool.preparedQuery("SELECT current_timestamp, coalesce(sum(steps), 0) FROM stepevent WHERE " +
        "(deviceid = $1) AND" +
        "(date_trunc('day', inserttimestamp) = date_trunc('day', current_timestamp))")
      .execute(Tuple.of(deviceId)).
      onComplete(rowSetAsyncResult -> {

        if(rowSetAsyncResult.succeeded()){

          message.reply(rowSetAsyncResult.result().iterator().next());

          logger.info("Today step "+rowSetAsyncResult.result().iterator().next());
        }
        else {

          message.reply(rowSetAsyncResult.cause());

          logger.error("Today step "+rowSetAsyncResult.cause());
        }
      });

  }
}
