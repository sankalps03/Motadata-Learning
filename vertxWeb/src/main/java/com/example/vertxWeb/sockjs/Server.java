package com.example.vertxWeb.sockjs;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.SockJSBridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;

import java.text.DateFormat;
import java.time.Instant;
import java.util.Date;

public class Server extends AbstractVerticle {

  public static void main(String[] args) {

    Vertx vertx1 =Vertx.vertx();

    vertx1.deployVerticle(Server.class.getName());

  }

  @Override
  public void start() throws Exception {

    Router router = Router.router(vertx);

    SockJSBridgeOptions opts = new SockJSBridgeOptions()

      .addOutboundPermitted(new PermittedOptions().setAddress("feed"));

    SockJSHandler ebHandler = SockJSHandler.create(vertx);

    router.mountSubRouter("/eventbus", ebHandler.bridge(opts));

    router.route().handler(StaticHandler.create().setWebRoot("sockjs"));

    vertx.createHttpServer().requestHandler(router).listen(8080).onSuccess(done ->{

      System.out.println("listening on port 8080");
    });

    EventBus eBus = vertx.eventBus();

    vertx.setPeriodic(1000, t -> {

      String timestamp = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(Date.from(Instant.now()));

      eBus.send("feed", new JsonObject().put("now", timestamp));
    });
  }
}
