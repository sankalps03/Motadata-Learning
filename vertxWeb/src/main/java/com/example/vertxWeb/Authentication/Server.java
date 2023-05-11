package com.example.vertxWeb.Authentication;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.auth.properties.PropertyFileAuthentication;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.*;
import io.vertx.ext.web.sstore.LocalSessionStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server extends AbstractVerticle {

  static{

    System.setProperty("vertx.logger-delegate-factory-class-name", "io.vertx.core.logging.SLF4JLogDelegateFactory");

  }


  private static final Logger LOG = LoggerFactory.getLogger(Server.class);

  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();

    vertx.deployVerticle(Server.class.getName());

  }

  @Override
  public void start() {

    try {

      Router router = Router.router(vertx);

      router.route().handler(BodyHandler.create());

      router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx)));

      PropertyFileAuthentication authenticate = PropertyFileAuthentication.create(vertx,"/home/sankalp/MotadataLearning/vertxWeb/src/main/resources/users.properties");

      router.route("/private/*").handler(RedirectAuthHandler.create(authenticate, "/loginPage.html"));

      router.route("/private/*").handler(StaticHandler.create().setCachingEnabled(false).setWebRoot("Private"));

      router.route("/private/*").handler(StaticHandler.create().setCachingEnabled(false).setWebRoot("cookie"));

      router.route("/loginHandler").handler(FormLoginHandler.create(authenticate));

      router.route("/logout").handler(context -> {

        context.clearUser();

        context.response().putHeader("location", "/").setStatusCode(302).end();
      });

      router.route().handler(StaticHandler.create());

      vertx.createHttpServer().requestHandler(router).listen(8080).onSuccess(done -> System.out.println("listening on port 8080"));

    }catch (Exception e){

      e.printStackTrace();
    }
  }
}
