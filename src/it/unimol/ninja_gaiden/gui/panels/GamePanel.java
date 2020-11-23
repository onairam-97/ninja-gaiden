package it.unimol.ninja_gaiden.gui.panels;

import javax.swing.*;

/**
 * Singleton che rappresenta la schermata di gioco.
 * Quando viene istanziato inizializza il pannello e aggiunge il pannello delle stastische e il pannello del livello.
 *
 * @author Mariano Buttino
 */

public class GamePanel extends JPanel {

    private static GamePanel  instance = new GamePanel();

    private StatisticsPanel statisticsPanel;
    private LayerPanel layerPanel;

    private GamePanel() {

        this.setSize(900, 480);
        this.setLayout(null);

        statisticsPanel = StatisticsPanel.getInstance();
        statisticsPanel.setLocation(0,0);
        this.add(statisticsPanel);

        layerPanel = LayerPanel.getInstance();
        layerPanel.setLocation(0, 100);
        this.add(layerPanel);
    }

    public static GamePanel getInstance() {
        return instance;
    }
}
