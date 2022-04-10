package com.vins.vertex_starter;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(VertxExtension.class)
public class FuturePromiseExample {
  private static final Logger LOGGER = LoggerFactory.getLogger(FuturePromiseExample.class);

  @Test
  void promise_success(Vertx vertx, VertxTestContext vertxTestContext){
    final Promise<String> promise = Promise.promise();
    LOGGER.debug("Start");
    vertx.setTimer(500, id -> {
      promise.complete("Success");
      LOGGER.debug("Success");
      vertxTestContext.completeNow();
    });
    LOGGER.debug("End");
  }

  @Test
  void promise_failure(Vertx vertx, VertxTestContext vertxTestContext){
    final Promise<String> promise = Promise.promise();
    LOGGER.debug("Start");
    vertx.setTimer(500, id -> {
      promise.fail(new RuntimeException("Failed!"));
      LOGGER.debug("Failed");
      vertxTestContext.completeNow();
    });
    LOGGER.debug("End");
  }

  @Test
  void future_scuccess(Vertx vertx, VertxTestContext vertxTestContext){
    final Promise<String> promise = Promise.promise();
    LOGGER.debug("Start");
    vertx.setTimer(500, id -> {
      promise.complete("Success");
      LOGGER.debug("Timer done.");
      vertxTestContext.completeNow();
    });
    final Future<String> future = promise.future();
    future.onSuccess(result -> {
      LOGGER.debug("Result : {} ", result);
      vertxTestContext.completeNow();
    }).onFailure(vertxTestContext::failNow);
    LOGGER.debug("End");
  }

  @Test
  void future_failure(Vertx vertx, VertxTestContext vertxTestContext){
    final Promise<String> promise = Promise.promise();
    LOGGER.debug("Start");
    vertx.setTimer(500, id -> {
      promise.fail(new RuntimeException("Failed"));
      LOGGER.debug("Timer done.");
    });
    final Future<String> future = promise.future();
    future.onSuccess(vertxTestContext::failNow)
    .onFailure(error -> {
      LOGGER.debug("Result : {}", error);
      vertxTestContext.completeNow();
    });
    LOGGER.debug("End");
  }

  @Test
  void future_map(Vertx vertx, VertxTestContext vertxTestContext){
    final Promise<String> promise = Promise.promise();
    LOGGER.debug("Start");
    vertx.setTimer(500, id -> {
      promise.complete("Success");
      LOGGER.debug("Timer done.");
      vertxTestContext.completeNow();
    });
    final Future<String> future = promise.future();
    future
      .map(asString ->{
        LOGGER.debug("Map String to JsonObject");
        return new JsonObject().put("key", asString);
      })
      .map(jsonObject -> new JsonArray().add(jsonObject))
      .onSuccess(result -> {
      LOGGER.debug("Result : {} ", result);
      vertxTestContext.completeNow();
    }).onFailure(vertxTestContext::failNow);
    LOGGER.debug("End");
  }

  @Test
  void future_coordination(Vertx vertx, VertxTestContext vertxTestContext){
    vertx.createHttpServer()
      .requestHandler(request ->{
        LOGGER.debug("{}", request);
      })
      .listen(10000)
      .compose(server -> {
        LOGGER.info("Another task ");
        return Future.succeededFuture(server);
      })
      .onFailure(vertxTestContext::failNow)
      .onSuccess(server -> {
        LOGGER.debug("Server started on port {} ", server.actualPort());
        vertxTestContext.completeNow();
      });
  }
}

