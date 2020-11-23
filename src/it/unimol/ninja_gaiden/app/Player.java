package it.unimol.ninja_gaiden.app;

public class Player {

    private static final int MAXHEALTH = 800;

    private int health;
    private int damage;
    private boolean dead;
    private Enemy enemy;
    private Boss boss;

    public Player(Enemy enemy, Boss boss) {

        initPlayer();
        this.enemy = enemy;
        this.boss = boss;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isDead() {
        return dead;
    }



    public void initPlayer() {

        this.health = MAXHEALTH;
        this.damage = 1;
        this.dead = false;
    }

    public void update() {

        this.health = this.health - enemy.getDamage();
        this.health = this.health - boss.getDamage();

        if (health <= 0) {
            dead = true;
        }
    }

}
