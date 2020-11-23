package it.unimol.ninja_gaiden.gui.panels;

import it.unimol.ninja_gaiden.app.Enemy;
import it.unimol.ninja_gaiden.gui.utils.Resources;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Classe che rappresenta il pannello delle statistiche.
 * Quando viene istanziato viene inizializzato il pannello, vengono iniziallizate le immagini
 * e vengono inizializzati gli attributi della classe.
 *
 * @author Mariano Buttino
 */

public class StatisticsPanel extends JPanel {

    private static StatisticsPanel instance = new StatisticsPanel();

    public static final int MAX_TIME = 100;

    private int layer;
    private int act;
    private int score;
    private int time;

    private Font customFont;

    private JLabel scoreLabel;
    private JLabel stageLabel;
    private JLabel actLabel;
    private JLabel timerLabel;
    private JLabel fireballLabel;
    private JLabel ninjaLabel;
    private JLabel enemyLabel;

    private Image fireballIconContainerTopLeft;
    private Image fireballIconContainerlowerLeft;
    private Image fireballIconContainerTopRight;
    private Image fireballIconContainerLowerRight;
    private Image ninjaEmptyLifeBar;
    private Image enemyEmptyLifeBar;
    private Image ninjaFullLifeBar;
    private Image enemyFullLifeBar;
    private Image fireballIcon;

    private Enemy enemy;

    public static StatisticsPanel getInstance() {
        return instance;
    }

    private StatisticsPanel() {

    initPanel();
    initInfo();
    initImage();

    }

    public int getScore() {
        return score;
    }

    public Font getCustomFont() {
        return customFont;
    }

    public int getTime() {
        return time;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 640, 100);

        g.drawImage(this.fireballIconContainerTopLeft, 220, 50, 20, 20, null);
        g.drawImage(this.fireballIconContainerlowerLeft, 220, 72, 20, 20, null);
        g.drawImage(this.fireballIconContainerTopRight, 260, 50, 20, 20, null);
        g.drawImage(this.fireballIconContainerLowerRight, 260, 72, 20, 20, null);
        g.drawImage(this.fireballIcon, 185, 5, 130, 132, null);

        int x = 410;
        int x1 = 410;

        for (int i= 0; i < 16; i++) {
            g.drawImage(this.ninjaEmptyLifeBar, x, 52, 10, 17, null);
            g.drawImage(this.enemyEmptyLifeBar, x1, 72, 10, 17, null);
            x = x + 13;
            x1 = x1 + 13;
        }


        int x3 = 16 * LayerPanel.getInstance().getPlayer().getHealth() / 800;
        int x4 = 410;

        for (int i = 0; i < x3; i++) {
            g.drawImage(this.ninjaFullLifeBar, x4, 52, 10, 17, null);
            x4 = x4 + 13;
        }

        int x5 = 16 * LayerPanel.getInstance().getBoss().getHealth() / 3200;
        int x6 = 410;

        for (int i = 0; i < x5; i++) {
            g.drawImage(this.enemyFullLifeBar, x6, 72, 10, 17, null);
            x6 = x6 + 13;
        }


        g.setFont(customFont);
        g.setColor(Color.white);
        g.drawString(String.format("%06d", this.score), 160, 50);

        g.setFont(customFont);
        g.setColor(Color.white);
        g.drawString(Integer.toString(this.time), 160, 70);

        g.setFont(customFont);
        g.setColor(Color.white);
        g.drawString(this.layer + "-", 410, 49);

    }

    public void updateScoreByKillingEnemies() {

        this.score = this.score + enemy.getScore();
        this.repaint();
    }

    public void updateScoreByKillingBoss() {

        this.score = this.score + LayerPanel.getInstance().getBoss().getScore();
        this.repaint();
    }

    public void updateLayer() {
        this.layer++;
        this.repaint();
    }

    public void resetStatistics() {
        this.score = 0;
        this.layer = 1;
        this.act = 1;
    }

    public void updateAct() {
        this.act++;
        this.repaint();
    }

    public void updateStage() {
        this.layer++;
        this.repaint();
    }

    private void initImage() {

        this.fireballIconContainerTopLeft = Resources.getImage("/sprite_item_and_conteiners/fireball_icon_container_top_left.png");
        this.fireballIconContainerlowerLeft = Resources.getImage("/sprite_item_and_conteiners/fireball_icon_container_lower_left.png");
        this.fireballIconContainerTopRight = Resources.getImage("/sprite_item_and_conteiners/fireball_icon_container_top_right.png");
        this.fireballIconContainerLowerRight = Resources.getImage("/sprite_item_and_conteiners/fireball_icon_container_lower_right.png");
        this.ninjaEmptyLifeBar = Resources.getImage("/sprite_item_and_conteiners/empty_life_bar.png");
        this.enemyEmptyLifeBar = Resources.getImage("/sprite_item_and_conteiners/empty_life_bar.png");
        this.ninjaFullLifeBar = Resources.getImage("/sprite_item_and_conteiners/full_life_bar.png");
        this.enemyFullLifeBar = Resources.getImage("/sprite_item_and_conteiners/full_life_bar.png");
        this.fireballIcon = Resources.getImage("/sprite_item_and_conteiners/fireball_icon.png");

        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/font/Ninja Gaiden (NES) (Alternate).ttf")).deriveFont(18f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/font/Ninja Gaiden (NES) (Alternate).ttf")));
        }
        catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        scoreLabel = new JLabel("SCORE-");
        scoreLabel.setBounds(50, -10, 300, 100);
        scoreLabel.setFont(customFont);
        scoreLabel.setForeground(Color.white);
        this.add(scoreLabel);

        stageLabel = new JLabel("STAGE-");
        stageLabel.setBounds(300, -10, 300,100);
        stageLabel.setFont(customFont);
        stageLabel.setForeground(Color.white);
        this.add(stageLabel);

        actLabel = new JLabel();
        actLabel.setText(Integer.toString(act));
        actLabel.setBounds(450, -10, 300, 100);
        actLabel.setFont(customFont);
        actLabel.setForeground(Color.white);
        this.add(actLabel);

        timerLabel = new JLabel("TIMER-");
        timerLabel.setBounds(50, 10, 300, 100);
        timerLabel.setFont(customFont);
        timerLabel.setForeground(Color.white);
        this.add(timerLabel);

        fireballLabel = new JLabel("FIREBALL");
        fireballLabel.setBounds(50, 30, 300, 100);
        fireballLabel.setFont(customFont);
        fireballLabel.setForeground(Color.white);
        this.add(fireballLabel);

        ninjaLabel = new JLabel("NINJA-");
        ninjaLabel.setBounds(300, 10, 300, 100);
        ninjaLabel.setFont(customFont);
        ninjaLabel.setForeground(Color.white);
        this.add(ninjaLabel);

        enemyLabel = new JLabel("ENEMY-");
        enemyLabel.setBounds(300, 30, 300, 100);
        enemyLabel.setFont(customFont);
        enemyLabel.setForeground(Color.white);
        this.add(enemyLabel);

    }

    private void initPanel() {
        this.setSize(640, 100);
        this.setLayout(null);
        enemy = new Enemy();


    }

    private void initInfo() {
        this.score = 0;
        this.layer = 1;
        this.act = 1;
        this.time = MAX_TIME;
    }

    public void updateTime() {
        new Thread(()-> {
            while (this.time > 0) {
                this.time--;
                this.repaint();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
