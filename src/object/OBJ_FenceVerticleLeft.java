package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_FenceVerticleLeft extends SuperObject{
      public OBJ_FenceVerticleLeft() {
    name = "FenceVerticleLeft";
    try {
      image =
        ImageIO.read(getClass().getResourceAsStream("/res/objects/fence-verticle-left.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    collision = true;
  }

}
