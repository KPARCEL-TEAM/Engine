package com.kpe;

import com.kpe.verticle.EngineVerticle;
import io.vertx.core.Launcher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
  public class Application extends Launcher {

    public static void main(String[] args) {
      new Application().dispatch(new String[] { "run", EngineVerticle.class.getName() });
    }
}
