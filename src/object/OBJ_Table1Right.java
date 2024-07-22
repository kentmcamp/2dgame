package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Table1Right extends SuperObject {

      public OBJ_Table1Right() {
    name = "Table1Right";
    try {
      image =
        ImageIO.read(getClass().getResourceAsStream("/res/objects/table1-right.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    collision = true;
  }

}
