package it.unimol.ninja_gaiden.gui.panels;

import it.unimol.ninja_gaiden.app.*;
import it.unimol.ninja_gaiden.gui.sprites.EnemySprite;
import it.unimol.ninja_gaiden.gui.sprites.PlayerSprite;
import it.unimol.ninja_gaiden.gui.frames.Mainframe;
import it.unimol.ninja_gaiden.gui.sprites.AttackRangedSprite;
import it.unimol.ninja_gaiden.gui.utils.PersonalTimer;
import it.unimol.ninja_gaiden.gui.utils.Resources;
import it.unimol.ninja_gaiden.gui.utils.Sound;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import static java.lang.System.exit;

/**
 * Singleton che rappresenta il pannello del livello del gioco.
 * Quando viene istanziato si ha l'inizializzazione del pannello, inizializzazione dei listener,
 * l'inizializzazione del thread che gestisce i movimenti del personaggio e
 * l'inizializzazione del thread che gestisce le collisioni del personaggio con la mappa e con i vari nemici.
 * La classe fornisce le seguenti funzionalità:
 * - cambio di livello
 * - cambio di pannello
 * - inizializzazione della musica di sottofondo.
 *
 * @author Mariano Buttino
 */

public class LayerPanel extends JPanel implements ActionListener {

    private static LayerPanel instance = new LayerPanel();

    private int mapX;
    private int mapXRightCollision0;
    private int mapXRightCollision1;
    private int mapXRightCollision2;
    private int mapXRightCollision3;

    private PlayerSprite playerSprite;
    private AttackRangedSprite attackRangedSprite;
    private EnemySprite enemySprite;
    private Enemy enemy;
    private Player player;
    private Boss boss;
    private Skill skill;

    private Image backgroundImage;

    private Timer time;

    private Set<Integer> pressed;

    private boolean canPressRight;
    private boolean canPressLeft;
    private boolean canPressDown;
    private boolean canPressA;
    private boolean canPressUP;
    private boolean canPressS;
    private boolean canPressD;
    private boolean isPressed;

    private Rectangle[] rectangles;

    private boolean isCollideFromRight;
    private boolean isCollideFromLeft;
    private boolean isCollidefromAbove;
    private boolean isCollide;
    private boolean finishLayer;
    private boolean bossRoom;

    private Sound[] sounds;
    private Clip clip;


    private LayerPanel() {

        initPanel();
        initListener();
        time = new Timer(5, this);
        time.start();
        init();
        collide();
    }

    public static LayerPanel getInstance() {
        return instance;
    }

    public boolean isCanPressRight() {
        return canPressRight;
    }

    public boolean isCanPressLeft() {
        return canPressLeft;
    }

    public boolean isPressed() {
        return isPressed;
    }

    public void setPressed(boolean pressed) {
        isPressed = pressed;
    }

    public int getMapX() {
        return mapX;
    }

    public void setMapX(int mapX) {
        this.mapX = mapX;
    }

    public int getMapXRightCollision0() {
        return mapXRightCollision0;
    }

    public void setMapXRightCollision0(int mapXRightCollision0) {
        this.mapXRightCollision0 = mapXRightCollision0;
    }

    public int getMapXRightCollision1() {
        return mapXRightCollision1;
    }

    public void setMapXRightCollision1(int mapXRightCollision1) {
        this.mapXRightCollision1 = mapXRightCollision1;
    }

    public int getMapXRightCollision2() {
        return mapXRightCollision2;
    }

    public void setMapXRightCollision2(int mapXRightCollision2) {
        this.mapXRightCollision2 = mapXRightCollision2;
    }

    public Rectangle[] getRectangles() {
        return rectangles;
    }

    public void setRectangles(Rectangle[] rectangles) {
        this.rectangles = rectangles;
    }

    public boolean isCollide() {
        return isCollide;
    }

    public void setCollide(boolean collide) {
        isCollide = collide;
    }

    public boolean isCollideFromRight() {
        return isCollideFromRight;
    }

    public void setCollideFromRight(boolean collideFromRight) {
        isCollideFromRight = collideFromRight;
    }

    public boolean isCollideFromLeft() {
        return isCollideFromLeft;
    }

    public void setCollideFromLeft(boolean collideFromLeft) {
        isCollideFromLeft = collideFromLeft;
    }

    public boolean isCollidefromAbove() {
        return isCollidefromAbove;
    }

