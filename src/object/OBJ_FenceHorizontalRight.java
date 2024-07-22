package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_FenceHorizontalRight extends SuperObject {

  public OBJ_FenceHorizontalRight(GamePanel gp) {

    name = "FenceHorizontalRight";
    try {
      image =
        ImageIO.read(
          getClass()
            .getResourceAsStream("/res/objects/fence-horizontal-right.png")
        );
        uTool.scaleImage(image, gp.tileSize, gp.tileSize);
    } catch (IOException e) {
      e.printStackTrace();
    }
    collision = true;
  }

}
