package com.vins.vertex_starter.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VerticleA extends AbstractVerticle {
  private static final Logger LOG = LoggerFactory.getLogger(VerticleA.class);

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    LOG.info("Started {} ", getClass().getName());
    vertx.deployVerticle(new VercleAA(), whenDeployed -> {
      LOG.info("Deployed {} ",VercleAA.class.getName());
      vertx.undeploy(whenDeployed.result());
    });
    startPromise.complete();
  }
}
