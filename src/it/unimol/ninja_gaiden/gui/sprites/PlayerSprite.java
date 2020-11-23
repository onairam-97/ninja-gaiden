package it.unimol.ninja_gaiden.gui.sprites;

import it.unimol.ninja_gaiden.gui.animations.SpriteAnimation;
import it.unimol.ninja_gaiden.gui.panels.LayerPanel;
import it.unimol.ninja_gaiden.gui.animations.JumpSpriteAnimation;
import it.unimol.ninja_gaiden.gui.utils.Resources;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Classe che rappresenta il personaggio del gioco disegnabile.
 * Quando viene istanziata vengono inizializzati gli attributi della classe e tutte le immagini.
 * La classe fornisce le seguenti funzionalità:
 * - gestione delle azioni del personaggio.
 *
 * @author Mariano Buttino
 */

public class PlayerSprite {

    private final int LEFT_START_POSITION = 0;
    private final int RIGHT_START_POSITION = 1;
    private final int RIGHT_LOWER = 0;
    private final int LEFT_LOWER = 1;

    public final int FLOORY = 240;
    public final int FLOORY2 = 210;
    public final int FLOORY3 = 160;

    private int x;
    private int dx;
    private int y;
    private int dy;
    private int x2;
    private int dxInverted;

    private int widht;
    private int height;

    private Image[] raceAnimationRight;
    private Image[] raceAnimationLeft;
    private Image[] somersaultRight;
    private Image[] somersaultLeft;
    private Image[] startPositionAnimation;
    private Image[] lowerAnimation;
    private Image[] attackRightAnimation;
    private Image[] attackLeftAnimation;
    private Image[] throwShurikenRight;
    private Image[] throwShurikenLeft;

    private Image activeImage;

    private AtomicBoolean isRunning;
    private AtomicBoolean isAttacking;
    private AtomicBoolean isThrowing;

    private boolean isAnimationActive;
    private boolean right;
    private boolean left;
    private boolean releasedLeft;
    private boolean releasedRight;
    private boolean isJumping;
    private boolean up;
    private boolean releasedUp;
    private boolean down;
    private boolean releasedDown;
    private boolean shuriken;
    private boolean fireball;
    private boolean attack;
    private boolean range;
    private boolean leftLimits;
    private boolean rightLimits;

    private Rectangle collisionBox;
    private Rectangle attackCollisionBox;

    private int maxXReached;

