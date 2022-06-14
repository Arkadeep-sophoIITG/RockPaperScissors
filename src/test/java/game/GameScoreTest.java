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
import org.mockito.Mock;
import org.mockito.Mockito;
import repository.GameScoreRepository;
import rules.RuleEngine;
import service.RPSGameService;
import service.RpsGameServiceImpl;
import utils.RPSLogger;

import java.io.IOException;
import java.util.Optional;

import static org.mockito.Mockito.mock;

public class GameScoreTest {

    @Mock
    private Player humanPlayer;

    @Mock
    private Player computerPlayer;

    @Mock
    private RuleEngine engine;

    @Mock
    private InputOutputStream inputOutputStream;

    private GameScoreRepository gameScoreRepository;

    @Before
    public void setup(){
        humanPlayer = mock(HumanPlayer.class);
        computerPlayer = mock(ComputerPlayer.class);
        engine = mock(RuleEngine.class);
        inputOutputStream = mock(InputOutputStream.class);
        gameScoreRepository = new GameScoreRepository(mock(RPSLogger.class));
    }

    @Test
    public void testDifferentWinAndDrawScenarios() throws InvalidRuleException, IOException {

        //Scenario - 1 : Human wins
        Mockito.when(humanPlayer.getId()).thenReturn("id-1");
        Mockito.when(computerPlayer.getId()).thenReturn("id-2");
        Mockito.when(humanPlayer.getInput()).thenReturn(Optional.of(GameInputObject.PAPER));
        Mockito.when(computerPlayer.getInput()).thenReturn(Optional.of(GameInputObject.ROCK));


        Mockito.when(engine.get(Mockito.eq(GameInputObject.PAPER))).thenReturn((arg) -> true);

        RPSGameService gameService = createNewRPSGame();
        gameService.play(inputOutputStream);

        Assert.assertEquals(1, gameScoreRepository.getScore(humanPlayer.getId()).get().getPlayerScore());
        Assert.assertEquals(0, gameScoreRepository.getScore(humanPlayer.getId()).get().getComputerScore());
        Assert.assertEquals(0, gameScoreRepository.getScore(humanPlayer.getId()).get().getDraws());


        // Human again wins.
        Mockito.when(humanPlayer.getInput()).thenReturn(Optional.of(GameInputObject.PAPER));
        Mockito.when(computerPlayer.getInput()).thenReturn(Optional.of(GameInputObject.ROCK));

        gameService.play(inputOutputStream);

        Assert.assertEquals(2, gameScoreRepository.getScore(humanPlayer.getId()).get().getPlayerScore());
        Assert.assertEquals(0, gameScoreRepository.getScore(humanPlayer.getId()).get().getComputerScore());
        Assert.assertEquals(0, gameScoreRepository.getScore(humanPlayer.getId()).get().getDraws());

        //Computer wins

        Mockito.when(humanPlayer.getInput()).thenReturn(Optional.of(GameInputObject.PAPER));
        Mockito.when(computerPlayer.getInput()).thenReturn(Optional.of(GameInputObject.SCISSORS));

        Mockito.when(engine.get(Mockito.eq(GameInputObject.PAPER))).thenReturn((arg) -> false);
        Mockito.when(engine.get(Mockito.eq(GameInputObject.SCISSORS))).thenReturn((arg) -> true);

        gameService.play(inputOutputStream);
        Assert.assertEquals(2, gameScoreRepository.getScore(humanPlayer.getId()).get().getPlayerScore());
        Assert.assertEquals(1, gameScoreRepository.getScore(humanPlayer.getId()).get().getComputerScore());
        Assert.assertEquals(0, gameScoreRepository.getScore(humanPlayer.getId()).get().getDraws());

        //Draw
        Mockito.when(humanPlayer.getInput()).thenReturn(Optional.of(GameInputObject.SCISSORS));
        Mockito.when(computerPlayer.getInput()).thenReturn(Optional.of(GameInputObject.SCISSORS));

        Mockito.when(engine.get(Mockito.eq(GameInputObject.SCISSORS))).thenReturn((arg) -> false);

        gameService.play(inputOutputStream);
        Assert.assertEquals(2, gameScoreRepository.getScore(humanPlayer.getId()).get().getPlayerScore());
        Assert.assertEquals(1, gameScoreRepository.getScore(humanPlayer.getId()).get().getComputerScore());
        Assert.assertEquals(1, gameScoreRepository.getScore(humanPlayer.getId()).get().getDraws());

    }

    @Test
    public void testInvalidKeyForScoreMap() throws InvalidRuleException, IOException {
        Mockito.when(humanPlayer.getId()).thenReturn("id-1");
        Mockito.when(computerPlayer.getId()).thenReturn("id-2");
        Mockito.when(humanPlayer.getInput()).thenReturn(Optional.of(GameInputObject.PAPER));
        Mockito.when(computerPlayer.getInput()).thenReturn(Optional.of(GameInputObject.ROCK));


        Mockito.when(engine.get(Mockito.eq(GameInputObject.PAPER))).thenReturn((arg) -> true);

        RPSGameService gameService = createNewRPSGame();
        gameService.play(inputOutputStream);
        Assert.assertEquals(Optional.empty(), gameScoreRepository.getScore("id-5"));
    }

    private RPSGameService createNewRPSGame() {
        return new RpsGameServiceImpl(humanPlayer, computerPlayer, engine, gameScoreRepository);
    }

}
