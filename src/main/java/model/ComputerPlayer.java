package model;

import exceptions.InvalidPlayerNameException;
import utils.RPSLogger;

import java.time.LocalDateTime;
import java.util.*;

public class ComputerPlayer extends Player{

    private static final List<GameInputObject> possibleInputs = List.of(GameInputObject.values()).subList(0,3);
    private final Random randomNumberGenerator;
    private final RPSLogger LOGGER;


    private ComputerPlayer(String name, Long randomSeed, RPSLogger logger) throws InvalidPlayerNameException {
        super(name);
        this.randomNumberGenerator = new Random(randomSeed);
        this.LOGGER = logger;
    }

    /**
     *
     * @return  An instance of computerPlayer object using current nanoTime as random seed
     *          Also checks for InvalidPlayerNameException
     */

    public static ComputerPlayer buildPlayer(String name, RPSLogger logger) throws InvalidPlayerNameException {
        return new ComputerPlayer(name, (long) LocalDateTime.now().getNano(), logger);
    }

    /**
     *
     * @return  Returns a randomised valid game input object for computer.
     */

    @Override
    public Optional<GameInputObject> getInput() {
        Optional<GameInputObject> inputObject = Optional.of(possibleInputs.get(randomNumberGenerator.nextInt(possibleInputs.size())));
        LOGGER.logInfo("Computer with name : " + getName() + " played : " + inputObject.get().name());
        return inputObject;
    }
}
