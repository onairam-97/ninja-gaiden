package it.unimol.ninja_gaiden.app;

public class Skill {

    private int shurikenDamage;
    private int fireballDamege;

    public Skill() {

        init();
    }

    public int getShurikenDamage() {
        return shurikenDamage;
    }

    public int getFireballDamege() {
        return fireballDamege;
    }

    public void init() {

        this.shurikenDamage = 1;
        this.fireballDamege = 2;
    }
}
