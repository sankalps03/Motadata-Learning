package com.example.vertxWeb.Cookie;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.Cookie;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;


public class Server extends AbstractVerticle {

  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();

//    vertx.deployVerticle(Server.class.getName());

  }

  @Override
  public void start() {

    try {

      Router router = Router.router(vertx);

      router.route().handler(ctx -> {

        Cookie someCookie = ctx.request().getCookie("visits");

        long visits = 0;

        if (someCookie != null) {

          String cookieValue = someCookie.getValue();
          try {
            visits = Long.parseLong(cookieValue);

          } catch (NumberFormatException e) {
            visits = 0;
          }
        }

        visits++;

        ctx.response().addCookie(Cookie.cookie("visits", "" + visits));

        ctx.next();
      });

      router.route().handler(StaticHandler.create().setWebRoot("cookie"));

      vertx.createHttpServer().requestHandler(router).listen(8080)
        .onSuccess(done -> System.out.println("listening on port 8080"));

    }catch (Exception e){

      e.printStackTrace();
    }
  }
}
