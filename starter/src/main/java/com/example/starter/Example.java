package com.example.starter;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
//import io.vertx.ext.jdbc.JDBCClient;
//import io.vertx.ext.sql.SQLConnection;
//import io.vertx.ext.web.Router;
//import io.vertx.ext.web.RoutingContext;
//import io.vertx.ext.web.handler.BodyHandler;
//import io.vertx.ext.web.handler.StaticHandler;


public class Example extends AbstractVerticle
{

//  private JDBCClient jdbcClient;

  public static void main (String[] args)
  {

    Vertx vertx = Vertx.vertx();

    vertx.deployVerticle(Example.class.getName()).onComplete(stringAsyncResult ->
    {
      if ( stringAsyncResult.succeeded() )
      {
        System.out.println("Verticle Deployed Successfully");
      }
      else
      {
        System.out.println("Some error occurred " + stringAsyncResult.cause().getMessage());
      }
    });
  }


//  @Override
//  public void start (Promise< Void > startPromise) throws Exception
//  {
//
//    Router router = Router.router(vertx);
//
//// Serve static resources
//    router.route("/static/*").handler(StaticHandler.create());
//
//// Enable body parsing
//    router.route().handler(BodyHandler.create());
//
//// Serve the login page
//    router.get("/login").handler(this :: serveLoginPage);
//
//// Handle login form submission
//// router.post("/login").handler(this :: handleLogin);
////
//// // Serve the signup page
//// router.get("/signup").handler(this :: serveSignupPage);
////
//// // Handle signup form submission
//// router.post("/signup").handler(this :: handleSignup);
//
//// Create the JDBC client
//    var config = new JsonObject()
//      .put("url", "jdbc:h2:tcp://localhost/~/TESTJDBC")
//      .put("driver_class", "org.h2.Driver")
//      .put("user", "sa")
//      .put("password", "");
//
//    var client = JDBCClient.create(vertx, config);
//
//    HttpServer server = vertx.createHttpServer();
//
//    server.requestHandler(router).listen(8080).onComplete(httpServerAsyncResult ->
//    {
//      if ( httpServerAsyncResult.succeeded() )
//      {
//        System.out.println("Server syarted listening on port no 8080");
//
//        startPromise.complete();
//      }
//      else
//      {
//        startPromise.fail("some error occurred " + httpServerAsyncResult.cause().getMessage());
//      }
//    });
//
//  }


//  private void serveLoginPage (RoutingContext routingContext)
//  {
//    HttpServerResponse response = routingContext.response();
//
//    response.sendFile("login.html");
//  }



}
