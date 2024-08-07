package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

public class Entity {

  GamePanel gp;
  public int worldX, worldY;
  public int speed;

  public BufferedImage up0, up1, up2, down0, down1, down2, left0, left1, left2, right0, right1, right2;
  public String direction;

  public int spriteCounter = 0;
  public int spriteNum = 1;

  public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
  public int solidAreaDefaultX, solidAreaDefaultY;
  public boolean collisionOn = false;

  public int actionLockCounter = 0;
  String dialogues[] = new String[20];
  int dialogueIndex = 0;

  public Entity(GamePanel gp) {
    this.gp = gp;
  }

  public void setAction() {}
  public void speak() {
    if (dialogues[dialogueIndex] == null) {
      dialogueIndex = 0;
    }

    gp.ui.currentDialogue = dialogues[dialogueIndex];
    dialogueIndex++;

    switch(gp.player.direction) {
      case "up":
        direction = "down";
        break;
      case "down":
        direction = "up";
        break;
      case "left":
        direction = "right";
        break;
      case "right":
        direction = "left";
        break;
    }
  }
  public void update() {
    setAction();

    collisionOn = false;
    gp.cChecker.checkTile(this);
    gp.cChecker.checkObject(this, false);
    gp.cChecker.checkPlayer(this);

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
  }

  public void draw(Graphics2D g2) {
    BufferedImage image = null;

    int screenX = worldX - gp.player.worldX + gp.player.screenX;
    int screenY = worldY - gp.player.worldY + gp.player.screenY;

    if (
      worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
      worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
      worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
      worldY - gp.tileSize < gp.player.worldY + gp.player.screenY
    ) {
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
      g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
  }

  public BufferedImage setup(String imagePath) {
    UtilityTool uTool = new UtilityTool();
    BufferedImage image = null;

    String path = "/res/" + imagePath + ".png";
    try (InputStream is = getClass().getResourceAsStream(path)) {
        if (is != null) {
            image = ImageIO.read(is);
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } else {
            System.out.println("Resource not found at: " + path);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    if (image == null) {
        throw new IllegalArgumentException("Image not found: " + imagePath);
    }

    return image;
}
}
