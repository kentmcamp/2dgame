package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16
    final int scale = 3;

    final int tileSize = originalTileSize * scale; // 48x48

    final int maxScreenCol = 16;
    final int maxScreenRow = 12;

    final int screenWidth = tileSize * maxScreenCol; // 768
    final int screenHeight = tileSize * maxScreenRow; // 576

    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    // Set player position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    // Sleep Method for Game Loop
    // public void run() {

    //     double drawInterval = 1000000000 / FPS; // 0.01666 seconds
    //     double nextDrawTime = System.nanoTime() + drawInterval;

    //     while(gameThread != null) {

    //         update();

    //         repaint();

    //         try {
    //             double remainingTime = nextDrawTime - System.nanoTime();
    //             remainingTime = remainingTime / 1000000;

    //             if (remainingTime < 0) {
    //                 remainingTime = 0;
    //             }

    //             Thread.sleep((long) remainingTime );

    //             nextDrawTime += drawInterval;
    //         } catch (InterruptedException e) {
    //             e.printStackTrace();
    //         }

    //     }
    // }

    // Delta/Accumulated Time Method for Game Loop
    public void run() {

        double drawInterval = 1000000000 / FPS; // 0.01666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();
            timer += (currentTime - lastTime);
            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }


    public void update() {

        if (keyH.upPressed) {
            playerY -= playerSpeed;
        } else if (keyH.downPressed) {
            playerY += playerSpeed;
        } else if (keyH.leftPressed) {
            playerX -= playerSpeed;
        } else if (keyH.rightPressed) {
            playerX += playerSpeed;
        }

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics g2 = (Graphics2D) g;

        g2.setColor(Color.WHITE);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();

    }
}
