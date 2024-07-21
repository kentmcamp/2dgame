package main;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) throws Exception {

        JFrame window = new JFrame("Kent's Game-Boy Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // Make the gamePanel focusable so it can listen for key events
        gamePanel.setFocusable(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
