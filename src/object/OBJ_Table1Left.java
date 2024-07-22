package object;

import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

public class OBJ_Table1Left extends SuperObject {



  public OBJ_Table1Left(GamePanel gp) {

    name = "Table1Left";
    try {
      image =
        ImageIO.read(
          getClass().getResourceAsStream("/res/objects/table1-left.png")
        );
      uTool.scaleImage(image, gp.tileSize, gp.tileSize);
    } catch (IOException e) {
      e.printStackTrace();
    }
    collision = true;
  }
}
