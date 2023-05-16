package com.example.Steps_tracker.publicAPI;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.ext.auth.KeyStoreOptions;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.client.WebClient;
import io.vertx.core.http.HttpMethod;

import java.util.HashSet;
import java.util.Set;

public class publicApiVerticle extends AbstractVerticle {

  private static final int HTTP_PORT = 4000;

  private static final Logger logger = LoggerFactory.getLogger(publicApiVerticle.class);

  private WebClient webClient;

  private JWTAuth jwtAuth;

  public void start(Promise<Void> startPromise){

    jwtAuth = JWTAuth.create(vertx, new JWTAuthOptions()
      .setKeyStore(new KeyStoreOptions()
        .setType("jks")
        .setPath("server-keystore.jks")
        .setPassword("sankalp")));

    Router router = Router.router(vertx);

    Set<String> allowHeaders = new HashSet<>();

    allowHeaders.add("x-requested-with");

    allowHeaders.add("Access-Control-Allow-Origin");

    allowHeaders.add("origin");

    allowHeaders.add("Content-Type");

    allowHeaders.add("accept");

    allowHeaders.add("Authorization");

    Set<HttpMethod> allowMethods = new HashSet<>();

    allowMethods.add(HttpMethod.GET);

    allowMethods.add(HttpMethod.POST);

    allowMethods.add(HttpMethod.OPTIONS);

    allowMethods.add(HttpMethod.PUT);

    router.route().handler(CorsHandler
      .create("*")
      .allowedHeaders(allowHeaders)
      .allowedMethods(allowMethods));

  }


}
