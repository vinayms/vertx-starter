package com.vins.vertex_starter.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VercleAA extends AbstractVerticle {

  private static final Logger LOG = LoggerFactory.getLogger(VercleAA.class);

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    LOG.info("Start {} ",getClass().getName());
    startPromise.complete();
  }

  @Override
  public void stop(Promise<Void> stopPromise) throws Exception {
    LOG.info("Stop {} ", getClass().getName());
    stopPromise.complete();
  }
}
