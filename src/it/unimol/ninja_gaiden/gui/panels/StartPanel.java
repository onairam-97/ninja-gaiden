package it.unimol.ninja_gaiden.gui.panels;

import it.unimol.ninja_gaiden.gui.frames.Mainframe;
import it.unimol.ninja_gaiden.gui.utils.Resources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Classe che rappresenta il menù principale del gioco.
 * Quando viene istanziato viene inizializzato il pannello e il listener.
 *
 * @author Mariano Buttino
 */

public class StartPanel extends JPanel {

    private Image backgroundImage;

    public StartPanel() {

        this.setSize(640, 480);
        this.setLayout(null);
        this.backgroundImage = Resources.getImage("/background/ninjagaidenmenu.png");

        this.setFocusable(true);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    Mainframe.getInstance().showPanel(Mainframe.GAME);
                    StatisticsPanel.getInstance().updateTime();
                    //LayerPanel.getInstance().initMusicBackground();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.backgroundImage, 0, 0, 640, 480, null);
    }

}
