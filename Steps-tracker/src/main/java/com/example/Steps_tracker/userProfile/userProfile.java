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

  // add h2

  public void start(Promise<Void> startPromise){
    try{

      EventBus eventBus = vertx.eventBus();

      eventBus.consumer("register",message -> {

        JsonObject userData = (JsonObject) message.body();


         register(userData)
            .onSuccess((registered ->{
            message.reply("user Registered");
          }));

      });


    }catch (Exception exception){

      logger.error(exception.getMessage());
      exception.printStackTrace();
    }


  }

  private Future<Void> validateRegistration(JsonObject message){

    Promise<Void> promise = Promise.promise();

    if (registrationFieldMissing(message)||registrationFieldIsWrong(message)){

      promise.complete();

      System.out.println("validation completed");


    }

    return promise.future();

  }

  private boolean registrationFieldMissing(JsonObject message){

    return (message.containsKey("username") && message.containsKey("password")
    && message.containsKey("email") && message.containsKey("city")
      && message.containsKey("deviceId") && message.containsKey("makePublic"));

  }

  private final Pattern validUsername = Pattern.compile("\\w[\\w+|-]*");
  private final Pattern validDeviceId = Pattern.compile("\\w[\\w+|-]*");

  private final Pattern validEmail = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");

  private boolean registrationFieldIsWrong(JsonObject body) {

    return !validUsername.matcher(body.getString("username")).matches() ||
      !validEmail.matcher(body.getString("email")).matches() ||
      body.getString("password").trim().isEmpty();
  }

  private Future<Void> register(JsonObject message){

    Promise<Void> promise= Promise.promise();

    EventBus eventBus = vertx.eventBus();

    JsonObject newUserData = new JsonObject();

    newUserData.put("username",message.getString("username"));
    newUserData.put("password",message.getString("password"));
    newUserData.put("email",message.getString("email"));
    newUserData.put("city",message.getString("city"));
    newUserData.put("makePublic",message.getString("makePublic"));

    eventBus.request("register_in_DB",newUserData,reply ->{

      if(reply.succeeded()){

        promise.complete();


      }

    });

    return promise.future();
  }

  private void fetchUser (JsonObject message){

    // fetch record from database
  }

  private void updateUser(JsonObject message){

    // get data from message and update table
  }

  private  void whoOwns(JsonObject message){

    // get username of device id

  }





}
