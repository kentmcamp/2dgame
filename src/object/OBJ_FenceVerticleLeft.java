package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_FenceVerticleLeft extends SuperObject{

      public OBJ_FenceVerticleLeft(GamePanel gp) {

    name = "FenceVerticleLeft";
    try {
      image =
        ImageIO.read(getClass().getResourceAsStream("/res/objects/fence-verticle-left.png"));
        uTool.scaleImage(image, gp.tileSize, gp.tileSize);
    } catch (IOException e) {
      e.printStackTrace();
    }
    collision = true;
  }

}
