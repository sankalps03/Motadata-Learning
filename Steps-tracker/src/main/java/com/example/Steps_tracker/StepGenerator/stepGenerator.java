package com.example.Steps_tracker.StepGenerator;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;

import java.util.HashSet;
import java.util.Random;

public class stepGenerator extends AbstractVerticle {

  public void start(Promise<Void> startPromise){

    HashSet<String> devices = new HashSet<>();

    EventBus eventBus = vertx.eventBus();

    Random random = new Random();

    eventBus.request("devices","get devices", messageAsyncResult -> {


    });

    vertx.setPeriodic(30000, stepGenerator->{

      for (String device : devices){

        JsonObject incomingSteps = new JsonObject();

        int steps = random.nextInt(50);

        incomingSteps.put("deviceId",device);

        incomingSteps.put("steps",steps);

        eventBus.publish("incomingSeps",incomingSteps);
      }

    });

  }

}
