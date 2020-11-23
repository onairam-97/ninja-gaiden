package it.unimol.ninja_gaiden.gui.sprites;

import it.unimol.ninja_gaiden.app.Enemy;
import it.unimol.ninja_gaiden.gui.panels.LayerPanel;
import it.unimol.ninja_gaiden.gui.utils.Resources;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Classe che rappresenta un nemico del gioco disegnabile.
 * Quando viene istanziata richiede un'istanza di {@link PlayerSprite}, un pannello e un'istanza di {@link Enemy}.
 * La classe fornisce le seguenti funzionalità:
 * - gestione delle animazioni dei nemici.
 * - gestione del movimento del boss.
 *
 * @author Mariano Buttino
 */

public class EnemySprite {

    private int x;
    private int y;
    private int x2;
    private int x3;
    private int x4;
    private int x5;
    private int bossX;
    private int bossY;
    private int dx;

    private Image activeImage;
    private Image activeImage2;
    private Image activeImage3;
    private Image activeImage4;
    private Image activeImage5;
    private Image bossActiveImage;

    private Image[] boxerAnimationRight;
    private Image[] boxerAnimationLeft;
    private Image[] enemySwordAnimationRight;
    private Image[] enemySwordAnimationLeft;
    private Image[] enemyWoodenClubAnimationRight;
    private Image[] enemyWoodenClubAnimationLeft;
    private Image[] bossAnimationRight;
    private Image[] bossAnimationLeft;

    private PlayerSprite playerSprite;

    private JPanel panel;

    private Rectangle[] rectangles;
    private Rectangle[] bossCollisionBox;

    private Enemy enemy;

    private int range;

    private boolean right;
    private boolean left;

    private boolean rightLimits;
    private boolean leftLimits;

    public EnemySprite(PlayerSprite playerSprite, JPanel panel, Enemy enemy) {

        initAnimation();

        this.playerSprite = playerSprite;
        this.panel = panel;
        this.enemy = enemy;

        init();

        Animation();
    }

    public Image getActiveImage() {
        return activeImage;
    }

    public Image getActiveImage2() {
        return activeImage2;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }


    public int getX3() {
        return x3;
    }

    public int getX4() {
        return x4;
    }

    public int getX5() {
        return x5;
    }

    public Image getActiveImage3() {
        return activeImage3;
    }

    public Image getActiveImage4() {
        return activeImage4;
    }

    public Image getActiveImage5() {
        return activeImage5;
    }

    public Rectangle[] getRectangles() {
        return rectangles;
    }

    public void setActiveImage(Image activeImage) {
        this.activeImage = activeImage;
    }

    public void setActiveImage2(Image activeImage2) {
        this.activeImage2 = activeImage2;
    }

    public void setActiveImage3(Image activeImage3) {
        this.activeImage3 = activeImage3;
    }

    public void setActiveImage4(Image activeImage4) {
        this.activeImage4 = activeImage4;
    }

    public void setActiveImage5(Image activeImage5) {
        this.activeImage5 = activeImage5;
    }

    public Image getBossActiveImage() {
        return bossActiveImage;
    }

    public int getBossX() {
        return bossX;
    }

    public int getBossY() {
        return bossY;
    }

    public int getRange() {
        return range;
    }

