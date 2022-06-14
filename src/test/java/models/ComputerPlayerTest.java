package models;

import exceptions.InvalidPlayerNameException;
import model.ComputerPlayer;
import model.GameInputObject;
import model.Player;
import org.junit.Assert;
import org.junit.Test;
import utils.RPSLogger;

import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.mock;

public class ComputerPlayerTest {

    private static final Set<GameInputObject> validInputSet =
            Set.of(GameInputObject.ROCK, GameInputObject.PAPER, GameInputObject.SCISSORS);

    @Test
    public void testBuildBotPlayerWithValidName() throws InvalidPlayerNameException {
        Player computer = ComputerPlayer.buildPlayer("ALPHA-ZERO", mock(RPSLogger.class));
        Assert.assertEquals("ALPHA-ZERO", computer.getName());
    }

    @Test
    public void testThrowExceptionUsingInvalidName() throws InvalidPlayerNameException{
        Assert.assertThrows(InvalidPlayerNameException.class, () -> ComputerPlayer.buildPlayer("", mock(RPSLogger.class)));
    }

    @Test
    public void testThrowExceptionUsingNullName() throws InvalidPlayerNameException{
        Assert.assertThrows(InvalidPlayerNameException.class, () -> ComputerPlayer.buildPlayer(null, mock(RPSLogger.class)));
    }

    @Test
    public void testShouldReturnValidInputObject() throws InvalidPlayerNameException {
        Player computer = ComputerPlayer.buildPlayer("LEELA-365", mock(RPSLogger.class));
        Optional<GameInputObject> inputObject1 = computer.getInput();
        inputObject1.ifPresent(gameInputObject -> Assert.assertTrue(validInputSet.contains(gameInputObject)));
        Optional<GameInputObject> inputObject2 = computer.getInput();
        inputObject2.ifPresent(gameInputObject -> Assert.assertTrue(validInputSet.contains(gameInputObject)));
        Optional<GameInputObject> inputObject3 = computer.getInput();
        inputObject3.ifPresent(gameInputObject -> Assert.assertTrue(validInputSet.contains(gameInputObject)));
    }
}
