package com.vins.vertex_starter.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VerticleN extends AbstractVerticle {

  private static final Logger LOG = LoggerFactory.getLogger(VerticleN.class);

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    LOG.info("Started {} on the thread {} with config {} ", getClass().getName(), Thread.currentThread().getName() , config().toString());
    vertx.deployVerticle(new VerticleBB());
    startPromise.complete();
  }
}
