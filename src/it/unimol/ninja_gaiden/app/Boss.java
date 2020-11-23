package it.unimol.ninja_gaiden.app;

import it.unimol.ninja_gaiden.gui.panels.LayerPanel;

public class Boss {

    private int health;
    private boolean dead;
    private int damage;
    private int score;

    public Boss() {

        init();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getScore() {
        return score;
    }

    public void init() {

        this.health = 3200;
        this.dead = false;
        this.damage = 2;
        this.score = 250;
    }

    public void update() {

        this.health = this.health - LayerPanel.getInstance().getPlayer().getDamage();
        this.health = this.health - LayerPanel.getInstance().getSkill().getShurikenDamage();
        this.health = this.health - LayerPanel.getInstance().getSkill().getFireballDamege();

        if (this.health <= 0)
            this.dead = true;
    }
}
