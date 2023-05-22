package com.example.Steps_tracker.StepGenerator;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

public class stepGenerator extends AbstractVerticle {

  public void start(Promise<Void> startPromise){

    HashSet<Integer> devices = new HashSet<>();

    EventBus eventBus = vertx.eventBus();

    Random random = new Random();

    vertx.setPeriodic(3000, send ->

    eventBus.request("devices","get devices", messageAsyncResult -> {

      if(messageAsyncResult.succeeded())
      {
      devices.addAll((Collection<? extends Integer>) messageAsyncResult.result().body());

        System.out.println(devices);
      }


    }));

    vertx.setPeriodic(3000, stepGenerator->{

      for (Integer device : devices){

        JsonObject incomingSteps = new JsonObject();

        int steps = random.nextInt(50);

        incomingSteps.put("deviceId",device);

        incomingSteps.put("steps",steps);

        System.out.println(incomingSteps.toString());

        eventBus.publish("incomingSeps",incomingSteps);
      }

    });

    startPromise.complete();

  }

}
