package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

  GamePanel gp;
  KeyHandler keyH;

  public final int screenX;
  public final int screenY;

  int hasKey = 0;

  public Player(GamePanel gp, KeyHandler keyH) {
    this.gp = gp;
    this.keyH = keyH;

    screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
    screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

    solidArea = new Rectangle();
    solidArea.x = 8;
    solidArea.y = 16;
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;
    solidArea.width = 32;
    solidArea.height = 32;

    setDefaultValues();
    getPlayerImage();
  }

  public void setDefaultValues() {
    worldX = gp.tileSize * 13;
    worldY = gp.tileSize * 27;
    speed = 3;
    direction = "down";
  }

  public void getPlayerImage() {
    try {
      up1 =
        ImageIO.read(getClass().getResourceAsStream("/res/player/back-1.png"));
      up2 =
        ImageIO.read(getClass().getResourceAsStream("/res/player/back-2.png"));
      down1 =
        ImageIO.read(getClass().getResourceAsStream("/res/player/front-1.png"));
      down2 =
        ImageIO.read(getClass().getResourceAsStream("/res/player/front-2.png"));
      left1 =
        ImageIO.read(getClass().getResourceAsStream("/res/player/left-1.png"));
      left2 =
        ImageIO.read(getClass().getResourceAsStream("/res/player/left-2.png"));
      right1 =
        ImageIO.read(getClass().getResourceAsStream("/res/player/right-1.png"));
      right2 =
        ImageIO.read(getClass().getResourceAsStream("/res/player/right-2.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void update() {
    if (
      keyH.upPressed == true ||
      keyH.downPressed == true ||
      keyH.leftPressed == true ||
      keyH.rightPressed == true
    ) {
      if (keyH.upPressed) {
        direction = "up";
      } else if (keyH.downPressed) {
        direction = "down";
      } else if (keyH.leftPressed) {
        direction = "left";
      } else if (keyH.rightPressed) {
        direction = "right";
      }

      // Check Tile Collision
      collisionOn = false;
      gp.cChecker.checkTile(this);

      // Check Object Collision
      int objIndex = gp.cChecker.checkObject(this, true);
      pickUpObject(objIndex);

      // If Collision false, player can move
      if (collisionOn == false) {
        switch (direction) {
          case "up":
            worldY -= speed;
            break;
          case "down":
            worldY += speed;
            break;
          case "left":
            worldX -= speed;
            break;
          case "right":
            worldX += speed;
            break;
        }
      }

      spriteCounter++;
      if (spriteCounter > 10) {
        if (spriteNum == 1) {
          spriteNum = 2;
        } else if (spriteNum == 2) {
          spriteNum = 1;
        }
        spriteCounter = 0;
      }
    }
  }

  private long lastSoundPlayTime = 0;

  public void pickUpObject(int i) {
    if (i != 999) {
      String objectName = gp.obj[i].name;

      switch (objectName) {
        case "Key":
          gp.playSE(1, 0);
          hasKey++;
          gp.obj[i] = null;
          System.out.println("Keys: " + hasKey);
          break;
        case "Door":
          if (hasKey > 0) {
            gp.playSE(1, 0);
            gp.obj[i] = null;
            hasKey--;
            System.out.println("Keys: " + hasKey);
          } else if (hasKey == 0) {
            long currentTime = System.currentTimeMillis();

            if (currentTime - lastSoundPlayTime > 1000) {
              gp.playSE(3, -1);
              lastSoundPlayTime = currentTime;
            }
          }
          break;
      }
    }
  }

  // *** Maybe I can put audio for footsteps here so they'll play as the image changes.
  public void draw(Graphics2D g2) {
    BufferedImage image = null;

    switch (direction) {
      case "up":
        if (spriteNum == 1) {
          image = up1;
        }
        if (spriteNum == 2) {
          image = up2;
        }

        break;
      case "down":
        if (spriteNum == 1) {
          image = down1;
        }
        if (spriteNum == 2) {
          image = down2;
        }

        break;
      case "left":
        if (spriteNum == 1) {
          image = left1;
        }
        if (spriteNum == 2) {
          image = left2;
        }

        break;
      case "right":
        if (spriteNum == 1) {
          image = right1;
        }
        if (spriteNum == 2) {
          image = right2;
        }

        break;
    }
    g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
  }
}
