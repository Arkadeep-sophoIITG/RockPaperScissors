package service;

import exceptions.InvalidRuleException;
import ioModel.InputOutputStream;
import model.Player;

import java.io.IOException;
import java.util.Optional;

public interface RPSGameService {

    /**
     *
     * @param inputOutputStream         UserInputOutput functionalities as seen on UI/Terminal
     * @return                          An Optional object of the victorious player of the game. Empty object if its a tie.
     * @throws InvalidRuleException     thrown when no valid rule exists for the user input.
     * @throws IOException              thrown when an error occurred during an input-output operation
     */

    Optional<Player> play(InputOutputStream inputOutputStream) throws InvalidRuleException, IOException;

    void shutDown(InputOutputStream inputOutputStream) throws IOException;
}
