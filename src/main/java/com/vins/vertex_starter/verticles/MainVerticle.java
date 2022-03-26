package com.vins.vertex_starter.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.util.UUID;

public class MainVerticle extends AbstractVerticle {

  public static void main(String[] args) {
    final Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new com.vins.vertex_starter.verticles.MainVerticle());
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    System.out.println("Started "+getClass().getName());
    vertx.deployVerticle(new VerticleA());
    vertx.deployVerticle(new VerticleB());
    vertx.deployVerticle(VerticleN.class.getName(),
      new DeploymentOptions()
        .setInstances(4)
        .setConfig(new JsonObject()
          .put("ID", UUID.randomUUID().toString())
          .put("name", VerticleN.class.getSimpleName())
          .put("featureEnabled", "true")
        ));
    startPromise.complete();
  }
}