    public void setCollidefromAbove(boolean collidefromAbove) {
        isCollidefromAbove = collidefromAbove;
    }

    public boolean isBossRoom() {
        return bossRoom;
    }

    public boolean isFinishLayer() {
        return finishLayer;
    }

    public Player getPlayer() {
        return player;
    }

    public Boss getBoss() {
        return boss;
    }

    public Skill getSkill() {
        return skill;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public PlayerSprite getPlayerSprite() {
        return playerSprite;
    }

    public void actionPerformed(ActionEvent e) {
        playerSprite.move();
        playerSprite.attack();
        attackRangedSprite.rangedAttack();
        enemySprite.updateCoordinates();
        collide();
        switchLayer();
        gameOver();
        endGame();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

            g.drawImage(backgroundImage, -this.mapX, 0, 3073, 380, null);
            if (finishLayer) {
                g.drawImage(backgroundImage, 0, 0, 640, 380, null);
                g.drawImage(enemySprite.getBossActiveImage(), enemySprite.getBossX(), enemySprite.getBossY(), 170, 170, null);
            }
            g.drawImage(playerSprite.getActiveImage(), playerSprite.getX(), playerSprite.getY(), playerSprite.getWidht(), playerSprite.getHeight(), null);
            g.drawImage(attackRangedSprite.getActiveImage(), attackRangedSprite.getX(), attackRangedSprite.getY(), 80, 80, null);
            g.drawImage(enemySprite.getActiveImage(), enemySprite.getX(), enemySprite.getY(), 120, 120, null);
            g.drawImage(enemySprite.getActiveImage2(), enemySprite.getX2(), enemySprite.getY(), 120, 120, null);
            g.drawImage(enemySprite.getActiveImage3(), enemySprite.getX3(), enemySprite.getY(), 120, 120, null);
            g.drawImage(enemySprite.getActiveImage4(), enemySprite.getX4(), enemySprite.getY(), 120, 120, null);
            g.drawImage(enemySprite.getActiveImage5(), enemySprite.getX5(), enemySprite.getY(), 120, 120, null);

            /*g.setColor(Color.RED);
            g.drawRect(player.getCollisionBox().x, player.getCollisionBox().y, player.getCollisionBox().width, player.getCollisionBox().height);

            g.setColor(Color.RED);
            g.drawRect(this.rectangles[0].x, this.rectangles[0].y, this.rectangles[0].width, this.rectangles[0].height);

            g.setColor(Color.RED);
            g.drawRect(this.rectangles[1].x, this.rectangles[1].y, this.rectangles[1].width, this.rectangles[1].height);

            g.setColor(Color.RED);
            g.drawRect(this.rectangles[2].x, this.rectangles[2].y, this.rectangles[2].width, this.rectangles[2].height);

            g.setColor(Color.RED);
            g.drawRect(this.rectangles[3].x, this.rectangles[3].y, this.rectangles[3].width, this.rectangles[3].height);

            g.setColor(Color.RED);
            g.drawRect(enemySprite.getRectangles()[0].x, enemySprite.getRectangles()[0].y, enemySprite.getRectangles()[0].width, enemySprite.getRectangles()[0].height);

            g.setColor(Color.RED);
            g.drawRect(enemySprite.getRectangles()[1].x, enemySprite.getRectangles()[1].y, enemySprite.getRectangles()[1].width, enemySprite.getRectangles()[1].height);

            g.setColor(Color.RED);
            g.drawRect(enemySprite.getRectangles()[2].x, enemySprite.getRectangles()[2].y, enemySprite.getRectangles()[2].width, enemySprite.getRectangles()[2].height);

            g.setColor(Color.RED);
            g.drawRect(enemySprite.getRectangles()[3].x, enemySprite.getRectangles()[3].y, enemySprite.getRectangles()[3].width, enemySprite.getRectangles()[3].height);

            g.setColor(Color.RED);
            g.drawRect(enemySprite.getRectangles()[4].x, enemySprite.getRectangles()[4].y, enemySprite.getRectangles()[4].width, enemySprite.getRectangles()[4].height);

            if (player.getISAttacking().get()) {
                g.setColor(Color.RED);
                g.drawRect(player.getAttackCollisionBox().x, player.getAttackCollisionBox().y, player.getAttackCollisionBox().width, player.getAttackCollisionBox().height);
            }

            if (attackRangedSprite.isAnimationActive()) {
                g.setColor(Color.RED);
                g.drawRect(attackRangedSprite.getCollisionBox().x, attackRangedSprite.getCollisionBox().y, attackRangedSprite.getCollisionBox().width, attackRangedSprite.getCollisionBox().height);
            }

            g.setColor(Color.RED);
            g.drawRect(enemySprite.getBossCollisionBox()[0].x, enemySprite.getBossCollisionBox()[0].y, enemySprite.getBossCollisionBox()[0].width, enemySprite.getBossCollisionBox()[0].height);

            g.setColor(Color.RED);
            g.drawRect(enemySprite.getBossCollisionBox()[1].x, enemySprite.getBossCollisionBox()[1].y, enemySprite.getBossCollisionBox()[1].width, enemySprite.getBossCollisionBox()[1].height);

             */
    }

    private void initListener() {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public synchronized void keyTyped(KeyEvent e) {
                super.keyTyped(e);

            }

            @Override
            public synchronized void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT && canPressLeft) {
                    isPressed = false;
                    playerSprite.setAttack(false);
                    playerSprite.setRight(false);
                    playerSprite.setLeft(true);
                    playerSprite.setIsRunning(true);
                    pressed.add(e.getKeyCode());
                }

                if (key == KeyEvent.VK_RIGHT && canPressRight) {
                    isPressed = false;
                    playerSprite.setAttack(false);
                    playerSprite.setLeft(false);
                    playerSprite.setRight(true);
                    playerSprite.setIsRunning(true);
                    pressed.add(e.getKeyCode());
                }

                if (key == KeyEvent.VK_UP && canPressUP) {
                    playerSprite.setUp(true);
                    playerSprite.setReleasedUp(false);
                }

                if (key == KeyEvent.VK_DOWN && canPressDown) {
                    playerSprite.setDown(true);
                    playerSprite.setReleasedDown(false);
                    pressed.add(e.getKeyCode());
                }

                if (key == KeyEvent.VK_A && canPressA) {
                    isPressed = false;
                    playerSprite.setLeft(false);
                    playerSprite.setRight(false);
                    playerSprite.setAttack(true);
                    playerSprite.setIsRunning(true);
                    playerSprite.setIsAttacking(true);
                    pressed.add(e.getKeyCode());
                }

                if (key == KeyEvent.VK_S && canPressS && !isPressed) {
                    playerSprite.setRange(true);
                    playerSprite.setIsThrowing(true);
                    playerSprite.setIsRunning(true);
                    playerSprite.setShuriken(true);
                    new PersonalTimer(300, ()-> isPressed = true);
                    pressed.add(e.getKeyCode());
                }

                if (key == KeyEvent.VK_D && canPressD && !isPressed) {
                    playerSprite.setIsThrowing(true);
                    playerSprite.setIsRunning(true);
                    playerSprite.setFireball(true);
                    new PersonalTimer(300, ()-> isPressed = true);
                    pressed.add(e.getKeyCode());
                }

                if (pressed.size() > 1) {
                    playerSprite.setLeft(false);
                    playerSprite.setRight(false);
                    playerSprite.setDown(false);
                    playerSprite.setAttack(false);
                    playerSprite.setRange(false);
                    playerSprite.setIsRunning(false);
                    playerSprite.setIsAttacking(false);
                    playerSprite.setIsThrowing(false);

                }

            }

