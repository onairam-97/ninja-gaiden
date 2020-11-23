package it.unimol.ninja_gaiden.gui.animations;

import it.unimol.ninja_gaiden.gui.sprites.PlayerSprite;
import it.unimol.ninja_gaiden.gui.panels.LayerPanel;
import it.unimol.ninja_gaiden.gui.utils.Resources;

import javax.swing.*;
import java.awt.*;

/**
 * Thread che gestisce il salto del personaggio.
 * Quando viene inizializzato richiede un'istanza di {@link PlayerSprite}, un pannello e un array di immagini.
 * La classe fornisce le seguenti funzionalità:
 * - gestione del salto.
 *
 * @author Mariano Buttino
 */

public class JumpSpriteAnimation extends Thread {

    private PlayerSprite playerSprite;
    private boolean h = false;
    private boolean done = false;
    private Image idleRight;
    private Image idleLeft;
    private Image[] animation;
    private JPanel panel;

    public JumpSpriteAnimation(PlayerSprite playerSprite, JPanel panel, Image[] animation) {
        this.playerSprite = playerSprite;
        this.panel = panel;
        this.animation = animation;

        this.idleRight = Resources.getImage("/sprite_player/start_position.png");
        this.idleLeft = Resources.getImage("/sprite_player/start_position_left.png");
       this.start();
    }

    public PlayerSprite getPlayerSprite() {
        return playerSprite;
    }

    public void setPlayerSprite(PlayerSprite playerSprite) {
        this.playerSprite = playerSprite;
    }

    public boolean isH() {
        return h;
    }

    public void setH(boolean h) {
        this.h = h;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * Decrementa la coordinata y del personaggio fin quando non raggiunge il tetto massimo,
     * quando ciò avviene la coordinata y viene incrementata fin quando non raggiunge il pavimento o
     * non trova una collisione.
     */

    private void cycle() {

        if (!h)
            playerSprite.setY(playerSprite.getY() - 4);
        if (playerSprite.getY() == 160) //punto massimo del salto
            h = true;
        if (h && playerSprite.getY() <= playerSprite.FLOORY) {
            playerSprite.setY(playerSprite.getY() + 4);
            if (playerSprite.getY() == playerSprite.FLOORY || LayerPanel.getInstance().isCollidefromAbove()) {
                done = true;
            }
        }
    }


    @Override
    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (!done) {

            cycle();

            playerSprite.getCollisionBox().setBounds(playerSprite.getX() + 40, playerSprite.getY() + 27, playerSprite.getWidht() - 75, playerSprite.getHeight() - 55);

            for (int i = 0; i < animation.length; i++) {
                playerSprite.setActiveImage(animation[i]);
                panel.repaint();
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = 10 - timeDiff;

            if (sleep < 0)
                sleep = 3;
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }

            beforeTime = System.currentTimeMillis();

        }
        done = true;
        h = false;
        playerSprite.setJumping(false);

        if (this.done && playerSprite.isReleasedRight()) {
            playerSprite.setActiveImage(idleRight);
        } else if (this.done && playerSprite.isReleasedLeft()) {
            playerSprite.setActiveImage(idleLeft);
        } else if (this.done && !playerSprite.isReleasedLeft() && !playerSprite.isReleasedRight()) {
            playerSprite.setActiveImage(idleRight);
        }
    }


}