    public PlayerSprite() {

        initPlayer();

        initAnimation();

        activeImage = this.startPositionAnimation[RIGHT_START_POSITION];

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

    public boolean isLeftLimits() {
        return leftLimits;
    }

    public int getMaxXReached() {
        return maxXReached;
    }

    public AtomicBoolean getISAttacking() {
        return isAttacking;
    }

    public void setIsAttacking(boolean attacking) {
        isAttacking.set(attacking);
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isJumping() {
        return isJumping;
    }

    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }

    public boolean isReleasedUp() {
        return releasedUp;
    }

    public void setReleasedUp(boolean releasedUp) {
        this.releasedUp = releasedUp;
    }

    public Image[] getSomersaultRight() {
        return somersaultRight;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getDy() {
        return dy;
    }

    public boolean isReleasedLeft() {
        return releasedLeft;
    }

    public void setReleasedLeft(boolean releasedLeft) {
        this.releasedLeft = releasedLeft;
    }

    public boolean isReleasedRight() {
        return releasedRight;
    }

    public void setReleasedRight(boolean releasedRight) {
        this.releasedRight = releasedRight;
    }

    public boolean isReleasedDown() {
        return releasedDown;
    }

    public void setReleasedDown(boolean releasedDown) {
        this.releasedDown = releasedDown;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDxInverted() {
        return dxInverted;
    }

    public void setDxInverted(int dxInverted) {
        this.dxInverted = dxInverted;
    }

    public int getX2() {
        return x2;
    }

    public AtomicBoolean getIsRunning() {
        return isRunning;
    }

    public void setIsRunning(boolean running) {
        isRunning.set(running);
    }

    public AtomicBoolean getIsThrowing() {
        return isThrowing;
    }

    public void setIsThrowing(boolean throwing) {
        isThrowing.set(throwing);
    }

    public Image[] getRaceAnimationRight() {
        return raceAnimationRight;
    }

    public Image[] getStartPositionAnimation() {
        return startPositionAnimation;
    }

    public Image getActiveImage() {
        return activeImage;
    }

    public int getWidht() {
        return widht;
    }

    public void setWidht(int widht) {
        this.widht = widht;
    }

    public int getHeight() {
        return height;
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

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setActiveImage(Image activeImage) {
        this.activeImage = activeImage;
    }

    public boolean isAnimationActive() {
        return isAnimationActive;
    }

    public void setAnimationActive(boolean animationActive) {
        isAnimationActive = animationActive;
    }

    public boolean isShuriken() {
        return shuriken;
    }

    public void setShuriken(boolean shuriken) {
        this.shuriken = shuriken;
    }

    public boolean isFireball() {
        return fireball;
    }

    public void setFireball(boolean fireball) {
        this.fireball = fireball;
    }

    public boolean isAttack() {
        return attack;
    }

    public void setAttack(boolean attack) {
        this.attack = attack;
    }

    public boolean isRange() {
        return range;
    }

    public void setRange(boolean range) {
        this.range = range;
    }

    public Rectangle getCollisionBox() {
        return collisionBox;
    }

    public void setCollisionBox(Rectangle collisionBox) {
        this.collisionBox = collisionBox;
    }

    public Rectangle getAttackCollisionBox() {
        return attackCollisionBox;
    }

    public void setAttackCollisionBox(Rectangle attackCollisionBox) {
        this.attackCollisionBox = attackCollisionBox;
    }

    public boolean isRightLimits() {
        return rightLimits;
    }

    public void setRightLimits(boolean rightLimits) {
        this.rightLimits = rightLimits;
    }

    /**
     * Incrementa di uno la coordinata x del personaggio se viene premuta la freccetta a destra, la decrementa
     * di 2 se viene premuta quella sinistra e istanzia il thread che si occupa della relativa animazione.
     * Se il personaggio collide l'incremento è pari a 0.
     */

    public void move() {

        if (right && !rightLimits) {
            this.dx = 1;
            this.dxInverted = -1;
            if (LayerPanel.getInstance().isCollideFromLeft()) {
                this.dx = 0;
                this.dxInverted = 0;
            } } else if (left && !leftLimits) {
            this.dx = -1;
            this.dxInverted = 1;
            if (LayerPanel.getInstance().isCollideFromRight()) {
                this.dx = 0;
                this.dxInverted = 0;
            } } else {
                this.dx = 0;
                this.dxInverted = 0;
            }


        if (x <= - 37) {
            leftLimits = true;
        } else if (x >= 545){
            rightLimits = true;
        } else {
            leftLimits = false;
            rightLimits = false;
        }

        if (x <= 230 && right) {
            x = x + dx;
            x2 = x2 + dx;
            if (x2 > maxXReached) {
                maxXReached = x2;
            }
            collisionBox.setBounds(this.x + 40, this.y + 27, this.widht - 75, this.height - 55);
            attackCollisionBox.setBounds(this.x + 100, this.y + 35, this.widht - 100, this.height - 100);
            if (this.isRunning.get() && !isAnimationActive) {
                new SpriteAnimation(raceAnimationRight, LayerPanel.getInstance(), this, isRunning);
            }

        } else if (x2 <= 2663 && right) {
            collisionBox.setBounds(this.x + 40, this.y + 27, this.widht - 75, this.height - 55);
            attackCollisionBox.setBounds(this.x + 100, this.y + 35, this.widht - 100, this.height - 100);
            Rectangle[] rectangles = LayerPanel.getInstance().getRectangles();
            LayerPanel.getInstance().setMapX(LayerPanel.getInstance().getMapX() + dx);
            rectangles[0].setLocation(rectangles[0].x + dxInverted, rectangles[0].y);
            rectangles[1].setLocation(rectangles[1].x + dxInverted, rectangles[1].y);
            rectangles[2].setLocation(rectangles[2].x + dxInverted, rectangles[2].y);
            rectangles[3].setLocation(rectangles[3].x + dxInverted, rectangles[3].y);

            x2 = x2 + dx;
            if (x2 > maxXReached) {
                maxXReached = x2;
            }
            if (this.isRunning.get() && !isAnimationActive) {
                new SpriteAnimation(raceAnimationRight, LayerPanel.getInstance(), this, isRunning);
            }
        } else if (left) {
            collisionBox.setBounds(this.x + 40, this.y + 27, this.widht - 75, this.height - 55);
            attackCollisionBox.setBounds(this.x, this.y + 35, this.widht - 100, this.height - 100);
            x = x + dx;
            x2 = x2 + dx;
            if (x2 > maxXReached) {
                maxXReached = x2;
            }
            if (this.isRunning.get() && !isAnimationActive) {
                new SpriteAnimation(raceAnimationLeft, LayerPanel.getInstance(), this, isRunning);
            }
        } else if (right) {
            Rectangle[] rectangles = LayerPanel.getInstance().getRectangles();
            rectangles[3].setLocation(rectangles[3].x + dxInverted, rectangles[3].y);
            collisionBox.setBounds(this.x + 40, this.y + 27, this.widht - 75, this.height - 55);
            attackCollisionBox.setBounds(this.x + 100, this.y + 35, this.widht - 100, this.height - 100);
            LayerPanel.getInstance().setMapX(2433);
            x = x + dx;
            x2 = x2 + dx;
            if (x2 > maxXReached) {
                maxXReached = x2;
            }
            if (this.isRunning.get() && !isAnimationActive) {
                new SpriteAnimation(raceAnimationRight, LayerPanel.getInstance(), this, isRunning);
            }
        }

        if (LayerPanel.getInstance().isCollidefromAbove() && !isJumping) {
            this.y = 204;
        } else if (!LayerPanel.getInstance().isCollidefromAbove() && this.y < FLOORY && !isJumping) {
            this.y = FLOORY;
        }

        if (up && !isJumping && releasedRight) {
            this.setJumping(true);
            new JumpSpriteAnimation(this, LayerPanel.getInstance(), somersaultRight);
        } else if (up && !isJumping && releasedLeft) {
            this.setJumping(true);
            new JumpSpriteAnimation(this, LayerPanel.getInstance(), somersaultLeft);
        } else if (up && !isJumping) {
            this.setJumping(true);
            new JumpSpriteAnimation(this, LayerPanel.getInstance(), somersaultRight);
        }

        if (down && releasedRight) {
            collisionBox.setBounds(this.x + 40, this.y + 44, this.widht - 75, this.height - 72);
            activeImage = this.lowerAnimation[RIGHT_LOWER];
        } else if (releasedDown && releasedRight && !isRunning.get()) {
            collisionBox.setBounds(this.x + 40, this.y + 27, this.widht - 75, this.height - 55);
            activeImage = this.startPositionAnimation[RIGHT_START_POSITION];
        } else if (releasedDown && releasedLeft && !isRunning.get()) {
            collisionBox.setBounds(this.x + 40, this.y + 27, this.widht - 75, this.height - 55);
            activeImage = this.startPositionAnimation[LEFT_START_POSITION];
        } else if (releasedLeft && down) {
            collisionBox.setBounds(this.x + 40, this.y + 44, this.widht - 75, this.height - 72);
            activeImage = this.lowerAnimation[LEFT_LOWER];
        } else if (down && !releasedDown) {
            collisionBox.setBounds(this.x + 40, this.y + 44, this.widht - 75, this.height - 72);
            activeImage = this.lowerAnimation[RIGHT_LOWER];
        } else if (releasedDown && !isRunning.get()) {
            collisionBox.setBounds(this.x + 40, this.y + 27, this.widht - 75, this.height - 55);
            activeImage = this.startPositionAnimation[RIGHT_START_POSITION];
        }

    }

    /**
     * Istanzia un thread che si occupa della relativa animazione.
     */

    public void attack() {

        if (attack && !isAnimationActive && releasedRight) {
            new SpriteAnimation(attackRightAnimation, LayerPanel.getInstance(), this, isAttacking);
        } else if (attack && !isAnimationActive && releasedLeft) {
            new SpriteAnimation(attackLeftAnimation, LayerPanel.getInstance(), this, isAttacking);
        } else if (attack && !isAnimationActive) {
            new SpriteAnimation(attackRightAnimation, LayerPanel.getInstance(), this, isAttacking);
        }

        if (this.isThrowing.get() && !isAnimationActive && releasedRight) {
            new SpriteAnimation(throwShurikenRight, LayerPanel.getInstance(), this, isThrowing);
        } else if (this.isThrowing.get() && !isAnimationActive && releasedLeft) {
            new SpriteAnimation(throwShurikenLeft, LayerPanel.getInstance(), this, isThrowing);
        } else if (this.isThrowing.get() && !isAnimationActive) {
            new SpriteAnimation(throwShurikenRight, LayerPanel.getInstance(), this, isThrowing);
        }
    }

    private void initAnimation() {

        this.raceAnimationRight = new BufferedImage[]{
                Resources.getImage("/sprite_player/race_right_0.png"),
                Resources.getImage("/sprite_player/race_right_1.png"),
                Resources.getImage("/sprite_player/race_right_2.png")};

        this.raceAnimationLeft = new BufferedImage[]{
                Resources.getImage("/sprite_player/race_left_0.png"),
                Resources.getImage("/sprite_player/race_left_1.png"),
                Resources.getImage("/sprite_player/race_left_2.png")};

        this.startPositionAnimation = new BufferedImage[]{
                Resources.getImage("/sprite_player/start_position_left.png"),
                Resources.getImage("/sprite_player/start_position.png")};

        this.somersaultRight = new BufferedImage[]{
                Resources.getImage("/sprite_player/somersault_right_0.png"),
                Resources.getImage("/sprite_player/somersault_right_1.png"),
                Resources.getImage("/sprite_player/somersault_right_2.png"),
                Resources.getImage("/sprite_player/somersault_right_3.png")};

        this.somersaultLeft = new BufferedImage[]{
                Resources.getImage("/sprite_player/somersault_left_0.png"),
                Resources.getImage("/sprite_player/somersault_left_1.png"),
                Resources.getImage("/sprite_player/somersault_left_2.png"),
                Resources.getImage("/sprite_player/somersault_left_3.png")};

        this.lowerAnimation = new BufferedImage[]{
                Resources.getImage("/sprite_player/lower.png"),
                Resources.getImage("/sprite_player/lower_left.png")};

        this.attackRightAnimation = new BufferedImage[]{
                Resources.getImage("/sprite_player/attack_0.png"),
                Resources.getImage("/sprite_player/attack_1.png"),
                Resources.getImage("/sprite_player/attack_2.png")};

        this.attackLeftAnimation = new BufferedImage[]{
                Resources.getImage("/sprite_player/attack_left_0.png"),
                Resources.getImage("/sprite_player/attack_left_1.png"),
                Resources.getImage("/sprite_player/attack_left_2.png")};

        this.throwShurikenRight = new BufferedImage[]{
                Resources.getImage("/sprite_player/throw_shuriken_0.png"),
                Resources.getImage("/sprite_player/throw_shuriken_1.png"),
                Resources.getImage("/sprite_player/throw_shuriken_2.png")};

        this.throwShurikenLeft = new BufferedImage[]{
                Resources.getImage("/sprite_player/throw_shuriken_0_left.png"),
                Resources.getImage("/sprite_player/throw_shuriken_1_left.png"),
                Resources.getImage("/sprite_player/throw_shuriken_2_left.png")};
    }

    private void initPlayer() {
        x = 10;
        y = 240;
        widht = 120;
        height = 120;
        x2 = 10;
        isAnimationActive = false;
        isJumping = false;
        up = false;
        down = false;
        right = false;
        left = false;
        releasedDown = true;
        shuriken = false;
        fireball = false;
        attack = false;
        range = false;
        releasedLeft = false;
        releasedRight = false;
        leftLimits = false;
        rightLimits = false;
        maxXReached = 10;


        this.isRunning = new AtomicBoolean(false);
        this.isAttacking = new AtomicBoolean(false);
        this.isThrowing = new AtomicBoolean(false);

        collisionBox = new Rectangle(this.x + 40, this.y + 27, this.widht - 75, this.height - 55);
        attackCollisionBox = new Rectangle(this.x + 100, this.y + 35, this.widht - 100, this.height - 100);
    }
}