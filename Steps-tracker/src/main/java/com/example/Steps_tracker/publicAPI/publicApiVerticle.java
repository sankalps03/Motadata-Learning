package com.example.Steps_tracker.publicAPI;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.*;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.handler.sockjs.SockJSBridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.ext.auth.properties.PropertyFileAuthentication;


public class publicApiVerticle extends AbstractVerticle {

  private static final int HTTP_PORT = 8080;

  private static final Logger logger = LoggerFactory.getLogger(publicApiVerticle.class);

  private final JsonArray ranking = new JsonArray();

  EventBus eventBus;

  public void start(Promise<Void> startPromise) {

    try {

    Router router = Router.router(vertx);

    eventBus = getVertx().eventBus();

    BodyHandler bodyHandler = BodyHandler.create();

    router.post().handler(bodyHandler);

    router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx)));

    PropertyFileAuthentication authenticate = PropertyFileAuthentication.create(vertx,"/home/sankalp/MotadataLearning/Steps-tracker/src/main/resources/users.properties");

    router.route("/logout").handler(context -> {

      context.clearUser();

      context.response().putHeader("location", "/").setStatusCode(302).end();
    });

    String prefix = "/user";

    router.route(prefix+"/*").handler(RedirectAuthHandler.create(authenticate, "/loginPage.html"));

    router.route(prefix+"/*").handler(StaticHandler.create().setIndexPage("userProfile.html"));

    router.route("/loginHandler").handler(FormLoginHandler.create(authenticate));

    router.post("/register").handler(this::register);

    router.get(prefix + "/:username").handler(this::fetchUser);

    router.get(prefix + "/:username/total").handler(this::totalSteps);

      router.get(prefix + "/:username/:year").handler(this::yearlySteps);

    router.get(prefix + "/:username/:year/:month").handler(this::monthlySteps);

    router.get(prefix + "/:username/:year/:month/:day").handler(this::dailySteps);

    eventBus.consumer("dailyRankings").handler(this::publishRanking);

      SockJSHandler jsHandler = SockJSHandler.create(vertx);

      SockJSBridgeOptions bridgeOptions = new SockJSBridgeOptions()
        .addInboundPermitted(new PermittedOptions().setAddressRegex("updates.*"))
          .addOutboundPermitted(new PermittedOptions().setAddressRegex("updates.*"));

      router.mountSubRouter("/eventbus",jsHandler.bridge(bridgeOptions));

      router.route().handler(StaticHandler.create().setCachingEnabled(false));

    vertx.createHttpServer().requestHandler(router).listen(HTTP_PORT).onSuccess(done -> logger.info("Listening on port 4000"));

    startPromise.complete();

  }catch (Exception exception){
      logger.error( publicApiVerticle.class.getName() + exception.getMessage());
      exception.printStackTrace();
    }
  }

  private void publishRanking(Message message) {

    JsonArray rankings = (JsonArray) message.body();

    for (Object rank : rankings){

      JsonObject device =(JsonObject)rank;

      deviceOwner(device).onSuccess(handler->{

        String userName = handler.toString();

        device.put("userName",userName);

        ranking.add(device);
      });
    }

    eventBus.publish("updates.publicRanking",ranking);

    ranking.clear();
  }

  private Future<String> deviceOwner(JsonObject rank) {

    Promise<String> promise = Promise.promise();

    String deviceId = rank.getString("deviceId");

    eventBus.request("whoOwns",deviceId,messageAsyncResult -> {

      if (messageAsyncResult.succeeded()){

        String userName = messageAsyncResult.result().body().toString();

        logger.info("device ownwer fetced "+ userName);

        promise.complete(userName);

      }
      else {
        logger.error("can't fec device owner");
      }

    });
    return promise.future();
  }


  private void register(RoutingContext context) {

    System.out.println(context.body().asString());

    JsonObject userData = context.body().asJsonObject();

    logger.info("user data " + userData.toString());

    eventBus.request("register" ,userData,reply -> {if (reply.succeeded()){

      context.response().setChunked(true);

      forwardJsonOrStatusCode(context,new JsonObject().put("message","user added"));

    }
    else {

      sendBadGateway(context,reply.cause());
    }
    });
  }


  private void sendBadGateway(RoutingContext context, Throwable error) {

    logger.error("Bad Gateway", error);

    context.fail(502);
  }


  private void fetchUser(RoutingContext context) {

    String userName = context.request().params().get("username");

    eventBus.request("fetchUser",userName,reply ->{

      JsonObject userData = (JsonObject) reply.result().body();

      if (reply.succeeded()){

        forwardJsonOrStatusCode(context,userData);

      }
      else {

        sendBadGateway(context,reply.cause());

      }

    });

  }

  private void forwardJsonOrStatusCode(RoutingContext ctx,JsonObject resp) {

      ctx.response()
        .putHeader("Content-Type", "application/json")
        .end(resp.encode());

  }

  private void totalSteps(RoutingContext context) {

    String deviceId = "1000";

    eventBus.request("totalSteps",deviceId,messageAsyncResult -> {

      if(messageAsyncResult.succeeded()){

        Long totalStep = (Long) messageAsyncResult.result().body();

        forwardJsonOrStatusCode(context,new JsonObject().put("total steps", totalStep));
      }else {

        sendBadGateway(context,messageAsyncResult.cause());
      }

    });

  }

  private void monthlySteps(RoutingContext context) {

    JsonObject monthlyStep = new JsonObject();

    monthlyStep.put( "deviceId" , 1000);

    monthlyStep.put( "year" , context.pathParam("year"));

    monthlyStep.put( "month" , context.pathParam("month"));

    eventBus.request("monthlySteps",monthlyStep,messageAsyncResult -> {

      if(messageAsyncResult.succeeded()){

        Long totalStep = (Long) messageAsyncResult.result().body();

        forwardJsonOrStatusCode(context,new JsonObject().put("monthly step",totalStep));
      }else {

        sendBadGateway(context,messageAsyncResult.cause());
      }

    });

  }

  private void dailySteps(RoutingContext context) {

    JsonObject dailyStep = new JsonObject();

    dailyStep.put( "deviceId" , 1000);

    dailyStep.put( "year" , context.pathParam("year"));

    dailyStep.put( "month" , context.pathParam("month"));

    dailyStep.put( "day" , context.pathParam("day"));

    eventBus.request("dailySteps",dailyStep,messageAsyncResult -> {

      if(messageAsyncResult.succeeded()){

        Long totalStep = (Long) messageAsyncResult.result().body();

        forwardJsonOrStatusCode(context,new JsonObject().put("daily step",totalStep));
      }else {

        sendBadGateway(context,messageAsyncResult.cause());
      }

    });

  }

  private void yearlySteps(RoutingContext context) {

    JsonObject yearlyStep = new JsonObject();

    yearlyStep.put( "deviceId" , 1000);

    yearlyStep.put( "year" , context.pathParam("year"));

    eventBus.request("yearlySteps",yearlyStep,messageAsyncResult -> {

      if(messageAsyncResult.succeeded()){

        Long totalStep = (Long) messageAsyncResult.result().body();

        forwardJsonOrStatusCode(context,new JsonObject().put("yearly Steps ", totalStep));
      }else {

        sendBadGateway(context,messageAsyncResult.cause());
      }

    });

  }
}
