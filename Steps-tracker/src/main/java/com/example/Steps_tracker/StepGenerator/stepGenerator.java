package com.example.Steps_tracker.StepGenerator;

import com.example.Steps_tracker.Activity.activity;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;



public class stepGenerator extends AbstractVerticle {

  EventBus eventBus;

  private static HashSet<Integer> devices;

  private static final Logger logger = LoggerFactory.getLogger(activity.class);

  public void start(Promise<Void> startPromise){

    devices = new HashSet<>();

    eventBus = vertx.eventBus();

    Random random = new Random();

    getDevices();

    eventBus.consumer("newUserAdded", handler -> {
      getDevices();
    });



    vertx.setPeriodic(10000, stepGenerator->{

      for (Integer device : devices){

        JsonObject incomingSteps = new JsonObject();

        int steps = random.nextInt(50);

        incomingSteps.put("deviceId",device);

        incomingSteps.put("steps",steps);

        eventBus.publish("incomingSteps",incomingSteps);

        logger.info("step generated for" + incomingSteps);

      }

    });

  }

  private void getDevices() {

    vertx.setTimer(10000, send ->

      eventBus.request("devices","get devices", messageAsyncResult -> {

        if(messageAsyncResult.succeeded())
        {
          devices.addAll((Collection<? extends Integer>) messageAsyncResult.result().body());

          logger.info("Devicelist " + devices);
        }

      }));
  }

}
