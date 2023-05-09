package com.example.starter.httpBasc;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;

public class server extends AbstractVerticle {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();

    vertx.deployVerticle(new server());
  }

  @Override
  public void start() throws Exception {

    HttpServer server = vertx.createHttpServer();

    server.requestHandler(req -> {
      System.out.println(req.remoteAddress());
      req.response().putHeader("content-type", "text/html").end("<html><body><h1>Hello from vert.x!</h1></body></html>");
    });

    server.listen(8080);
  }
}
