package it.unimol.ninja_gaiden.gui.frames;

import it.unimol.ninja_gaiden.gui.panels.*;

import javax.swing.*;

/**
 * Singleton che gestisce le operazioni sull'interfaccia grafica.
 * Quando richiamato, inizializza la finestra, aggiunge i pannelli alla finestra e mostra il pannello del menù principale.
 * La classe fornisce le seguenti funzionalità:
 * - creazione di una finestra per l'interazione con il programma.
 * - gestione della visualizzazione dei pannelli.
 *
 * @author Mariano Buttino
 */

public class Mainframe extends JFrame {

    private static Mainframe instance = new Mainframe();

    public static final int START = 1;
    public static final int GAME = 2;
    public static final int GAMEOVER = 3;
    public static final int WIN = 4;

    public static final int WIDTH_FRAME = 640;
    public static final int HEIGH_FRAME = 480;

    private static StartPanel startPanel;
    private static GamePanel gamePanel;
    private static GameOverPanel gameOverPanel;
    private static FinishGamePanel finishGamePanel;
    private JPanel activePanel;

    private Mainframe() {
        initFrame();
    }

    public static Mainframe getInstance() {
        return instance;
    }

    public JPanel getActivePanel() {
        return activePanel;
    }

    private void initFrame() {

        startPanel = new StartPanel();
        this.getContentPane().add(startPanel);

        gamePanel = GamePanel.getInstance();
        this.getContentPane().add(gamePanel);

        gameOverPanel = GameOverPanel.getInstance();
        this.getContentPane().add(gameOverPanel);

        finishGamePanel = FinishGamePanel.getInstance();
        this.getContentPane().add(finishGamePanel);

        showPanel(Mainframe.START);

        this.setTitle("Ninja Gaiden");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH_FRAME, HEIGH_FRAME);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);


    }

    /**
     * Imposta come attivo il pannello selezionato tramite GameStatus.
     * Rende invisibili tutti gli altri pannelli.
     * @param pGameStatus identificatore del pannello da visualizzare.
     */

    public void showPanel(int pGameStatus) {
        switch (pGameStatus) {
            case START:
                activePanel = startPanel;
                startPanel.setVisible(true);
                gamePanel.setVisible(false);
                gameOverPanel.setVisible(false);
                finishGamePanel.setVisible(false);
                break;
            case GAME:
                activePanel = GamePanel.getInstance();
                gamePanel.setVisible(true);
                startPanel.setVisible(false);
                gameOverPanel.setVisible(false);
                finishGamePanel.setVisible(false);
                break;
            case GAMEOVER:
                activePanel = gameOverPanel;
                gameOverPanel.setVisible(true);
                gamePanel.setVisible(false);
                startPanel.setVisible(false);
                finishGamePanel.setVisible(false);
                break;
            case WIN:
                activePanel = finishGamePanel;
                finishGamePanel.setVisible(true);
                gamePanel.setVisible(false);
                startPanel.setVisible(false);
                gameOverPanel.setVisible(false);
                break;

        }

    }
}