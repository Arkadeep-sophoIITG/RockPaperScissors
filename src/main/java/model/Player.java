package model;

import exceptions.InvalidPlayerNameException;

import java.util.Optional;
import java.util.UUID;

public abstract class Player {

    protected String id;
    private final String name;

    protected Player(String name) throws InvalidPlayerNameException {
        this.id = UUID.randomUUID().toString();
        isValidName(name);
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public String getId() { return id;}

    public abstract Optional<GameInputObject> getInput();

    private static void isValidName(String name) throws InvalidPlayerNameException {
        if (name == null || name.isBlank()) {
            throw new InvalidPlayerNameException("Player name cannot be empty");
        }
    }
}
