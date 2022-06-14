package game;

import exceptions.InvalidRuleException;
import ioModel.InputOutputStream;
import model.ComputerPlayer;
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
import repository.GameScoreRepository;
import rules.RuleEngine;
import service.RPSGameService;
import service.RpsGameServiceImpl;

import java.io.IOException;
import java.util.Optional;

import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class TwoPlayerGameTest {

    @Mock
    private Player humanPlayer;

    @Mock
    private Player computerPlayer;

    @Mock
    private RuleEngine engine;

    @Mock
    private InputOutputStream inputOutputStream;

    @Before
    public void setup(){
        humanPlayer = mock(HumanPlayer.class);
        computerPlayer = mock(ComputerPlayer.class);
        engine = mock(RuleEngine.class);
        inputOutputStream = mock(InputOutputStream.class);
    }

    @Test
    public void testShouldReturnHumanPlayerAsWinnerWhenHumanPlayerWins() throws InvalidRuleException, IOException {
        Mockito.when(humanPlayer.getInput()).thenReturn(Optional.of(GameInputObject.PAPER));
        Mockito.when(computerPlayer.getInput()).thenReturn(Optional.of(GameInputObject.ROCK));

        Mockito.when(engine.get(Mockito.eq(GameInputObject.PAPER))).thenReturn((arg) -> true);
        Mockito.when(engine.get(Mockito.eq(GameInputObject.ROCK))).thenReturn((arg) -> false);

        Optional<Player> winner = createNewRPSGame().play(inputOutputStream);

        Assert.assertTrue(winner.isPresent());
        Assert.assertEquals(humanPlayer, winner.get());
    }

    @Test
    public void testShouldReturnComputerPlayerAsWinnerWhenComputerPlayerWins() throws InvalidRuleException, IOException {
        Mockito.when(humanPlayer.getInput()).thenReturn(Optional.of(GameInputObject.PAPER));
        Mockito.when(computerPlayer.getInput()).thenReturn(Optional.of(GameInputObject.SCISSORS));

        Mockito.when(engine.get(Mockito.eq(GameInputObject.PAPER))).thenReturn((arg) -> false);
        Mockito.when(engine.get(Mockito.eq(GameInputObject.SCISSORS))).thenReturn((arg) -> true);

        Optional<Player> winner = createNewRPSGame().play(inputOutputStream);

        Assert.assertTrue(winner.isPresent());
        Assert.assertEquals(computerPlayer, winner.get());
    }

    @Test
    public void testShouldReturnEmptyResultOnDraw() throws InvalidRuleException, IOException {
        Mockito.when(humanPlayer.getInput()).thenReturn(Optional.of(GameInputObject.SCISSORS));
        Mockito.when(computerPlayer.getInput()).thenReturn(Optional.of(GameInputObject.SCISSORS));

        Mockito.when(engine.get(Mockito.eq(GameInputObject.SCISSORS))).thenReturn((arg) -> false);

        Optional<Player> winner = createNewRPSGame().play(inputOutputStream);

        Assert.assertFalse(winner.isPresent());
    }

    private RPSGameService createNewRPSGame() {
        return new RpsGameServiceImpl(humanPlayer, computerPlayer, engine, mock(GameScoreRepository.class));
    }
}


