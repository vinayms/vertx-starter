package com.vins.vertex_starter.worker;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkerExample extends AbstractVerticle {
  private static final Logger LOG = LoggerFactory.getLogger(WorkerExample.class);

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new WorkerExample());
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    vertx.deployVerticle(new WorkerVerticle(),
      new DeploymentOptions()
        .setWorker(true)
        .setWorkerPoolSize(1)
        .setWorkerPoolName("my-worker-verticle")
    );
    startPromise.complete();
    vertx.executeBlocking(event -> {
      LOG.info("Executing the blocking code.");
      try {
        Thread.sleep(5000);
        event.complete();
      }catch (InterruptedException interruptedException){
        LOG.error("Failed : {}", interruptedException);
        event.fail(interruptedException);
      }
    }, result -> {
      if(result.succeeded()){
        LOG.info("Blocking call done.");
      } else{
        LOG.info("Blocking call failed due to {} ", result.cause());
      }
    });
  }
}

