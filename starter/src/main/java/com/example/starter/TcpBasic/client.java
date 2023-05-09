package com.example.starter.TcpBasic;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.file.FileSystem;
import io.vertx.core.net.NetSocket;

public class client extends AbstractVerticle {
  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();

    vertx.deployVerticle(new client());
  }

  @Override
  public void start() throws Exception {

    vertx.createNetClient().connect(9999, "localhost", res -> {

      if (res.succeeded()) {

        NetSocket socket = res.result();

        socket.handler(buffer -> System.out.println(buffer.toString()));

      } else {

        res.cause().printStackTrace();

      }
    });
  }
}
