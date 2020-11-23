package it.unimol.ninja_gaiden.gui.animations;

import it.unimol.ninja_gaiden.gui.panels.LayerPanel;
import it.unimol.ninja_gaiden.gui.sprites.PlayerSprite;
import it.unimol.ninja_gaiden.gui.sprites.AttackRangedSprite;
import it.unimol.ninja_gaiden.gui.utils.Resources;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Thread che geatisce le animazioni del personaggio.
 * Quando viene istanziato richiede un array di immagini, un pannello, un'istanza di {@link PlayerSprite} e un AtomicBoolean.
 *
 * @author Mariano Buttino
 */

public class SpriteAnimation extends Thread {

    private Image[] animation;
    private Image idleLeft;
    private Image idleRight;
    private JPanel panel;
    private PlayerSprite playerSprite;
    private AtomicBoolean isActive;
    private AttackRangedSprite attackRangedSprite;

    public SpriteAnimation(Image[] animation, JPanel panel, PlayerSprite playerSprite, AtomicBoolean isActive) {
        this.animation = animation;
        this.panel = panel;
        this.playerSprite = playerSprite;
        this.isActive = isActive;

        attackRangedSprite = new AttackRangedSprite(this.playerSprite);

        this.idleLeft = Resources.getImage("/sprite_player/start_position_left.png");
        this.idleRight = Resources.getImage("/sprite_player/start_position.png");

        this.start();
    }

    @Override
    public void run() {
        super.run();

        playerSprite.setAnimationActive(true);

        while (isActive.get() && !LayerPanel.getInstance().isPressed()) {
            for (int i = 0; i < animation.length; i++) {
                if (!isActive.get())
                    this.interrupt();
                 else {
                    playerSprite.setActiveImage(animation[i]);
                    panel.repaint();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


        playerSprite.setAnimationActive(false);

        if (playerSprite.isReleasedRight()) {
            playerSprite.setActiveImage(idleRight);
        } else if (playerSprite.isReleasedLeft()){
            playerSprite.setActiveImage(idleLeft);
        } else
            playerSprite.setActiveImage(idleRight);


    }
}