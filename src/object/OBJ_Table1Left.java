package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Table1Left extends SuperObject {

      public OBJ_Table1Left() {
    name = "Table1Left";
    try {
      image =
        ImageIO.read(getClass().getResourceAsStream("/res/objects/table1-left.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    collision = true;
  }

}
