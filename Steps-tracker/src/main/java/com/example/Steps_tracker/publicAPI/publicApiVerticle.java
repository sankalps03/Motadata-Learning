package com.example.Steps_tracker.publicAPI;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.ext.auth.JWTOptions;
import io.vertx.ext.auth.KeyStoreOptions;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import io.vertx.ext.web.handler.JWTAuthHandler;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.client.predicate.ResponsePredicate;
import io.vertx.ext.web.codec.BodyCodec;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.HashSet;
import java.util.Set;

public class publicApiVerticle extends AbstractVerticle {

  private static final int HTTP_PORT = 8080;

  private static final Logger logger = LoggerFactory.getLogger(publicApiVerticle.class);

  private WebClient webClient;

  private JWTAuth jwtAuth;

  public void start(Promise<Void> startPromise) {

    try {

    jwtAuth = JWTAuth.create(vertx, new JWTAuthOptions()
      .setKeyStore(new KeyStoreOptions()
        .setType("jks")
        .setPath("server-keystore.jks")
        .setPassword("sankalp")));

    Router router = Router.router(vertx);

    EventBus eventBus = getVertx().eventBus();

    BodyHandler bodyHandler = BodyHandler.create();

    router.post().handler(bodyHandler);

    router.post().handler(bodyHandler);

    String prefix = "/api/v1";

    router.post(prefix + "/register").handler(this::register);

    router.post(prefix + "/token").handler(this::token);

    router.get(prefix + "/:username/*").handler(JWTAuthHandler.create(jwtAuth));

    router.get(prefix + "/:username").handler(this::checkUser).handler(this::fetchUser);

    router.get(prefix + "/:username").handler(this::checkUser).handler(this::updateUser);

    router.get(prefix + "/:username").handler(this::checkUser).handler(this::totalSteps);

    router.get(prefix + "/:username").handler(this::checkUser).handler(this::monthlySteps);

    router.get(prefix + "/:username").handler(this::checkUser).handler(this::dailySteps);

    webClient = WebClient.create(vertx);

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

    EventBus eventBus = vertx.eventBus();

    System.out.println(context.body().asString());

    JsonObject userData = context.body().asJsonObject();

    logger.info("user data" + userData.toString());

    eventBus.request("register" ,userData,reply -> {if (reply.succeeded()){

      sendStatusCode(context,200);

      context.response().end("user added");
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

//    EventBus eventBus = vertx.eventBus();
//
//    eventBus.request("token" ,payload,reply ->{
//
//      if(reply.succeeded()){
//        fetchUserDetails(username)
//          .onSuccess(details -> details.body().getString("deviceId"))
//          .onSuccess(deviceID -> makeJwtToken(username,deviceID))
//          .onComplete(result ->{
//
//            if (result.succeeded()){
//
//              sendToken(context,result.result().bodyAsString());
//            }
//            else {
//              handleAuthError(context,result.cause());
//            }
//
//
//          });
//      }
//    });

      webClient.post(3000, "localhost", "authenticate")
      .expect(ResponsePredicate.SC_SUCCESS)
      .sendJson(payload)
      .flatMap(resp -> fetchUserDetails(username))
      .map(resp -> resp.body().getString("deviceID"))
      .map(deviceID -> makeJwtToken(username, deviceID))
      .onComplete(result->{



      });
  }

  private Future<HttpResponse<JsonObject>> fetchUserDetails(String username) {
    return webClient
      .get(3000, "localhost", "/" + username)
      .expect(ResponsePredicate.SC_OK)
      .as(BodyCodec.jsonObject())
      .send();
  }

  private String makeJwtToken(String username, String deviceId) {
    JsonObject claims = new JsonObject()
      .put("deviceId", deviceId);

    JWTOptions jwtOptions = new JWTOptions()
      .setAlgorithm("RS256")
      .setExpiresInMinutes(10_080) // 7 days
      .setIssuer("10k-steps-api")
      .setSubject(username);

    return jwtAuth.generateToken(claims, jwtOptions);

  }

  private void handleAuthError(RoutingContext context, Throwable error) {

    logger.error("Authentication error", error);

    context.fail(401);
  }

  private void sendToken(RoutingContext context, String token) {

    context.response().putHeader("Content-Type", "application/jwt").end(token);
  }

  private void fetchUser(RoutingContext context) {

    EventBus eventBus = vertx.eventBus();

    eventBus.request("fetchUser",context.body().asJsonObject(),reply ->{

      JsonObject user = (JsonObject) reply.result().body();

      if (reply.succeeded()){

        forwardJsonOrStatusCode(context,user);
      }
      else {

        sendBadGateway(context,reply.cause());
      }

    });}

  private void forwardJsonOrStatusCode(RoutingContext ctx,JsonObject resp) {

      ctx.response()
        .putHeader("Content-Type", "application/json")
        .end(resp.encode());

  }

  private void  updateUser(RoutingContext context) {

    webClient.put(3000, "localhost", "/" + context.pathParam("username"))
      .putHeader("Content-Type", "application/json")
      .expect(ResponsePredicate.SC_OK)
      .sendBuffer(context.body().buffer())
      .onComplete(result->{

        if (result.succeeded()){

          context.response().end();
        }
        else {

          sendBadGateway(context,result.cause());
        }

      });
  }

  private void totalSteps(RoutingContext context) {

    String deviceId = context.user().principal().getString("deviceId");

  }

  private void monthlySteps(RoutingContext context) {

    String deviceId = context.user().principal().getString("deviceId");

    String year = context.pathParam("year");

    String month = context.pathParam("month");

  }

  private void dailySteps(RoutingContext context) {

    String deviceId = context.user().principal().getString("deviceId");

    String year = context.pathParam("year");

    String month = context.pathParam("month");

    String day = context.pathParam("day");

  }
}
