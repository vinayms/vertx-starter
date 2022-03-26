package com.vins.vertex_starter.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class VerticleA extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    System.out.println("Started "+ getClass().getName());
    vertx.deployVerticle(new VercleAA(), whenDeployed -> {
      System.out.println("Deployed "+VercleAA.class.getName());
      vertx.undeploy(whenDeployed.result());
    });
    startPromise.complete();
  }
}
