package it.unimol.ninja_gaiden.app;

public class Enemy {

    private int score;
    private int damage;
    private boolean dead;
    private boolean dead2;
    private boolean dead3;
    private boolean dead4;
    private boolean dead5;

    public Enemy() {

        init();
    }


    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public boolean isDead2() {
        return dead2;
    }

    public void setDead2(boolean dead2) {
        this.dead2 = dead2;
    }

    public boolean isDead3() {
        return dead3;
    }

    public void setDead3(boolean dead3) {
        this.dead3 = dead3;
    }

    public boolean isDead4() {
        return dead4;
    }

    public void setDead4(boolean dead4) {
        this.dead4 = dead4;
    }

    public boolean isDead5() {
        return dead5;
    }

    public void setDead5(boolean dead5) {
        this.dead5 = dead5;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void init() {
        score = 50;
        damage = 1;
        dead = false;
        dead2 = false;
        dead3 = false;
        dead4 = false;
        dead5 = false;
    }
}
