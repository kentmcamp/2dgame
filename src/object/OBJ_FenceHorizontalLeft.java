package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_FenceHorizontalLeft extends SuperObject{


  public OBJ_FenceHorizontalLeft() {
    name = "FenceHorizontalLeft";
    try {
      image =
        ImageIO.read(getClass().getResourceAsStream("/res/objects/fence-horizontal-left.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    collision = true;
  }

}
