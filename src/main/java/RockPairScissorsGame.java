import exceptions.InvalidPlayerNameException;
import exceptions.InvalidRuleException;
import ioModel.InputOutputStream;
import model.ComputerPlayer;
import model.HumanPlayer;
import model.Player;
import repository.GameScoreRepository;
import rules.TwoPlayerRule;
import service.RPSGameService;
import service.RpsGameServiceImpl;
import utils.RPSLogger;

import java.io.IOException;

public class RockPairScissorsGame {

    public static void beginGame() throws IOException, InvalidPlayerNameException, InvalidRuleException {
        InputOutputStream inputOutputStream = new InputOutputStream();
        RPSLogger LOGGER = new RPSLogger();

        inputOutputStream.printNewLine("Welcome to Rock Paper Scissors Game!");
        inputOutputStream.printNewLine("");

        final GameScoreRepository gameScoreRepository = new GameScoreRepository(LOGGER);
        Player humanPlayer = HumanPlayer.buildPlayer(inputOutputStream, LOGGER);
        Player computerPlayer = ComputerPlayer.buildPlayer("Deep Blue", LOGGER);

        RPSGameService rpsGameService = new RpsGameServiceImpl(humanPlayer, computerPlayer, TwoPlayerRule.getInstance(),
                gameScoreRepository);

        int gameNumber = 1;
        while (true){
            inputOutputStream.printNewLine("");
            inputOutputStream.printNewLine("Game : " + gameNumber + " begins!");
            rpsGameService.play(inputOutputStream);
            ++gameNumber;
        }


    }

}
