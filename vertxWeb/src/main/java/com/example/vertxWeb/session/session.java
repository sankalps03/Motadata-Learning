package com.example.vertxWeb.session;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;


public class session extends AbstractVerticle {

  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();

    vertx.deployVerticle(session.class.getName());

  }

  @Override
  public void start() {

    try {

    Router router = Router.router(vertx);

    router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx)).setSessionTimeout(5000));

    router.route().handler(routingContext -> {

      Session session = routingContext.session();

      Integer count = session.get("hitcount");

      count = (count == null ? 0 : count) + 1;

      session.put("hitcount", count);

      routingContext.response().putHeader("content-type", "text/html")
        .end("<html><body><h1>Hitcount: " + count + "</h1></body></html>");

      if (count >= 10){

        session.destroy();

      }
    });

    vertx.createHttpServer().requestHandler(router).listen(8888).onSuccess(done -> System.out.println("listening on port 8888"));

  }catch (Exception e){

      e.printStackTrace();
    }
  }
}
