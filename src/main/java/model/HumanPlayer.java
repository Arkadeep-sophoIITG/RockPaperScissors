package model;

import exceptions.InvalidPlayerNameException;
import ioModel.InputOutputStream;
import utils.RPSLogger;

import java.io.IOException;
import java.util.Optional;

public class HumanPlayer extends Player{

    private String name;
    private final InputOutputStream ioStream;
    private final RPSLogger rpsLogger;

    /**
     *
     * @param inputOutputStream             UserInputOutPutStream object
     * @param logger                        Logger class
     * @return                              An instance of HumanPlayer
     * @throws IOException                  thrown when an error occurred during an input-output operation
     * @throws InvalidPlayerNameException   thrown when name of player is null or empty
     */

    public static HumanPlayer buildPlayer(InputOutputStream inputOutputStream, RPSLogger logger) throws IOException, InvalidPlayerNameException {
        inputOutputStream.printNewLine("Enter your name : ");
        String name = inputOutputStream.getInputString();
        return new HumanPlayer(name, inputOutputStream, logger);
    }

    private HumanPlayer(String name, InputOutputStream ioStream, RPSLogger logger) throws InvalidPlayerNameException {
        super(name);
        this.ioStream = ioStream;
        this.rpsLogger = logger;
    }


    /**
     *
     * @return  If valid input by user, maps to an Optional object of GameInputObject.  Also checks for IOException
     *          AND InvalidInputException.
     */

    @Override
    public Optional<GameInputObject> getInput() {
        ioStream.print("Play your move (Rock/Paper/Scissors). Type EXIT for exiting the game : ");
        String userInput;
        try {
            userInput = ioStream.getInputString().toUpperCase();
        }
        catch (IOException e){
            rpsLogger.logSevereError("Unexpected problem while reading user input", e);
            return Optional.empty();
        }
        try {
            GameInputObject inputObject = GameInputObject.valueOf(userInput);
            rpsLogger.logInfo("Player : " + getName() + " has played : " + inputObject.name());
            return Optional.of(inputObject);
        }
        catch (IllegalArgumentException exception) {
            rpsLogger.logSevereError("Invalid input by user. Input should be one among {ROCK, PAPER, SCISSORS}",
                    exception);
        }
        return Optional.empty();
    }
}
