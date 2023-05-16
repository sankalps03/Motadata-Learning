package com.example.vertxWeb.Authorization;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.JksOptions;
import io.vertx.ext.auth.JWTOptions;
import io.vertx.ext.auth.KeyStoreOptions;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.authorization.PermissionBasedAuthorization;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import io.vertx.ext.auth.jwt.authorization.JWTAuthorization;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.JWTAuthHandler;
import io.vertx.ext.web.handler.StaticHandler;


public class authorization extends AbstractVerticle {

  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();

    vertx.deployVerticle(authorization.class.getName());

  }

  @Override
  public void start() throws Exception {

    try {

    Router router = Router.router(vertx);

    JWTAuth jwt = JWTAuth.create(vertx, new JWTAuthOptions()
      .setKeyStore(new KeyStoreOptions()
        .setType("jks")
        .setPath("server-keystore.jks")
        .setPassword("sankalp")));

    router.get("/api/newToken").handler(ctx -> {
      JsonArray authorities = new JsonArray();

      for (String authority : ctx.request().params().getAll("authority")) {
        authorities.add(authority);
      }

      ctx.response().putHeader("Content-Type", "text/plain");
      ctx.response().end(
        jwt.generateToken(new JsonObject().put("permissions", authorities), new JWTOptions().setExpiresInSeconds(60)));
    });

    router.route("/api/protected*").handler(JWTAuthHandler.create(jwt));

    JWTAuthorization authzProvider = JWTAuthorization.create("permissions");

    router.get("/api/protected").handler(ctx -> {

      ctx.response().putHeader("Content-Type", "text/plain");

      ctx.response().end("this secret is not defcon!");
    });

    PermissionBasedAuthorization defcon1 = PermissionBasedAuthorization.create("defcon1");

    router.get("/api/protected/defcon1").handler(ctx -> {

      User user = ctx.user();

      authzProvider.getAuthorizations(user).onComplete(done -> {

        if (done.succeeded()) {

          if (defcon1.match(user)) {

            ctx.response().putHeader("Content-Type", "text/plain");

            ctx.response().end("this secret is defcon1!");
          } else {

            ctx.response().setStatusCode(403).end();
          }
        } else {

          ctx.fail(done.cause());
        }
      });
    });

    PermissionBasedAuthorization defcon2 = PermissionBasedAuthorization.create("defcon2");

    router.get("/api/protected/defcon2").handler(ctx -> {

      User user = ctx.user();

      authzProvider.getAuthorizations(user).onComplete(done -> {

        if (done.succeeded()) {


          if (defcon2.match(user)) {

            ctx.response().putHeader("Content-Type", "text/plain");

            ctx.response().end("this secret is defcon2!");

          } else {

            ctx.response().setStatusCode(403).end();
          }
        } else {
          ctx.fail(done.cause());
        }
      });
    });

    PermissionBasedAuthorization defcon3 = PermissionBasedAuthorization.create("defcon3");

    router.get("/api/protected/defcon3").handler(ctx -> {

      User user = ctx.user();

      authzProvider.getAuthorizations(user).onComplete(done -> {

        if (done.succeeded()) {

          if (defcon3.match(user)) {

            ctx.response().putHeader("Content-Type", "text/plain");

            ctx.response().end("this secret is defcon3!");
          } else {

            ctx.response().setStatusCode(403).end();
          }
        } else {
          ctx.fail(done.cause());
        }
      });
    });

    router.route().handler(StaticHandler.create().setWebRoot("Authorization"));

    vertx.createHttpServer(new HttpServerOptions().setSsl(true).setKeyStoreOptions(
      new JksOptions().setPath("server-keystore.jks").setPassword("sankalp"))).requestHandler(router).listen(8080).onSuccess(done-> System.out.println("listening on port 8080"));

  }catch (Exception e){

      e.printStackTrace();
    }
  }
}
