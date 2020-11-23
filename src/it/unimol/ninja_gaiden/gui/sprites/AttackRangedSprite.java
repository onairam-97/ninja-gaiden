package it.unimol.ninja_gaiden.gui.sprites;

import it.unimol.ninja_gaiden.gui.sprites.PlayerSprite;
import it.unimol.ninja_gaiden.gui.animations.SpriteSkillAttackAnimation;
import it.unimol.ninja_gaiden.gui.panels.LayerPanel;
import it.unimol.ninja_gaiden.gui.utils.Resources;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Classe che rappresenta un'abilità del personaggio disegnabile.
 * Quando viene istanziata richiede un'instanza di {@link PlayerSprite}.
 * La classe fornisce le seguenti funzionalità:
 * - gestione delle animazioni.
 *
 * @author Mariano Buttino
 */

public class AttackRangedSprite {

    private Image[] shurikenRightAnimation;
    private Image[] shurikenLeftAnimation;
    private Image[] fireballRight;
    private Image activeImage;

    private PlayerSprite playerSprite;

    private int x;
    private int y;

    private boolean isAnimationActive;

    private Rectangle collisionBox;

    public Image[] getShurikenRightAnimation() {
        return shurikenRightAnimation;
    }

    public Image getActiveImage() {
        return activeImage;
    }

    public void setActiveImage(Image activeImage) {
        this.activeImage = activeImage;
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

    public boolean isAnimationActive() {
        return isAnimationActive;
    }

    public void setAnimationActive(boolean animationActive) {
        isAnimationActive = animationActive;
    }

    public Rectangle getCollisionBox() {
        return collisionBox;
    }

    public void setCollisionBox(Rectangle collisionBox) {
        this.collisionBox = collisionBox;
    }

    public AttackRangedSprite(PlayerSprite playerSprite) {

        this.playerSprite = playerSprite;
        this.activeImage = null;

        this.isAnimationActive = false;

        collisionBox = new Rectangle(playerSprite.getX() + 60, playerSprite.getY() + 5, 20, 20);

        initAnimation();

    }

    public void initAnimation() {

        this.shurikenRightAnimation = new BufferedImage[] {
                Resources.getImage("/sprite_item_and_conteiners/shuriken_0.png"),
                Resources.getImage("/sprite_item_and_conteiners/shuriken_1.png")};

        this.shurikenLeftAnimation = new BufferedImage[] {
                Resources.getImage("/sprite_item_and_conteiners/shuriken_left_0.png"),
                Resources.getImage("/sprite_item_and_conteiners/shuriken_left_1.png")};

        this.fireballRight = new  BufferedImage[] {
                Resources.getImage("/sprite_item_and_conteiners/fireball.png")};

    }

    /**
     * Gestisce l'animazione dell'attacco a distanza in base all'ultimo tasto premuto.
     */

    public void rangedAttack() {
        if (playerSprite.getIsThrowing().get() && playerSprite.isShuriken() && !isAnimationActive && !LayerPanel.getInstance().isPressed() && playerSprite.isReleasedRight()) {
            new SpriteSkillAttackAnimation(LayerPanel.getInstance(), shurikenRightAnimation, this.playerSprite, this);
        } else if (playerSprite.getIsThrowing().get() && playerSprite.isShuriken() && !isAnimationActive && !LayerPanel.getInstance().isPressed() && playerSprite.isReleasedLeft()) {
            new SpriteSkillAttackAnimation(LayerPanel.getInstance(), shurikenLeftAnimation, this.playerSprite, this);
        } else if (playerSprite.getIsThrowing().get() && playerSprite.isShuriken() && !isAnimationActive && !LayerPanel.getInstance().isPressed()){
            new SpriteSkillAttackAnimation(LayerPanel.getInstance(), shurikenRightAnimation, this.playerSprite, this);
        }
        if (playerSprite.getIsThrowing().get() && playerSprite.isFireball() && !isAnimationActive && !LayerPanel.getInstance().isPressed()) {
            new SpriteSkillAttackAnimation(LayerPanel.getInstance(), fireballRight, this.playerSprite, this);
        }
    }
}
