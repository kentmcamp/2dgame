package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import object.OBJ_Key;

public class UI {

  GamePanel gp;
  Font gameboyFont;
  BufferedImage keyImage;
  public boolean messageOn = false;
  public String message = "";
  int messageCounter = 0;
  public boolean gameFinished = false;

  double playTime;

  public UI(GamePanel gp) {
    this.gp = gp;

    try {
      InputStream is = UI.class.getResourceAsStream("/res/ui/GameboyFont.ttf");
      Font font = Font.createFont(Font.TRUETYPE_FONT, is);
      gameboyFont = font.deriveFont(Font.PLAIN, 20f);
    } catch (FontFormatException | IOException e) {
      e.printStackTrace();
    }

    OBJ_Key key = new OBJ_Key(gp);
    keyImage = key.image;
  }

  public void showMessage(String text) {
    message = text;
    messageOn = true;
  }

  public void draw(Graphics2D g2) {
    if (gameFinished == true) {
        g2.setFont(gameboyFont);
        g2.setColor(Color.WHITE);

        String text;
        int textLength;
        int x;
        int y;

        text = "Congratulations!";
        textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth / 2 - textLength / 2;
        y = gp.screenHeight / 2 - (gp.tileSize*2);
        g2.drawString(text, x, y);

        text = "You finished in: " + String.format("%.2f", playTime) + " seconds";
        textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth / 2 - textLength / 2;
        y = gp.screenHeight / 2 + (gp.tileSize*3);
        g2.drawString(text, x, y);

        gp.gameThread = null;

    } else {
      g2.setFont(gameboyFont);
      g2.setColor(Color.WHITE);
      g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
      g2.drawString("x " + gp.player.hasKey, 74, 60);

      // Time
      playTime += (double) 1/60;
      g2.drawString("Time: " + String.format("%.2f", playTime), gp.tileSize*11, gp.tileSize/2);

      // Draw message
      if (messageOn == true) {
        g2.setFont(g2.getFont().deriveFont(15F));
        g2.drawString(message, gp.tileSize / 2, gp.tileSize * 11);

        messageCounter++;

        if (messageCounter > 150) {
          messageCounter = 0;
          messageOn = false;
        }
      }
    }
  }
}
