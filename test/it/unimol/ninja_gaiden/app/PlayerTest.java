package it.unimol.ninja_gaiden.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void update() {

        Boss boss = new Boss();
        Enemy enemy = new Enemy();
        Player player = new Player(enemy, boss);

        player.update();

        assertEquals(797, player.getHealth());
    }
}