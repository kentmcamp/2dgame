package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_FenceVerticleRight extends SuperObject {

    public OBJ_FenceVerticleRight() {
        name = "FenceVerticleRight";
        try {
          image =
            ImageIO.read(
              getClass()
                .getResourceAsStream("/res/objects/fence-verticle-right.png")
            );
        } catch (IOException e) {
          e.printStackTrace();
        }
        collision = true;
      }

}
