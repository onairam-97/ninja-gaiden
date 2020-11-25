package it.unimol.ninja_gaiden.gui.panels;

import it.unimol.ninja_gaiden.app.Player;
import it.unimol.ninja_gaiden.gui.sprites.PlayerSprite;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class LayerPanelTest {

    @Test
    void Testcollide() {

        LayerPanel.getInstance().getPlayerSprite().setX2(618);

        LayerPanel.getInstance().getPlayerSprite().getCollisionBox().setBounds(705, 293,  45,  65);

        LayerPanel.getInstance().collide();

        System.out.println(LayerPanel.getInstance().getMapXRightCollision0());
        System.out.println(LayerPanel.getInstance().getPlayerSprite().getCollisionBox().x);
        System.out.println(LayerPanel.getInstance().getPlayerSprite().getY());

        assertTrue(LayerPanel.getInstance().isCollide());
    }
}