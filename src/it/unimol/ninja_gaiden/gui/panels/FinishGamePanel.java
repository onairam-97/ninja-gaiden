package it.unimol.ninja_gaiden.gui.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Singleton che rappresenta il pannello di fine gioco;
 * Quando viene istanziato inizializza il pannello.
 *
 * @author Mariano Buttino
 */

public class FinishGamePanel extends JPanel {

    private static FinishGamePanel instance = new FinishGamePanel();

    private FinishGamePanel() {

        this.setSize(640, 480);
        this.setLayout(null);
    }

    public static FinishGamePanel getInstance() {
        return instance;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 640, 480);

        g.setFont(StatisticsPanel.getInstance().getCustomFont());
        g.setColor(Color.white);
        g.drawString("YOU WIN!", 250, 240);
    }
}
