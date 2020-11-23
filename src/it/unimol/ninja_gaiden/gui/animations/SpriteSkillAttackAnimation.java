package it.unimol.ninja_gaiden.gui.animations;

import it.unimol.ninja_gaiden.gui.sprites.PlayerSprite;
import it.unimol.ninja_gaiden.gui.sprites.AttackRangedSprite;

import javax.swing.*;
import java.awt.*;

/**
 * Threa che gestisce l'animazione degli attacchi a distanza.
 * Quando viene istanziato richiede un pannello, un array di immagini,
 * un'instanza di {@link PlayerSprite} e unì'istanza di {@link AttackRangedSprite}.
 *
 * @author Mariano Buttino
 */

public class SpriteSkillAttackAnimation extends Thread{

    private JPanel panel;
    private Image[] animation;
    private PlayerSprite playerSprite;
    private AttackRangedSprite attackRangedSprite;
    private int range;

    public SpriteSkillAttackAnimation(JPanel panel, Image[] animation,  PlayerSprite playerSprite, AttackRangedSprite attackRangedSprite) {
        this.panel = panel;
        this.animation = animation;
        this.attackRangedSprite = attackRangedSprite;
        this.playerSprite = playerSprite;

        this.start();
    }

    @Override
    public void run() {
        super.run();

        attackRangedSprite.setAnimationActive(true);

        if (playerSprite.isReleasedRight()) {
            attackRangedSprite.setX(playerSprite.getX() + 60);
            attackRangedSprite.setY(playerSprite.getY() + 5);
        } else if (playerSprite.isReleasedLeft()) {
            attackRangedSprite.setX(playerSprite.getX() - 20);
            attackRangedSprite.setY(playerSprite.getY() + 5);
        } else {
            attackRangedSprite.setX(playerSprite.getX() + 60);
            attackRangedSprite.setY(playerSprite.getY() + 5);
        }

        this.range = 0;

        boolean isReleasedRight = playerSprite.isReleasedRight();
        boolean isReleasedLeft = playerSprite.isReleasedLeft();

        while (this.range <= 30) {
            if (isReleasedRight) {
                attackRangedSprite.setX(attackRangedSprite.getX() + 8);
                attackRangedSprite.getCollisionBox().setLocation(attackRangedSprite.getX() + 30, attackRangedSprite.getY() + 30);
            }
            else if (isReleasedLeft) {
                attackRangedSprite.setX(attackRangedSprite.getX() - 8);
                attackRangedSprite.getCollisionBox().setLocation(attackRangedSprite.getX() + 30, attackRangedSprite.getY() + 30);
            } else {
                attackRangedSprite.setX(attackRangedSprite.getX() + 8);
                attackRangedSprite.getCollisionBox().setLocation(attackRangedSprite.getX() + 30, attackRangedSprite.getY() + 30);
            }
            this.range++;
            for (int i = 0; i < animation.length; i++) {
                    attackRangedSprite.setActiveImage(animation[i]);
                    panel.repaint();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        }

        attackRangedSprite.setAnimationActive(false);
        attackRangedSprite.setActiveImage(null);

    }
}
