package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Torch extends SuperObject {

  public OBJ_Torch(GamePanel gp) {

    name = "Torch";
    try {
      image =
        ImageIO.read(getClass().getResourceAsStream("/res/objects/torch.png"));
        uTool.scaleImage(image, gp.tileSize, gp.tileSize);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
