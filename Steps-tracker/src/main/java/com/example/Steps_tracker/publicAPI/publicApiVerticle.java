package com.example.Steps_tracker.publicAPI;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBus;
import io.vertx.ext.auth.JWTOptions;
import io.vertx.ext.auth.KeyStoreOptions;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import io.vertx.ext.web.handler.JWTAuthHandler;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class publicApiVerticle extends AbstractVerticle {

  private static final int HTTP_PORT = 8080;

  private static final Logger logger = LoggerFactory.getLogger(publicApiVerticle.class);

  private JWTAuth jwtAuth;

  EventBus eventBus;

  public void start(Promise<Void> startPromise) {

    try {

    jwtAuth = JWTAuth.create(vertx, new JWTAuthOptions()
      .setKeyStore(new KeyStoreOptions()
        .setType("jks")
        .setPath("server-keystore.jks")
        .setPassword("sankalp")));

    Router router = Router.router(vertx);

    eventBus = getVertx().eventBus();

    BodyHandler bodyHandler = BodyHandler.create();

    router.post().handler(bodyHandler);

    String prefix = "/api/v1";

    router.post(prefix + "/register").handler(this::register);

    router.post(prefix + "/token").handler(this::token);

//    router.get(prefix + "/:username/*").handler(JWTAuthHandler.create(jwtAuth));

    router.get(prefix + "/:username").handler(this::fetchUser);

    router.get(prefix + "/:username/total").handler(this::totalSteps);

      router.get(prefix + "/:username/:year").handler(this::yearlySteps);

    router.get(prefix + "/:username/:year/:month").handler(this::monthlySteps);

    router.get(prefix + "/:username/:year/:month/:day").handler(this::dailySteps);

    vertx.createHttpServer().requestHandler(router).listen(HTTP_PORT).onSuccess(done -> logger.info("Listening on port 4000"));

    startPromise.complete();

  }catch (Exception exception){
      logger.error(exception.getMessage());
      exception.printStackTrace();
    }
  }

  private void checkUser(RoutingContext context) {

    String subject = context.user().principal().getString("sub");

    if (!context.pathParam("username").equals(subject)) {

      sendStatusCode(context, 403);
    } else {
      context.next();
    }
  }

  private void register(RoutingContext context) {

    System.out.println(context.body().asString());

    JsonObject userData = context.body().asJsonObject();

    logger.info("user data" + userData.toString());

    eventBus.request("register" ,userData,reply -> {if (reply.succeeded()){

      sendStatusCode(context,200);

//      context.response().end("user added");
    }
    else {

      sendBadGateway(context,reply.cause());
    }
    });
  }

  private void sendStatusCode(RoutingContext context, int code) {

    context.response().setStatusCode(code).end();

  }

  private void sendBadGateway(RoutingContext context, Throwable error) {

    logger.error("Bad Gateway", error);

    context.fail(502);
  }

  private void token(RoutingContext context) {

    JsonObject payload = context.body().asJsonObject();

    String username = payload.getString("username");

    eventBus.request("token" ,payload,reply ->{

      if(reply.succeeded()){
        fetchUser(context)
          .onSuccess(details -> {String deviceID =details.getString("deviceId");
            makeJwtToken(username,deviceID).onComplete(result ->{

              if (result.succeeded()){

                sendToken(context,result.result());
              }
              else {
                handleAuthError(context,result.cause());
              }
          });
          });
      }
    });

  }


  private Future<String> makeJwtToken(String username, String deviceId) {

    Promise<String> promise = Promise.promise();

    JsonObject claims = new JsonObject()
      .put("deviceId", deviceId);

    JWTOptions jwtOptions = new JWTOptions()
      .setAlgorithm("RS256")
      .setExpiresInMinutes(10_080) // 7 days
      .setIssuer("10k-steps-api")
      .setSubject(username);

    String token = jwtAuth.generateToken(claims, jwtOptions);

    promise.complete(token);

    return promise.future() ;

  }

  private void handleAuthError(RoutingContext context, Throwable error) {

    logger.error("Authentication error", error);

    context.fail(401);
  }

  private void sendToken(RoutingContext context, String token) {

    context.response().putHeader("Content-Type", "application/jwt").end(token);
  }

  private Future<JsonObject> fetchUser(RoutingContext context) {

    Promise<JsonObject> promise = Promise.promise();

    eventBus.request("fetchUser",context.body().asJsonObject(),reply ->{

      JsonObject userData = (JsonObject) reply.result().body();

      if (reply.succeeded()){

        forwardJsonOrStatusCode(context,userData);

        promise.complete(userData);
      }
      else {

        sendBadGateway(context,reply.cause());

        promise.fail(reply.cause());
      }

    });
    return promise.future();
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

        JsonObject totalStep = (JsonObject) messageAsyncResult;

        forwardJsonOrStatusCode(context,totalStep);
      }else {

        sendBadGateway(context,messageAsyncResult.cause());
      }

    });

  }

  private void monthlySteps(RoutingContext context) {

    JsonObject monthlyStep = new JsonObject();

    monthlyStep.put( "deviceId" , context.user().principal().getString("deviceId"));

    monthlyStep.put( "year" , context.pathParam("year"));

    monthlyStep.put( "month" , context.pathParam("month"));

    eventBus.request("monthlySteps",monthlyStep,messageAsyncResult -> {

      if(messageAsyncResult.succeeded()){

        JsonObject totalStep = (JsonObject) messageAsyncResult;

        forwardJsonOrStatusCode(context,totalStep);
      }else {

        sendBadGateway(context,messageAsyncResult.cause());
      }

    });

  }

  private void dailySteps(RoutingContext context) {

    JsonObject dailyStep = new JsonObject();

    dailyStep.put( "deviceId" , context.user().principal().getString("deviceId"));

    dailyStep.put( "year" , context.pathParam("year"));

    dailyStep.put( "month" , context.pathParam("month"));

    dailyStep.put( "day" , context.pathParam("day"));

    eventBus.request("dailySteps",dailyStep,messageAsyncResult -> {

      if(messageAsyncResult.succeeded()){

        JsonObject totalStep = (JsonObject) messageAsyncResult;

        forwardJsonOrStatusCode(context,totalStep);
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

        JsonObject totalStep = (JsonObject) messageAsyncResult;

        forwardJsonOrStatusCode(context,totalStep);
      }else {

        sendBadGateway(context,messageAsyncResult.cause());
      }

    });

  }
}
