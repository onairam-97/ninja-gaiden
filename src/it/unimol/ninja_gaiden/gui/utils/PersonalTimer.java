package it.unimol.ninja_gaiden.gui.utils;

public class PersonalTimer extends Thread {
    private long delay;
    private Runnable toDo;

    public PersonalTimer(long delay, Runnable toDo) {
        super();
        this.delay = delay;
        this.toDo = toDo;

        this.start();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(this.delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        toDo.run();
    }
}
