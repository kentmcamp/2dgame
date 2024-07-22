package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Table1Right extends SuperObject {


    public OBJ_Table1Right(GamePanel gp) {

    name = "Table1Right";
    try {
      image =
        ImageIO.read(getClass().getResourceAsStream("/res/objects/table1-right.png"));
        uTool.scaleImage(image, gp.tileSize, gp.tileSize);
    } catch (IOException e) {
      e.printStackTrace();
    }
    collision = true;
  }

}
