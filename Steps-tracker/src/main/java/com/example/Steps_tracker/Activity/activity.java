package com.example.Steps_tracker.Activity;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
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

  public void start(Promise<Void> startPromise){

     pool = JDBCPool.pool(vertx,
      new JDBCConnectOptions().setJdbcUrl("jdbc:h2:~/test").setUser("sankalp").setPassword("sankalp"),
      new PoolOptions().setMaxSize(16).setName("H2Pool")
    );

    EventBus eventBus = vertx.eventBus();

    eventBus.consumer("totalSteps").handler(this::totalSteps);

    eventBus.consumer("dailySteps").handler(this::dailySteps);

    eventBus.consumer("monthlySteps").handler(this::monthlySteps);

    eventBus.consumer("YearlySteps").handler(this::yearlySteps);

    eventBus.consumer("rankingLast24Hours").handler(this::dailyRanking);


  }

  private void totalSteps(Message message){

    String deviceId = message.body().toString();

    pool.preparedQuery("SELECT sum(steps_count) FROM stepcount WHERE" +
        "(device_id = $1)").
      execute(Tuple.of(deviceId))
      .onComplete(stepCount ->{

        if ((stepCount.succeeded())){


          message.reply(stepCount.result().iterator().next());

          logger.info("total step "+stepCount.result().iterator().next());
        }else {

          message.reply(stepCount.cause());

          logger.error("total step "+stepCount.cause());
        }

      });

  }

  private void monthlySteps(Message message){

    JsonObject monthly = (JsonObject) message;

    String deviceId = monthly.getString("deviceId");

    LocalDateTime dateTime = LocalDateTime.of(Integer.parseInt(monthly.getString("year")),
      Integer.parseInt(monthly.getString("month")),1,0,0);

    pool.preparedQuery("SELECT sum(steps_count) FROM stepevent WHERE" +
        "(device_id = $1) AND" +
        "(date_trunc('month', sync_timestamp) = $2::timestamp)").
      execute(Tuple.of(deviceId,dateTime)).
      onComplete(rowSetAsyncResult -> {

        if(rowSetAsyncResult.succeeded()){

          message.reply(rowSetAsyncResult.result().iterator().next());

          logger.info("monthly step "+rowSetAsyncResult.result().iterator().next());
        }
        else {

          message.reply(rowSetAsyncResult.cause());

          logger.error("monthly step "+rowSetAsyncResult.cause());
        }
      });



  }
  private void dailySteps(Message message){

    JsonObject daily = (JsonObject) message;

    String deviceId = daily.getString("deviceId");

    LocalDateTime dateTime = LocalDateTime.of(Integer.parseInt(daily.getString("year")),
      Integer.parseInt(daily.getString("month")),Integer.parseInt(daily.getString("day")),0,0);

    pool.preparedQuery("SELECT current_timestamp, coalesce(sum(steps_count), 0) FROM stepcount WHERE " +
      "(device_id = $1) AND" + "(date_trunc('day', sync_timestamp) = date_trunc('day', current_timestamp))").
      execute(Tuple.of(deviceId,dateTime)).
      onComplete(rowSetAsyncResult -> {

        if(rowSetAsyncResult.succeeded()){

          message.reply(rowSetAsyncResult.result().iterator().next());

          logger.info("daily step "+rowSetAsyncResult.result().iterator().next());
        }
        else {

          message.reply(rowSetAsyncResult.cause());

          logger.error("daily step "+rowSetAsyncResult.cause());
        }
      });



  }

  private void yearlySteps (Message message){

    JsonObject yearly = (JsonObject) message;

    String deviceId = yearly.getString("deviceId");

    LocalDateTime dateTime = LocalDateTime.of(
      Integer.parseInt(yearly.getString("year")),1,1,0,0);

    pool.preparedQuery("SELECT sum(steps_count) FROM stepevent WHERE" +
        "(device_id = $1) AND" +
        "(date_trunc('year', sync_timestamp) = $2::timestamp)").
      execute(Tuple.of(deviceId,dateTime)).
      onComplete(rowSetAsyncResult -> {

        if(rowSetAsyncResult.succeeded()){

          message.reply(rowSetAsyncResult.result().iterator().next());

          logger.info("yearly step "+rowSetAsyncResult.result().iterator().next());
        }
        else {

          message.reply(rowSetAsyncResult.cause());

          logger.error("yearly step "+rowSetAsyncResult.cause());
        }
      });


  }

  private void dailyRanking(Message message ){

    pool.preparedQuery("SELECT deviceid, SUM(steps_count) as steps FROM stepcount WHERE" +
        "(now() - sync_timestamp <= (interval '24 hours'))" +
        "GROUP BY device_id ORDER BY steps DESC")
      .execute().onComplete(rank->{

        if(rank.succeeded()){

          RowSet<Row> ranks = rank.result();

//          for ()

//          message.reply();
        }

        }
        );

  }
}
