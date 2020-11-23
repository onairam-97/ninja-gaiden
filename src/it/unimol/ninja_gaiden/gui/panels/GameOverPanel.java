package it.unimol.ninja_gaiden.gui.panels;

import it.unimol.ninja_gaiden.gui.utils.Resources;

import javax.swing.*;
import java.awt.*;

/**
 * Singleton che rappresenta il pannello di gameOver;
 * Quando viene istanziato inizializza il pannello.
 *
 * @author Mariano Buttino
 */

public class GameOverPanel extends JPanel {

    private static GameOverPanel instance = new GameOverPanel();

    private Image backgroundImage;

    private GameOverPanel() {

        this.setSize(640, 480);
        this.setLayout(null);

        this.backgroundImage = Resources.getImage("/background/ninja_gaiden_game_over.png");
    }

    public static GameOverPanel getInstance() {
        return instance;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(backgroundImage, 0, 0, 640, 480, null);
    }
}
