package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_FenceHorizontalLeft extends SuperObject{


  public OBJ_FenceHorizontalLeft(GamePanel gp) {
    name = "FenceHorizontalLeft";
    try {
      image =
        ImageIO.read(getClass().getResourceAsStream("/res/objects/fence-horizontal-left.png"));
        uTool.scaleImage(image, gp.tileSize, gp.tileSize);
    } catch (IOException e) {
      e.printStackTrace();
    }
    collision = true;
  }

}
