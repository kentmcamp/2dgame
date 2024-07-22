package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_FenceHorizontalRight extends SuperObject {

  public OBJ_FenceHorizontalRight() {
    name = "FenceHorizontalRight";
    try {
      image =
        ImageIO.read(
          getClass()
            .getResourceAsStream("/res/objects/fence-horizontal-right.png")
        );
    } catch (IOException e) {
      e.printStackTrace();
    }
    collision = true;
  }

}
