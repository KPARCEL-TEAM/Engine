package com.kpe;

import com.kpe.verticle.EngineVerticle;
import io.vertx.core.Launcher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
  public class ApplicationLauncher extends Launcher {

    public static void main(String[] args) {
      new ApplicationLauncher().dispatch(new String[] { "run", EngineVerticle.class.getName() });
    }
}
