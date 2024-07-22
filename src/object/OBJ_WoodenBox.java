package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_WoodenBox extends SuperObject {

  public OBJ_WoodenBox(GamePanel gp) {
    name = "WoodenBox";
    try {
      image =
        ImageIO.read(getClass().getResourceAsStream("/res/objects/woodenBox.png"));
        uTool.scaleImage(image, gp.tileSize, gp.tileSize);
    } catch (IOException e) {
      e.printStackTrace();
    }
    collision = true;
  }
}
