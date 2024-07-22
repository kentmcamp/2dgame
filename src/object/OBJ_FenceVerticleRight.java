package object;

import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

public class OBJ_FenceVerticleRight extends SuperObject {


  public OBJ_FenceVerticleRight(GamePanel gp) {

    name = "FenceVerticleRight";
    try {
      image =
        ImageIO.read(
          getClass()
            .getResourceAsStream("/res/objects/fence-verticle-right.png")
        );
      uTool.scaleImage(image, gp.tileSize, gp.tileSize);
    } catch (IOException e) {
      e.printStackTrace();
    }
    collision = true;
  }
}
