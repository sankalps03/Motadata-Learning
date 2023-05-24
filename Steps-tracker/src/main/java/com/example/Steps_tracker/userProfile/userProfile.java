package com.example.Steps_tracker.userProfile;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;


public class userProfile extends AbstractVerticle {

  private static final Logger logger = LoggerFactory.getLogger(userProfile.class);

  EventBus eventBus;
  public void start(Promise<Void> startPromise){
    try{

      eventBus = vertx.eventBus();

      eventBus.consumer("register").handler(this::register);

      eventBus.consumer("fetchUser").handler(this::fetchUser);

      eventBus.consumer("whoOwns").handler(this::whoOwns);


    }catch (Exception exception){

      logger.error(exception.getMessage());

      exception.printStackTrace();
    }


  }

  private boolean registrationFieldMissing(JsonObject message){

    return (message.containsKey("username") && message.containsKey("password")
    && message.containsKey("email") && message.containsKey("city"));

  }

  private final Pattern validEmail = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");

  private boolean registrationFieldIsWrong(JsonObject body) {

    return
      !(validEmail.matcher(body.getString("email")).matches() ||
      body.getString("password").trim().isEmpty()|| body.getString("username").trim().isEmpty()
      || body.getString("city").trim().isEmpty());
  }

  private  void register(Message message){

    JsonObject userData = (JsonObject) message.body();

    JsonObject newUserData = new JsonObject();

    newUserData.put("username",userData.getString("username"));

    newUserData.put("password",userData.getString("password"));

    newUserData.put("email",userData.getString("email"));

    newUserData.put("city",userData.getString("city"));

    if (registrationFieldMissing(newUserData)&&registrationFieldIsWrong(newUserData)) {

      logger.info("valid user");

      eventBus.request("register_in_DB", newUserData, reply -> {

        if (reply.succeeded()) {

          message.reply("new user added");

          logger.info("new user added");


        } else {

          message.fail(reply.cause().hashCode(), reply.cause().getMessage());

          logger.error("new user addition failed");

        }

      });
    }
    else {

      message.fail(502,"invalid user");
      logger.error("invalid user");
    }

  }

  private void fetchUser (Message message){

    String userName = (String) message.body();

    eventBus.request("getUserDataFromDB",userName,reply ->{

      if (reply.succeeded()){

        JsonObject userData = (JsonObject) reply.result().body();

        message.reply(userData);

        logger.info("Data fetched for user :" + userName);

      }else {

        message.fail(reply.cause().hashCode(),reply.cause().toString());

        logger.error("Data fetch failed for user:" + userName);
      }

    });

  }

  private  void whoOwns(Message message){


    String deviceId =  message.body().toString();

    eventBus.request("deviceOwner",deviceId,reply->{

      if(reply.succeeded()){

        String username = reply.result().body().toString();

        message.reply(username);

        logger.info("user for device" + deviceId + " "+ username);
      }
      else {

        message.fail(reply.cause().hashCode(),reply.cause().getMessage());

        logger.error("getting device owner failed"+reply.cause().getMessage());
      }

    });

  }

}