    public Rectangle[] getBossCollisionBox() {
        return bossCollisionBox;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public boolean isRightLimits() {
        return rightLimits;
    }

    public void setRightLimits(boolean rightLimits) {
        this.rightLimits = rightLimits;
    }

    public boolean isLeftLimits() {
        return leftLimits;
    }

    public void setLeftLimits(boolean leftLimits) {
        this.leftLimits = leftLimits;
    }

    public void initAnimation() {

        this.boxerAnimationRight = new BufferedImage[] {
                Resources.getImage("/sprite_enemies/enemy_boxer_move_right_0.png"),
                Resources.getImage("/sprite_enemies/enemy_boxer_move_right_1.png"),
                Resources.getImage("/sprite_enemies/enemy_boxer_attack.png")};

        this.boxerAnimationLeft = new BufferedImage[] {
                Resources.getImage("/sprite_enemies/enemy_boxer_move_left_0.png"),
                Resources.getImage("/sprite_enemies/enemy_boxer_move_left_1.png"),
                Resources.getImage("/sprite_enemies/enemy_boxer_attack_left.png")};

        this.enemySwordAnimationRight = new BufferedImage[] {
                //Resources.getImage("/sprite_enemies/enemy_sword_move_right_0.png"),
                Resources.getImage("/sprite_enemies/enemy_sword_move_right_1.png"),
                Resources.getImage("/sprite_enemies/enemy_sword_attack.png")};

        this.enemySwordAnimationLeft = new BufferedImage[] {
                //Resources.getImage("/sprite_enemies/enemy_sword_move_left_0.png"),
                Resources.getImage("/sprite_enemies/enemy_sword_move_left_1.png"),
                Resources.getImage("/sprite_enemies/enemy_sword_attack_left.png")};

        this.enemyWoodenClubAnimationRight = new BufferedImage[] {
                Resources.getImage("/sprite_enemies/enemy_wooden_club_move_right_0.png"),
                //Resources.getImage("/sprite_enemies/enemy_wooden_club_move_right_1.png"),
                Resources.getImage("/sprite_enemies/enemy_wooden_club_attack.png")};

        this.enemyWoodenClubAnimationLeft = new BufferedImage[] {
                Resources.getImage("/sprite_enemies/enemy_wooden_club_move_left_0.png"),
                //Resources.getImage("/sprite_enemies/enemy_wooden_club_move_left_1.png"),
                Resources.getImage("/sprite_enemies/enemy_wooden_club_attack_left.png")};

        this.bossAnimationRight = new BufferedImage[] {
                Resources.getImage("/sprite_bosses/boss_stage_1_start_position.png"),
                Resources.getImage("/sprite_bosses/boss_stage_1_move_right_0.png"),
                Resources.getImage("/sprite_bosses/boss_stage_1_move_right_1.png"),
                Resources.getImage("/sprite_bosses/boss_stage_1_attack_0.png"),
                Resources.getImage("/sprite_bosses/boss_stage_1_attack_1.png"),
                Resources.getImage("/sprite_bosses/boss_stage_1_attack_2.png"),
                Resources.getImage("/sprite_bosses/boss_stage_1_attack_3.png")};

        this.bossAnimationLeft = new BufferedImage[] {
                Resources.getImage("/sprite_bosses/boss_stage_1_left_start_position.png"),
                Resources.getImage("/sprite_bosses/boss_stage_1_move_left_0.png"),
                Resources.getImage("/sprite_bosses/boss_stage_1_move_left_1.png"),
                Resources.getImage("/sprite_bosses/boss_stage_1_left_attack_0.png"),
                Resources.getImage("/sprite_bosses/boss_stage_1_left_attack_1.png"),
                Resources.getImage("/sprite_bosses/boss_stage_1_left_attack_2.png"),
                Resources.getImage("/sprite_bosses/boss_stage_1_left_attack_3.png")};
    }

    public void init() {

        this.x = 900;
        this.y = 240;
        this.x2 = 1200;
        this.x3 = 1300;
        this.x4 = 1700;
        this.x5 = 2200;
        this.range = 0;
        this.bossX = 450;
        this.bossY = 200;
        right = true;
        left = true;
        rightLimits = false;
        leftLimits = false;

        this.rectangles = new Rectangle[] {
                new Rectangle(this.x + 35, this.y + 8, 55, 83),
                new Rectangle(this.x2 + 35, this.y + 8, 55, 83),
                new Rectangle(this.x3 + 40, this.y + 8, 45, 83),
                new Rectangle(this.x4 + 40, this.y + 8, 45, 83),
                new Rectangle(this.x5 + 35, this.y + 8, 55, 83)};

        this.bossCollisionBox = new Rectangle[] {
                new Rectangle(this.bossX + 65, this.bossY + 37, 65, 90),
                new Rectangle(this.bossX + 165, this.bossY + 60, 20, 20)};
    }

    public void updateCoordinates() {
        if (playerSprite.isRight() && playerSprite.getX2() > 230 && playerSprite.getX2() >= playerSprite.getMaxXReached()) {
            this.x = this.x + playerSprite.getDxInverted();
            this.x2 = this.x2 + playerSprite.getDxInverted();
            this.x3 = this.x3 + playerSprite.getDxInverted();
            this.x4 = this.x4 + playerSprite.getDxInverted();
            this.x5 = this.x5 + playerSprite.getDxInverted();

            this.rectangles[0].setLocation(rectangles[0].x + playerSprite.getDxInverted(), rectangles[0].y);
            this.rectangles[1].setLocation(rectangles[1].x + playerSprite.getDxInverted(), rectangles[1].y);
            this.rectangles[2].setLocation(rectangles[2].x + playerSprite.getDxInverted(), rectangles[2].y);
            this.rectangles[3].setLocation(rectangles[3].x + playerSprite.getDxInverted(), rectangles[3].y);
            this.rectangles[4].setLocation(rectangles[4].x + playerSprite.getDxInverted(), rectangles[4].y);
        }
    }

    /**
     * Gestisce le animazioni dei nemici mediante l'istanziazione di un thread per ogni nemico.
     */

    public void Animation() {

        new Thread(()-> {
            while (!enemy.isDead()) {
                if (playerSprite.getX2() >= this.x + 600) {
                    for (int i = 0; i < boxerAnimationRight.length; i++) {
                        this.activeImage = boxerAnimationRight[i];
                        panel.repaint();
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (playerSprite.getX2() < this.x + 600) {
                    for (int i = 0; i < boxerAnimationLeft.length; i++) {
                        this.activeImage = boxerAnimationLeft[i];
                        panel.repaint();
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        new Thread(()-> {
            while (!enemy.isDead2()) {
                if (playerSprite.getX2() >= this.x2 + 850) {
                    for (int i = 0; i < boxerAnimationRight.length; i++) {
                        this.activeImage2 = boxerAnimationRight[i];
                        panel.repaint();
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (playerSprite.getX2() < this.x2 + 850) {
                    for (int i = 0; i < boxerAnimationLeft.length; i++) {
                        this.activeImage2 = boxerAnimationLeft[i];
                        panel.repaint();
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        new Thread(()-> {
            while (!enemy.isDead3()) {
                if (playerSprite.getX2() >= this.x3 + 1000) {
                    for (int i = 0; i < enemySwordAnimationRight.length; i++) {
                        this.activeImage3 = enemySwordAnimationRight[i];
                        panel.repaint();
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (playerSprite.getX2() < this.x3 + 1000) {
                    for (int i = 0; i < enemySwordAnimationLeft.length; i++) {
                        this.activeImage3 = enemySwordAnimationLeft[i];
                        panel.repaint();
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        new Thread(()-> {
            while (!enemy.isDead4()) {
                if (playerSprite.getX2() >= this.x4 + 1600) {
                    for (int i = 0; i < enemyWoodenClubAnimationRight.length; i++) {
                        this.activeImage4 = enemyWoodenClubAnimationRight[i];
                        panel.repaint();
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (playerSprite.getX2() < this.x4 + 1600) {
                    for (int i = 0; i < enemyWoodenClubAnimationLeft.length; i++) {
                        this.activeImage4 = enemyWoodenClubAnimationLeft[i];
                        panel.repaint();
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        new Thread(()-> {
            while (!enemy.isDead5()) {
                if (playerSprite.getX2() >= this.x5 + 1900) {
                    for (int i = 0; i < boxerAnimationRight.length; i++) {
                        this.activeImage5 = boxerAnimationRight[i];
                        panel.repaint();
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (playerSprite.getX2() < this.x5 + 1900) {
                    for (int i = 0; i < boxerAnimationLeft.length; i++) {
                        this.activeImage5 = boxerAnimationLeft[i];
                        panel.repaint();
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        new Thread(()-> {
           while (!LayerPanel.getInstance().getBoss().isDead()) {
               if (this.bossX >= 450) {
                   while (!leftLimits) {
                       right = false;
                       left = true;
                       this.bossCollisionBox[0].setLocation(this.bossX + 65, this.bossY + 37);
                       this.bossCollisionBox[1].setLocation(this.bossX, this.bossY + 60);
                       increment();
                       for (int i = 0; i < bossAnimationLeft.length; i++) {
                           this.bossActiveImage = bossAnimationLeft[i];
                           panel.repaint();
                           bossX = bossX + dx;
                           try {
                               Thread.sleep(100);
                           } catch (InterruptedException e) {
                               e.printStackTrace();
                           }
                       }
                   }
               } else if (this.bossX <= 25) {
                   while (!rightLimits) {
                       left = false;
                       right = true;
                       this.bossCollisionBox[0].setLocation(this.bossX + 65, this.bossY + 37);
                       this.bossCollisionBox[1].setLocation(this.bossX + 165, this.bossY + 60);
                       increment();
                       for (int i = 0; i < bossAnimationRight.length; i++) {
                           this.bossActiveImage = bossAnimationRight[i];
                           panel.repaint();
                           bossX = bossX + dx;
                           try {
                               Thread.sleep(100);
                           } catch (InterruptedException e) {
                               e.printStackTrace();
                           }
                       }

                   }
               }
           }
        }).start();

    }

    /**
     * Incrementa la coordinata x del boss di 2 finchè non arriva al limite destro della mappa
     * dopodichè la decrementa di 2 finchè non arriva al limite sinistro.
     * Se la collisione del boss collide con quella del giocatore l'incremento e pari a 0.
     */

    public void increment() {
        if (this.bossCollisionBox[0].intersects(playerSprite.getCollisionBox()) && LayerPanel.getInstance().isBossRoom()){
            dx = 0;
        } else if (right) {
            dx = + 2;
        } else if (left) {
            dx = - 2;
        }

        if (this.bossX >= 450) {
            rightLimits = true;
        } else if (this.bossX <= 25) {
            leftLimits = true;
        } else {
            rightLimits = false;
            leftLimits = false;
        }
    }

}
