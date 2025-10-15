package Main;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        JFrame windnow = new JFrame();
        windnow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windnow.setResizable(false);
        windnow.setTitle("Hokus Pokus");

        GamePanel gamePanel = new GamePanel();
        windnow.add(gamePanel);

        windnow.pack();

        windnow.setLocationRelativeTo(null);
        windnow.setVisible(true);
        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}