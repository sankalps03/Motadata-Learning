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
      new JDBCConnectOptions().setJdbcUrl("jdbc:h2:~/test").setUser("sankalp").setPassword("sankalp"),
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

    eventBus.consumer("getUserDataFromDB",message -> {

      String userName = message.body().toString();

      selectUser(pool,userName).onSuccess(userData ->{

        message.reply(userData);


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

    pool.preparedQuery("INSERT INTO stepuser(username,password,email,city,makepublic) VALUES(?,?,?,?,?)")
      .execute(Tuple.of(username, password,email,city,makePublic))
      .onComplete(inserted ->{
        if(inserted.succeeded()){

          System.out.println("inserted");

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

  private Future<JsonObject> selectUser(JDBCPool pool ,String userName){

    Promise<JsonObject> promise = Promise.promise();

    pool
      .preparedQuery("SELECT * FROM stepuser WHERE userName > ?")
      .execute(Tuple.of(userName))
      .onComplete(fetchedData ->{

        JsonObject userData = new JsonObject();

        if(fetchedData.succeeded()){

          for (Row row : fetchedData.result()) {

            userData.put("username", row.getString("username"));

            userData.put("email", row.getString("email"));

            userData.put("city", row.getString("city"));

            userData.put("makePublic", row.getString("makePublic"));

            userData.put("deviceId", row.getString("deviceId"));

          promise.complete(userData);
        }
        }else {

          promise.fail(fetchedData.cause());
        }
      });

    return promise.future();
  }
}
