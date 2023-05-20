package com.example.Steps_tracker.Database;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;

import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.jdbcclient.*;
import io.vertx.sqlclient.*;

public class h2Database  extends AbstractVerticle {

  public void start(Promise<Void> startPromise){

    try {

    JDBCPool pool = JDBCPool.pool(vertx,
      new JDBCConnectOptions().setJdbcUrl("jdbc:h2:~/test").setUser("sa").setPassword(""),
      new PoolOptions().setMaxSize(16).setName("H2Pool")
    );

    EventBus eventBus = vertx.eventBus();

    eventBus.consumer("register_in_DB",message->{

      System.out.println(message.body().toString()+"  h2");

      JsonObject userData = (JsonObject) message.body();

      insertUser(pool,userData).onSuccess(inserted ->{


        message.reply("user Registered");
      });


    });
  }catch (Exception exception){
      exception.printStackTrace();
    }
  }

  private Future<Void> insertUser(JDBCPool pool, JsonObject message){

    Promise<Void> promise = Promise.promise();

    try {

    System.out.println("insert ");

    String username = message.getString("username");
    String password = message.getString("password");
    String email = message.getString("email");
    String city = message.getString("city");
    String makePublic = message.getString("makePublic");

    pool.preparedQuery("INSERT INTO USERDATA VALUES(?,?,?,?,?)")
      .execute(Tuple.of(username, password,email,city,makePublic))
      .onComplete(inserted ->{
        if(inserted.succeeded()){

          System.out.println("inserted");

          long deviceId = inserted.result().iterator().next().getLong("deviceID");


          promise.complete();

        }
        else {

          System.out.println("insert failed" + inserted.cause());

          promise.fail(inserted.cause());

        }
      });


  }catch (Exception exception){
      exception.printStackTrace();
    }

    return promise.future();
  }

  private void updateUser(JDBCPool pool ,JsonObject message){



  }

  private void selectUser(JDBCPool pool ,JsonObject message){

    String deviceId = message.getString("deviceId");

    pool
      .preparedQuery("SELECT * FROM USERDATA WHERE deviceID > ?")
      .execute(Tuple.of(deviceId));

  }
}
