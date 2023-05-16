package com.example.vertxWeb;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;


public class BlockingHandlers extends AbstractVerticle
{

  @Override
  public void start (Promise< Void > startPromise) throws Exception
  {
    HttpServer serve = vertx.createHttpServer();

    Router router = Router.router(vertx);

    router.route("/harsh").blockingHandler(context->
    {
      context.response().setChunked(true);

      String s=null;

      for(int i=0;i<1000;i++)
      {
        s+="Hello World from context 1\n";
      }

      System.out.println("I am in context one");

      context.response().write(s);

// context.response().end();

      context.next();
    });

    router.route("/harsh").blockingHandler(routingContext ->
    {
//      routingContext.response().setChunked(true);

      String s=null;

      for(int i=0;i<1000;i++)
      {
        s+="Hello World from context 2\n";
      }

      System.out.println("I am in context two");

      routingContext.response().write(s);

      routingContext.response().end();

    });


    serve.requestHandler(router).listen(8080).onComplete(ready->
    {
      if(ready.succeeded())
      {
        System.out.println("Server is listening on 8080");

        startPromise.complete();
      }
      else
      {
        System.out.println("Some error wa occurred"+ready.cause().getMessage());

        startPromise.fail("Failed");
      }
    });
  }

  public static void main (String[] args)
  {

    Vertx vertx = Vertx.vertx();

    System.out.println("--------------------------Main Thread----"+Thread.currentThread().getName());


    vertx.deployVerticle(BlockingHandlers.class.getName());

  }

}
