package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import object.OBJ_Key;

public class UI {

  GamePanel gp;
  Graphics2D g2;
  Font gameboyFont;

  public boolean messageOn = false;
  public String message = "";
  int messageCounter = 0;
  public boolean gameFinished = false;
  public String currentDialogue = "";

  public UI(GamePanel gp) {
    this.gp = gp;

    try {
      InputStream is = UI.class.getResourceAsStream("/res/ui/GameboyFont.ttf");
      Font font = Font.createFont(Font.TRUETYPE_FONT, is);
      gameboyFont = font.deriveFont(Font.PLAIN, 20f);
    } catch (FontFormatException | IOException e) {
      e.printStackTrace();
    }
  }

  public void showMessage(String text) {
    message = text;
    messageOn = true;
  }

  public void draw(Graphics2D g2) {
    this.g2 = g2;

    g2.setFont(gameboyFont);
    g2.setColor(Color.WHITE);

    // Play State
    if (gp.gameState == gp.playState) {

    }
    // Pause State
    if (gp.gameState == gp.pauseState) {
      drawPauseScreen();
    }
    // Dialogue State
    if (gp.gameState == gp.dialogueState) {
      drawDialogueScreen();
    }
  }

  public void drawPauseScreen() {
    String text  = "PAUSED";
    int x = getXforCenteredText(text);
    int y = gp.screenHeight/3;
    Color lightGreenLight = new Color(207, 241, 206, 255);
    g2.setColor(lightGreenLight);
    g2.drawString(text, x, y);
  }

  public void drawDialogueScreen() {
    // Window
    int width = gp.screenWidth - (gp.tileSize * 2);
    int height = gp.tileSize * 4;
    int x = (gp.screenWidth - width) / 2;
    int y = gp.tileSize * 7;

    drawSubWindow(x, y, width, height);


    x += gp.tileSize;
    y += gp.tileSize;
    for (String line : currentDialogue.split("\n")) {
      g2.drawString(line, x, y);
      y += 40;
    }

}



  public void drawSubWindow(int x, int y, int width, int height) {
    Color darkBlueShade = new Color(7, 19, 29, 255);
    g2.setColor(darkBlueShade);
    g2.fillRoundRect(x, y, width, height, 0, 0);

    Color lightGreenLight = new Color(207, 241, 206, 255);
    g2.setColor(lightGreenLight);
    g2.setStroke(new BasicStroke(5));
    g2.drawRoundRect(x+4, y+4, width-10, height-10, 1, 1);

  }

  public int getXforCenteredText(String text) {
    int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    int x = gp.screenWidth/2 - length/2;
    return x;

  }
}
