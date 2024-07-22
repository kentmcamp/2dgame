package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_WoodenBox extends SuperObject {

  public OBJ_WoodenBox() {
    name = "WoodenBox";
    try {
      image =
        ImageIO.read(getClass().getResourceAsStream("/res/objects/woodenBox.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    collision = true;
  }
}
