package com.example.vertxWeb.Router;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class PutRequest extends AbstractVerticle {
  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();

    vertx.deployVerticle(PutRequest.class.getName(), new DeploymentOptions().setInstances(3));

  }

  @Override
  public void start(Promise<Void> startPromise) {
    try {

      Router router = Router.router(vertx);

      router.route(HttpMethod.PUT, "/login").handler(PutRequest::handleLogin);

      vertx.createHttpServer().requestHandler(router).listen(8080).onSuccess(ok -> System.out.println("Server listening on PORT 8080"));

    } catch (Exception e) {

      e.printStackTrace();
    }
  }

  private static void handleLogin(RoutingContext context) {
    HttpServerResponse response = context.response();

    String password = context.request().getParam("password");

  //  context.request().bodyHandler(buffer -> {
//
//      String password = buffer.toString();

      System.out.println(password);

      if (password == null) {
        response.putHeader("content-type", "text/plain");

        response.putHeader("content-length", "13");

        response.setStatusCode(400);

        response.write("null password");

        response.end();
      } else if (password.equals("12345")) {
        response.putHeader("content-type", "text/plain");

        response.putHeader("content-length", "16");

        response.setStatusCode(200).setStatusMessage("Login Successful");

        response.write("Login Successful");

        response.end();
      } else {
        response.putHeader("content-type", "text/plain");

        response.putHeader("content-length", "18");

        response.setStatusCode(401).setStatusMessage("Incorrect Password");

        response.write("Incorrect password");

        response.end();
      }
//    });
  }
}
