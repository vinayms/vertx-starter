package com.vins.vertex_starter.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VerticleBB extends AbstractVerticle {
  private static final Logger LOG = LoggerFactory.getLogger(VerticleBB.class);
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    LOG.info("Start {} ", getClass().getName());
    startPromise.complete();
  }
}