            @Override
            public synchronized void keyReleased(KeyEvent e) {
                super.keyReleased(e);

                int key = e.getKeyCode();

                if (key == KeyEvent.VK_LEFT) {
                    canPressRight = false;
                    canPressDown = false;
                    canPressA = false;
                    canPressS = false;
                    canPressD = false;
                    playerSprite.setIsRunning(false);
                    new PersonalTimer(100, () -> {canPressRight = true;
                    canPressDown = true; canPressA = true; canPressS = true; canPressD = true;});
                    playerSprite.setLeft(false);
                    playerSprite.setReleasedRight(false);
                    playerSprite.setReleasedLeft(true);
                    pressed.remove(e.getKeyCode());
                }


                if (key == KeyEvent.VK_RIGHT) {
                    canPressLeft = false;
                    canPressDown = false;
                    canPressA = false;
                    canPressS = false;
                    canPressD = false;
                    playerSprite.setIsRunning(false);
                    new PersonalTimer(100, () -> {canPressLeft = true;
                    canPressDown = true; canPressA = true; canPressS = true; canPressD = true;});
                    playerSprite.setRight(false);
                    playerSprite.setReleasedLeft(false);
                    playerSprite.setReleasedRight(true);
                    pressed.remove(e.getKeyCode());
                }

                if (key == KeyEvent.VK_UP) {
                    canPressS = false;
                    canPressA = false;
                    canPressDown = false;
                    canPressD = false;
                    new PersonalTimer(100, () -> {canPressA = true; canPressDown = true; canPressS = true; canPressD = true;});
                    playerSprite.setUp(false);
                    playerSprite.setReleasedUp(true);
                }

                if (key == KeyEvent.VK_DOWN) {
                    canPressLeft = false;
                    canPressRight = false;
                    canPressA = false;
                    canPressS = false;
                    canPressD = false;
                    new PersonalTimer(100, () -> {canPressLeft = true;
                    canPressRight = true; canPressA = true; canPressS = true; canPressD = true;});
                    playerSprite.setDown(false);
                    playerSprite.setReleasedDown(true);
                    pressed.remove(e.getKeyCode());
                }

                if (key == KeyEvent.VK_A) {
                    canPressS = false;
                    canPressDown = false;
                    canPressLeft = false;
                    canPressRight = false;
                    canPressUP = false;
                    canPressD = false;
                    playerSprite.setAttack(false);
                    new PersonalTimer(100, () -> {canPressLeft = true;
                    canPressRight = true; canPressDown = true; canPressS = true; canPressUP = true; canPressD = true;});
                    playerSprite.setIsAttacking(false);
                    playerSprite.setIsRunning(false);
                    pressed.remove(e.getKeyCode());
                }

                if (key == KeyEvent.VK_S) {
                    isPressed = false;
                    canPressLeft = false;
                    canPressRight = false;
                    canPressA = false;
                    canPressUP = false;
                    canPressDown = false;
                    canPressD = false;
                    playerSprite.setRange(false);
                    new PersonalTimer(100, () -> {canPressLeft = true;
                    canPressRight = true; canPressA = true; canPressDown = true; canPressUP = true; canPressD = true;});
                    playerSprite.setIsThrowing(false);
                    playerSprite.setIsRunning(false);
                    playerSprite.setShuriken(false);
                    pressed.remove(e.getKeyCode());
                }

                if (key == KeyEvent.VK_D) {
                    isPressed = false;
                    canPressLeft = false;
                    canPressRight = false;
                    canPressA = false;
                    canPressUP = false;
                    canPressDown = false;
                    canPressS = false;
                    new PersonalTimer(100, () -> {canPressLeft = true;
                    canPressRight = true; canPressA = true; canPressDown = true; canPressUP = true; canPressS = true;});
                    playerSprite.setIsThrowing(false);
                    playerSprite.setIsRunning(false);
                    playerSprite.setFireball(false);
                    pressed.remove(e.getKeyCode());
                }

            }


        });
    }

    public void initPanel() {

        this.setSize(640, 480);
        this.playerSprite = new PlayerSprite();
        this.attackRangedSprite = new AttackRangedSprite(this.playerSprite);
        this.enemy = new Enemy();
        this.boss = new Boss();
        this.player = new Player(this.enemy, this.boss);
        this.skill = new Skill();
        this.enemySprite = new EnemySprite(this.playerSprite, this, this.enemy);
        this.setFocusable(true);
        this.backgroundImage = Resources.getImage("/background/NinjaGaidenMapStage1-1BG.png");
    }

    public void init() {

        pressed = new HashSet<>();

        canPressLeft = true;
        canPressRight = true;
        canPressDown = true;
        canPressA = true;
        canPressUP = true;
        canPressS = true;
        canPressD = true;
        isPressed = false;
        isCollide = false;
        isCollideFromRight = false;
        isCollideFromLeft = false;
        isCollidefromAbove = false;
        finishLayer = false;
        bossRoom = false;

        mapX = 0;
        mapXRightCollision0 = 705;
        mapXRightCollision1 = 1825;
        mapXRightCollision2 = 2335;
        mapXRightCollision3 = 3360;

        this.rectangles = new Rectangle[] {
                new Rectangle(mapXRightCollision0, playerSprite.getY() + 53, 142, 54),
                new Rectangle(mapXRightCollision1, playerSprite.getY() + 53, 175, 54),
                new Rectangle(mapXRightCollision2, playerSprite.getY() + 53, 240, 54),
                new Rectangle(mapXRightCollision3, playerSprite.getY() + 17, 20, 90)};

        /*this.sounds = new Sound[] {
                Resources.getSound("/sound_effects/stage_1_soundtrack.wav"),
                Resources.getSound("/sound_effects/boss_room_soundtrack.wav"),
                Resources.getSound("/sound_effects/Game_Over_soundtrack.wav"),
                Resources.getSound("/sound_effects/ninja_gaiden_win_soundtrack.wav"),
                Resources.getSound("/sound_effects/26.wav")};*/
    }

    public void collide() {

        new PersonalTimer(1, ()-> {
            if (playerSprite.getCollisionBox().intersects(this.rectangles[0]) && (playerSprite.getX2() > 617 && playerSprite.getX2() < 801) && playerSprite.getY() >= playerSprite.FLOORY2 && !finishLayer) {
                isCollideFromLeft = true;
                isCollide = true;
            } else if (playerSprite.getCollisionBox().intersects(this.rectangles[0]) && (playerSprite.getX2() > 801 && playerSprite.getX2() < 806) && playerSprite.getY() >= playerSprite.FLOORY2 && !finishLayer) {
                isCollideFromRight = true;
                isCollide = true;
            } else if (playerSprite.getCollisionBox().intersects(this.rectangles[1]) && (playerSprite.getX2() > 1740 && playerSprite.getX2() < 1954) && playerSprite.getY() >= playerSprite.FLOORY2 && !finishLayer) {
                isCollideFromLeft = true;
                isCollide = true;
            } else if (playerSprite.getCollisionBox().intersects(this.rectangles[1]) && (playerSprite.getX2() > 1954 && playerSprite.getX2() < 1959) && playerSprite.getY() >= playerSprite.FLOORY2 && !finishLayer) {
                isCollideFromRight = true;
                isCollide = true;
            } else if (playerSprite.getCollisionBox().intersects(this.rectangles[2]) && (playerSprite.getX2() > 2250 && playerSprite.getX2() < 2528) && playerSprite.getY() >= playerSprite.FLOORY2 && !finishLayer) {
                isCollideFromLeft = true;
                isCollide = true;
            } else if (playerSprite.getCollisionBox().intersects(this.rectangles[2]) && (playerSprite.getX2() > 2528 && playerSprite.getX2() < 2533) && playerSprite.getY() >= playerSprite.FLOORY2 && !finishLayer) {
                isCollideFromRight = true;
                isCollide = true;
            } else if (playerSprite.getCollisionBox().intersects(this.rectangles[0]) && playerSprite.getY() <= playerSprite.FLOORY2 && !finishLayer){
                isCollide = true;
                isCollidefromAbove = true;
            } else if (playerSprite.getCollisionBox().intersects(this.rectangles[1]) && playerSprite.getY() <= playerSprite.FLOORY2 && !finishLayer) {
                isCollide = true;
                isCollidefromAbove = true;
            } else if (playerSprite.getCollisionBox().intersects(this.rectangles[2]) && playerSprite.getY() <= playerSprite.FLOORY2 && !finishLayer) {
                isCollidefromAbove = true;
            } else if (playerSprite.getAttackCollisionBox().intersects(enemySprite.getRectangles()[0]) && playerSprite.getISAttacking().get()) {
                if (!enemy.isDead())
                    StatisticsPanel.getInstance().updateScoreByKillingEnemies();
                enemy.setDead(true);
                enemySprite.setActiveImage(null);
            } else if (playerSprite.getAttackCollisionBox().intersects(enemySprite.getRectangles()[1]) && playerSprite.getISAttacking().get()) {
                if (!enemy.isDead2())
                    StatisticsPanel.getInstance().updateScoreByKillingEnemies();
                enemy.setDead2(true);
                enemySprite.setActiveImage2(null);
            } else if (playerSprite.getAttackCollisionBox().intersects(enemySprite.getRectangles()[2]) && playerSprite.getISAttacking().get()) {
                if (!enemy.isDead3())
                    StatisticsPanel.getInstance().updateScoreByKillingEnemies();
                enemy.setDead3(true);
                enemySprite.setActiveImage3(null);
            } else if (playerSprite.getAttackCollisionBox().intersects(enemySprite.getRectangles()[3]) && playerSprite.getISAttacking().get()) {
                if (!enemy.isDead4())
                    StatisticsPanel.getInstance().updateScoreByKillingEnemies();
                enemy.setDead4(true);
                enemySprite.setActiveImage4(null);
            } else if (playerSprite.getAttackCollisionBox().intersects(enemySprite.getRectangles()[4]) && playerSprite.getISAttacking().get()) {
                if (!enemy.isDead5())
                    StatisticsPanel.getInstance().updateScoreByKillingEnemies();
                enemy.setDead5(true);
                enemySprite.setActiveImage5(null);
            } else if (playerSprite.getCollisionBox().intersects(enemySprite.getRectangles()[0]) && (playerSprite.getX2() > 850 && playerSprite.getX2() < 940) && !enemy.isDead() && playerSprite.getY() >= playerSprite.FLOORY3) {
                isCollide = true;
                isCollideFromLeft = true;
                player.update();
            } else if (playerSprite.getCollisionBox().intersects(enemySprite.getRectangles()[0]) && (playerSprite.getX2() > 940 && playerSprite.getX2() < 949) && !enemy.isDead()) {
                isCollide = true;
                isCollideFromRight = true;
                player.update();
            } else if (playerSprite.getCollisionBox().intersects(enemySprite.getRectangles()[1]) && (playerSprite.getX2() > 1150 && playerSprite.getX2() < 1240) && !enemy.isDead2()) {
                isCollide = true;
                isCollideFromLeft = true;
                player.update();
            } else if (playerSprite.getCollisionBox().intersects(enemySprite.getRectangles()[1]) && (playerSprite.getX2() > 1240 && playerSprite.getX2() < 1247) && !enemy.isDead2()) {
                isCollide = true;
                isCollideFromRight = true;
                player.update();
            } else if (playerSprite.getCollisionBox().intersects(enemySprite.getRectangles()[2]) && (playerSprite.getX2() > 1252 && playerSprite.getX2() < 1330) && !enemy.isDead3()) {
                isCollide = true;
                isCollideFromLeft = true;
                player.update();
            } else if (playerSprite.getCollisionBox().intersects(enemySprite.getRectangles()[2]) && (playerSprite.getX2() > 1330 && playerSprite.getX2() < 1339) && !enemy.isDead3()) {
                isCollide = true;
                isCollideFromRight = true;
                player.update();
            } else if (playerSprite.getCollisionBox().intersects(enemySprite.getRectangles()[3]) && (playerSprite.getX2() > 1647 && playerSprite.getX2() < 1730) && !enemy.isDead4()) {
                isCollide = true;
                isCollideFromLeft = true;
                player.update();
            } else if (playerSprite.getCollisionBox().intersects(enemySprite.getRectangles()[3]) && (playerSprite.getX2() > 1730 && playerSprite.getX2() < 1737) && !enemy.isDead4()) {
                isCollide = true;
                isCollideFromRight = true;
                player.update();
            } else if (playerSprite.getCollisionBox().intersects(enemySprite.getRectangles()[4]) && (playerSprite.getX2() > 2145 && playerSprite.getX2() < 2235) && !enemy.isDead5()) {
                isCollide = true;
                isCollideFromLeft = true;
                player.update();
            } else if (playerSprite.getCollisionBox().intersects(enemySprite.getRectangles()[4]) && (playerSprite.getX2() > 2235 && playerSprite.getX2() < 2243) && !enemy.isDead5()) {
                isCollide = true;
                isCollideFromRight = true;
                player.update();
            } else if (attackRangedSprite.getCollisionBox().intersects(enemySprite.getRectangles()[0]) && attackRangedSprite.isAnimationActive()) {
                if (!enemy.isDead())
                    StatisticsPanel.getInstance().updateScoreByKillingEnemies();
                isCollide = true;
                enemy.setDead(true);
                enemySprite.setActiveImage(null);
            } else if (attackRangedSprite.getCollisionBox().intersects(enemySprite.getRectangles()[1]) && attackRangedSprite.isAnimationActive()) {
                if (!enemy.isDead2())
                    StatisticsPanel.getInstance().updateScoreByKillingEnemies();
                isCollide = true;
                enemy.setDead2(true);
                enemySprite.setActiveImage2(null);
            } else if (attackRangedSprite.getCollisionBox().intersects(enemySprite.getRectangles()[2]) && attackRangedSprite.isAnimationActive()) {
                if (!enemy.isDead3())
                    StatisticsPanel.getInstance().updateScoreByKillingEnemies();
                isCollide = true;
                enemy.setDead3(true);
                enemySprite.setActiveImage3(null);
            } else if (attackRangedSprite.getCollisionBox().intersects(enemySprite.getRectangles()[3]) && attackRangedSprite.isAnimationActive()) {
                if (!enemy.isDead4())
                    StatisticsPanel.getInstance().updateScoreByKillingEnemies();
                isCollide = true;
                enemy.setDead4(true);
                enemySprite.setActiveImage4(null);
            } else if (attackRangedSprite.getCollisionBox().intersects(enemySprite.getRectangles()[4]) && attackRangedSprite.isAnimationActive()) {
                if (!enemy.isDead5())
                    StatisticsPanel.getInstance().updateScoreByKillingEnemies();
                isCollide = true;
                enemy.setDead5(true);
                enemySprite.setActiveImage5(null);
            } else if (playerSprite.getCollisionBox().intersects(enemySprite.getBossCollisionBox()[0]) && bossRoom) {
                isCollideFromLeft = true;
                isCollide = true;
            } else if (playerSprite.getCollisionBox().intersects(enemySprite.getBossCollisionBox()[1]) && bossRoom) {
                player.update();
            } else if (playerSprite.getAttackCollisionBox().intersects(enemySprite.getBossCollisionBox()[0]) && bossRoom && playerSprite.getISAttacking().get()) {
                if (boss.isDead()) {
                    StatisticsPanel.getInstance().updateScoreByKillingBoss();
                    boss.setDead(true);
                }
                this.boss.update();
            } else if (attackRangedSprite.getCollisionBox().intersects(enemySprite.getBossCollisionBox()[0]) && bossRoom && attackRangedSprite.isAnimationActive()) {
                if (!boss.isDead())
                    StatisticsPanel.getInstance().updateScoreByKillingBoss();
                this.boss.update();
            } else {
                isCollide = false;
                isCollidefromAbove = false;
                isCollideFromLeft = false;
                isCollideFromRight = false;
            }

        });

    }

    /**
     * Cambia l'immagine di sfondo, aggiorna la posizione del giocatore e aggiorna il livello
     * se la collisione del personaggio collide con l'ultima collsione della mappa.
     */

    public void switchLayer() {
        if (playerSprite.getCollisionBox().intersects(this.rectangles[3]) && !bossRoom) {
            backgroundImage = Resources.getImage("/background/NinjaGaidenMapStage1-2BG.png");
            finishLayer = true;
            bossRoom = true;
            playerSprite.setX(playerSprite.getX() - 535);
            StatisticsPanel.getInstance().updateLayer();
            //stopMusic();
             //clip = this.sounds[1].play();
        }
    }

    /**
     * Visualizza il pannello di gameOver se il giocatore muore o scade il tempo.
     */

    public void gameOver() {
        if (this.player.isDead() || StatisticsPanel.getInstance().getTime() <= 0) {
            Mainframe.getInstance().showPanel(Mainframe.GAMEOVER);
            new PersonalTimer(5000, ()-> exit(1));
        }
    }

    /*public void initMusicBackground() {

      new Thread(()-> {
          clip = this.sounds[0].play();
          while (true) {
             if (Mainframe.getInstance().getActivePanel() == GameOverPanel.getInstance()) {
                 clip.close();
                 clip.stop();
                 stopMusic();
                 this.sounds[2].play();
                 break;
             } else if (Mainframe.getInstance().getActivePanel() == FinishGamePanel.getInstance()) {
                 clip.close();
                 clip.stop();
                 stopMusic();
                 this.sounds[3].play();
                 break;
             }
              try {
                  Thread.sleep(100);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
      }).start();
    }*/

    /*public void stopMusic() {

        this.clip.close();
        this.clip.stop();
    }*/

    /**
     * Dopo 3 secondi dalla morte del boss visuallizza il pannello di fine gioco e
     * dopo altri 5 secondi esce dal programma.
     */

    public void endGame() {
        if (this.boss.isDead()) {
            new PersonalTimer(3000, ()-> {
                Mainframe.getInstance().showPanel(Mainframe.WIN);
                new PersonalTimer(5000, ()-> exit(1));
            });

        }
    }
}
