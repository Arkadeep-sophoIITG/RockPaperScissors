package models;

import exceptions.InvalidPlayerNameException;
import ioModel.InputOutputStream;
import model.GameInputObject;
import model.HumanPlayer;
import model.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import utils.RPSLogger;

import java.io.IOException;

import static org.mockito.Mockito.mock;


@ExtendWith(MockitoExtension.class)
public class HumanPlayerTest {

    @Mock
    private InputOutputStream inputOutputStream;

    @Before
    public void setup(){
        inputOutputStream = mock(InputOutputStream.class);
    }

    @Test
    public void testBuildHumanPlayerWithValidName() throws Exception {
        Player humanPlayer = buildNewHumanPlayerWithName("robin");
        Assert.assertEquals("robin", humanPlayer.getName());
    }

    @Test
    public void testShouldThrowInvalidNameException() throws IOException, InvalidPlayerNameException {
        Mockito.when(inputOutputStream.getInputString()).thenReturn("");
        Assert.assertThrows(InvalidPlayerNameException.class, () -> HumanPlayer.buildPlayer(inputOutputStream,
                mock(RPSLogger.class)));
    }

    @Test
    public void testShouldReturnValidInputObject() throws InvalidPlayerNameException, IOException {

        Player human = buildNewHumanPlayerWithName("arka");
        Mockito.when(inputOutputStream.getInputString()).thenReturn("Rock");
        Assert.assertEquals(GameInputObject.ROCK, human.getInput().get());

        Mockito.when(inputOutputStream.getInputString()).thenReturn("Paper");
        Assert.assertEquals(GameInputObject.PAPER, human.getInput().get());

        Mockito.when(inputOutputStream.getInputString()).thenReturn("Scissors");
        Assert.assertEquals(GameInputObject.SCISSORS, human.getInput().get());

    }

    public HumanPlayer buildNewHumanPlayerWithName(String name) throws IOException, InvalidPlayerNameException {
        Mockito.when(inputOutputStream.getInputString()).thenReturn(name);
        return HumanPlayer.buildPlayer(inputOutputStream, mock(RPSLogger.class));
    }


}
