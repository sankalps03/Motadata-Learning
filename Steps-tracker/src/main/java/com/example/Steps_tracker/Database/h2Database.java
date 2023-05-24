package com.example.Steps_tracker.Database;

import com.example.Steps_tracker.Activity.activity;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.jdbcclient.*;
import io.vertx.sqlclient.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;

public class h2Database  extends AbstractVerticle {

  private static final Logger logger = LoggerFactory.getLogger(activity.class);

  JDBCPool pool;

  EventBus eventBus;

  public void start(Promise<Void> startPromise){

    try {

     pool = JDBCPool.pool(vertx,
      new JDBCConnectOptions().setJdbcUrl("jdbc:h2:~/test").setUser("sankalp").setPassword("sankalp"),
      new PoolOptions().setMaxSize(16).setName("H2Pool")
    );

    eventBus = vertx.eventBus();

    eventBus.consumer("register_in_DB").handler(this::insertUser);

    eventBus.consumer("getUserDataFromDB").handler(this::selectUser);

    eventBus.consumer("devices").handler(this::selectDeviceId);

    eventBus.consumer("deviceOwner").handler(this:: selectOwner);


  }catch (Exception exception){
      exception.printStackTrace();
    }
  }

  private void selectOwner(Message message) {

    String deviceId = message.body().toString();

    pool.preparedQuery("SELECT username FROM stepuser WHERE deviceid = ?")
      .execute(Tuple.of(deviceId))
      .onComplete(user->{

        if(user.succeeded()){

          Row userName = user.result().iterator().next();

          message.reply(userName.getString(0));

          logger.info("user for "+ deviceId+" :" + userName.getString(0));
        }
        else {

          message.fail(user.cause().hashCode(),user.cause().getMessage());

          logger.error("user for " + deviceId);
        }
      });
  }

  private void insertUser(Message message){

    try {
    JsonObject userData = (JsonObject) message.body();

    String username = userData.getString("username");

    String password = userData.getString("password");

    String email = userData.getString("email");

    String city = userData.getString("city");

    pool.preparedQuery("INSERT INTO stepuser(username,password,email,city) VALUES(?,?,?,?)")
      .execute(Tuple.of(username, password,email,city))
      .onComplete(inserted ->{

        if(inserted.succeeded()){

          message.reply("new user added");

          eventBus.publish("newUserAdded","new user added");

          logger.info("new user "+username+" added to database");
        }
        else {

          message.fail(inserted.cause().hashCode(),inserted.cause().getMessage());

          logger.error("new user"+username+" addition failed :"+ inserted.cause().toString());
        }
      });

  }catch (Exception exception){
      exception.printStackTrace();
    }
  }


  private void selectUser(Message message){

    String userName = message.body().toString();

    pool
      .preparedQuery("SELECT username,email,city,makepublic,deviceid FROM stepuser WHERE userName = ?")
      .execute(Tuple.of(userName))
      .onComplete(fetchedData ->{

        JsonObject userData = new JsonObject();

        if(fetchedData.succeeded()){

          for (Row row : fetchedData.result()) {

            userData.put("username", row.getString(0));

            userData.put("email", row.getString(1));

            userData.put("city", row.getString(2));

            userData.put("makePublic", row.getString(3));

            userData.put("deviceId", row.getInteger(4));

            message.reply(userData);

            logger.info("Data fetched from database for:"+userName);
        }
        }else {

          message.fail(fetchedData.cause().hashCode(),fetchedData.cause().toString());

          logger.error("Data fetch failed for:"+userName);
        }
      });
  }

  private void selectDeviceId(Message message){

    pool.preparedQuery("select deviceId from stepuser")
      .execute().onComplete( rowSetAsyncResult->{

        if(rowSetAsyncResult.succeeded()){

          RowSet<Row> devicesRow = rowSetAsyncResult.result();

          HashSet<Integer> devices = new HashSet<>();

          for (Row device : devicesRow){

            devices.add(device.getInteger("DEVICEID"));
          }

          message.reply(devices);

          logger.info("Device list from database"+devices);

        }else {

          message.fail(rowSetAsyncResult.cause().hashCode(), String.valueOf(rowSetAsyncResult.cause()));

          logger.error("getting device failed" + rowSetAsyncResult.cause().getMessage());
        }
      });

  }
}
