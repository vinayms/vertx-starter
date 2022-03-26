package com.vins.vertex_starter.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class VerticleB extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    System.out.println("Started "+ getClass().getName());
    vertx.deployVerticle(new VerticleBB());
    startPromise.complete();
  }
}
