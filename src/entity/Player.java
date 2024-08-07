package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity {


  KeyHandler keyH;

  public final int screenX;
  public final int screenY;

  public Player(GamePanel gp, KeyHandler keyH) {

    super(gp);

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
    worldX = gp.tileSize * 20;
    worldY = gp.tileSize * 46;
    speed = 3;
    direction = "down";
  }

  public void getPlayerImage() {
    up0 = setup("player/back-0");
    up1 = setup("player/back-1");
    up2 = setup("player/back-2");
    down0 = setup("player/front-0");
    down1 = setup("player/front-1");
    down2 = setup("player/front-2");
    left0 = setup("player/left-0");
    left1 = setup("player/left-1");
    left2 = setup("player/left-2");
    right0 = setup("player/right-0");
    right1 = setup("player/right-1");
    right2 = setup("player/right-2");
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

      // Check NPC Collision
      int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
      interactNPC(npcIndex);

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
      if (spriteCounter <= 10) {
        spriteNum = 1; // First step
    } else if (spriteCounter <= 20) {
        spriteNum = 0; // Transition to standing
    } else if (spriteCounter <= 30) {
        spriteNum = 2; // Second step
    } else if (spriteCounter <= 40) {
        spriteNum = 0; // Transition back to standing
        spriteCounter = 0; // Reset the counter to loop the animation
    }
} else {
    spriteNum = 0; // Player is standing still
    spriteCounter = 0; // Reset the counter when player stops moving
}
  }

  private long lastSoundPlayTime = 0;

  public void pickUpObject(int i) {
    if (i != 999) {

    }
  }

  public void interactNPC(int i) {
    if (i != 999) {
      System.out.println("Collided with NPC");
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
        if (spriteNum == 0) {
          image = up0;
        }

        break;
      case "down":
        if (spriteNum == 1) {
          image = down1;
        }
        if (spriteNum == 2) {
          image = down2;
        }
        if (spriteNum == 0) {
          image = down0;
        }

        break;
      case "left":
        if (spriteNum == 1) {
          image = left1;
        }
        if (spriteNum == 2) {
          image = left2;
        }
        if (spriteNum == 0) {
          image = left0;
        }

        break;
      case "right":
        if (spriteNum == 1) {
          image = right1;
        }
        if (spriteNum == 2) {
          image = right2;
        }
        if (spriteNum == 0) {
          image = right0;
        }

        break;
    }
    g2.drawImage(image, screenX, screenY, null);
  }
}
