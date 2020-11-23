package it.unimol.ninja_gaiden.gui.exceptions;

public class SoundException extends Exception {

    public SoundException(String file, Exception ex) {
        super();
    }

    public SoundException(String message) {
        super(message);
    }
}
