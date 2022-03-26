package com.vins.vertex_starter.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class VerticleBB extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    System.out.println("Start "+getClass().getName());
    startPromise.complete();
  }
}
