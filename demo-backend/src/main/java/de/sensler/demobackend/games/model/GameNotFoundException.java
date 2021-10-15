package de.sensler.demobackend.games.model;

public class GameNotFoundException extends Exception {
    public GameNotFoundException(String message) {
        super(message);
    }
}
