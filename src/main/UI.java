package main;

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

    // OBJ_Key key = new OBJ_Key(gp);
    // keyImage = key.image;
  }

  public void showMessage(String text) {
    message = text;
    messageOn = true;
  }

  public void draw(Graphics2D g2) {
    this.g2 = g2;

    g2.setFont(gameboyFont);
    g2.setColor(Color.WHITE);

    if (gp.gameState == gp.playState) {

    }
    if (gp.gameState == gp.pauseState) {
      drawPauseScreen();
    }
  }

  public void drawPauseScreen() {
    String text  = "PAUSED";
    int x = getXforCenteredText(text);
    int y = gp.screenHeight/2;
    g2.drawString(text, x, y);
  }

  public int getXforCenteredText(String text) {
    int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    int x = gp.screenWidth/2 - length/2;
    return x;

  }
}
