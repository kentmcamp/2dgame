package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

// For background image
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48
    public final int maxScreenCol = 16; // 16 * 16 = 768
    public final int maxScreenRow = 12; // 12 * 48 = 576
    public final int screenWidth = tileSize * maxScreenCol; // 768
    public final int screenHeight = tileSize * maxScreenRow; // 576

    // Change this to change the amount of tiles in the world
    public final int maxWorldCol = 28; // 27 * 16 = 432
    public final int maxWorldRow = 50; // 50 * 16 = 800

    // Screen Width in Pixels = 256
    // Screen Height in Pixels = 192

    // BACKGROUND IMAGE
    private BufferedImage backgroundImage;
    private UtilityTool uTool = new UtilityTool();

    int FPS = 60;

    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);

    Sound music = new Sound();
    Sound soundEffect = new Sound();

    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    // ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[20];
    public Entity npc[] = new Entity[10];

    // GAME STATES
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        loadBackgroundImage();
    }

    public void loadBackgroundImage() {
        try {
            backgroundImage = ImageIO.read(getClass().getResourceAsStream("/res/images/backgroundGrid.png"));
            backgroundImage = uTool.scaleImage(backgroundImage, screenWidth, screenHeight);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setupGame() {
        aSetter.setObject();
        aSetter.setNPC();
        playMusic(6, 2);
        gameState = playState;
    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS;
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
        if (gameState == playState) {
            music.play();
            music.loop();

            // Player
            player.update();

            // NPC
            for (int i = 0; i < npc.length; i++) {
                if(npc[i] != null) {
                    npc[i].update();
                }
            }
        }
        if (gameState == pauseState) {
            music.stop();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Background Image
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, null);
        }

        Graphics2D g2 = (Graphics2D) g;

        // Debug Tools
        long drawStart = 0;
        if (keyH.checkDrawTime == true) {
            drawStart = System.nanoTime();
        }

        // Tile
        tileM.draw(g2);

        // Object
        for(int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        // NPC
        for(int i = 0; i < npc.length; i++) {
            if(npc[i] != null) {
                npc[i].draw(g2);
            }
        }

        // Player
        player.draw(g2);

        // UI
        ui.draw(g2);

        // Debug Tools
        if (keyH.checkDrawTime == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.WHITE);
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);
        }

        g2.dispose();
    }

    // MUSIC AND SOUND EFFECT
    public void playMusic(int songIndex, float volume) {
        music.setFile(songIndex);
        // music.play();
        // music.loop();
        music.setVolume(volume);
    }
    public void stopMusic() {
        music.stop();
    }
    public void playSE(int soundIndex, float volume) {
        soundEffect.setFile(soundIndex);
        soundEffect.play();
        soundEffect.setVolume(volume);
    }
}
