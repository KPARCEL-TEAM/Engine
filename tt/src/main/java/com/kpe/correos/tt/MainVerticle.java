package com.kpe.correos.tt;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

//public class MainVerticle extends AbstractVerticle {
/*
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    vertx.createHttpServer().requestHandler(req -> {
      req.response()
        .putHeader("content-type", "text/plain")
        .end("Hello from Vert.x!");
    }).listen(8888, http -> {
      if (http.succeeded()) {
        startPromise.complete();
        System.out.println("HTTP server started on port 8888");
      } else {
        startPromise.fail(http.cause());
      }
    });
  }

 */
  public class MainVerticle extends AbstractVerticle {

    // Convenience method so you can run it in your IDE
    public static void main(String[] args) {
      Runner.runExample(SimpleREST.class);
    }

    private Map<String, JsonObject> products = new HashMap<>();

    @Override
    public void start() {

      setUpInitialData();

      Router router = Router.router(vertx);
      router.route().handler(BodyHandler.create());
      router.route("").
      router.get("/products/:productID").handler(this::handleGetProduct);
      router.put("/products/:productID").handler(this::handleAddProduct);
      router.get("/products").handler(this::handleListProducts);

      vertx.createHttpServer().requestHandler(router).listen(8080);
    }

    private void handleGetProduct(RoutingContext routingContext) {
      String productID = routingContext.request().getParam("productID");
      HttpServerResponse response = routingContext.response();
      if (productID == null) {
        sendError(400, response);
      } else {
        JsonObject product = products.get(productID);
        if (product == null) {
          sendError(404, response);
        } else {
          response.putHeader("content-type", "application/json").end(product.encodePrettily());
        }
      }
    }

    private void handleAddProduct(RoutingContext routingContext) {
      String productID = routingContext.request().getParam("productID");
      HttpServerResponse response = routingContext.response();
      if (productID == null) {
        sendError(400, response);
      } else {
        JsonObject product = routingContext.getBodyAsJson();
        if (product == null) {
          sendError(400, response);
        } else {
          products.put(productID, product);
          response.end();
        }
      }
    }

    private void handleListProducts(RoutingContext routingContext) {
      JsonArray arr = new JsonArray();
      products.forEach((k, v) -> arr.add(v));
      routingContext.response().putHeader("content-type", "application/json").end(arr.encodePrettily());
    }

    private void sendError(int statusCode, HttpServerResponse response) {
      response.setStatusCode(statusCode).end();
    }

    private void setUpInitialData() {
      addProduct(new JsonObject().put("id", "prod3568").put("name", "Egg Whisk").put("price", 3.99).put("weight", 150));
      addProduct(new JsonObject().put("id", "prod7340").put("name", "Tea Cosy").put("price", 5.99).put("weight", 100));
      addProduct(new JsonObject().put("id", "prod8643").put("name", "Spatula").put("price", 1.00).put("weight", 80));
    }

    private void addProduct(JsonObject product) {
      products.put(product.getString("id"), product);
    }
  }
}
