package service;

import exceptions.InvalidRuleException;
import ioModel.InputOutputStream;
import model.GameInputObject;
import model.PlayOutcome;
import model.Player;
import model.Score;
import repository.GameScoreRepository;
import rules.RuleEngine;

import java.io.IOException;
import java.util.Optional;

public class RpsGameServiceImpl implements RPSGameService{

    private final Player firstPlayer;
    private final Player secondPlayer;
    private final RuleEngine ruleEngine;
    private final GameScoreRepository gameScoreRepository;

    public RpsGameServiceImpl(Player firstPlayer, Player secondPlayer, RuleEngine ruleEngine,
                              GameScoreRepository gameScoreRepository) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.ruleEngine = ruleEngine;
        this.gameScoreRepository = gameScoreRepository;
    }

    @Override
    public Optional<Player> play(InputOutputStream inputOutputStream) throws InvalidRuleException, IOException {
        Optional<GameInputObject> firstPlayerInput = firstPlayer.getInput();
        Optional<GameInputObject> secondPlayerInput = secondPlayer.getInput();

        if (firstPlayerInput.isPresent() && secondPlayerInput.isPresent()) {
            GameInputObject firstPlayerInputObject = firstPlayerInput.get();
            GameInputObject secondPlayerInputObject = secondPlayerInput.get();
            if (GameInputObject.EXIT.equals(firstPlayerInputObject) || GameInputObject.EXIT.equals(secondPlayerInputObject)){
                shutDown(inputOutputStream);
            }
            inputOutputStream.printNewLine(firstPlayer.getName() + " plays " + firstPlayerInputObject.name());
            inputOutputStream.printNewLine(secondPlayer.getName() + " plays " + secondPlayerInputObject.name());

            Optional<Player> gameWinner = Optional.empty();


            if (ruleEngine.get(firstPlayerInputObject).beats(secondPlayerInputObject)) {
                gameWinner = Optional.of(firstPlayer);
                gameScoreRepository.addScore(firstPlayer.getId(), new Score(0, 1, 0));
            }
            else if (ruleEngine.get(secondPlayerInputObject).beats(firstPlayerInputObject)){
                gameWinner = Optional.of(secondPlayer);
                gameScoreRepository.addScore(firstPlayer.getId(), new Score(1, 0, 0));
            }

            else {
                gameWinner.ifPresentOrElse(player -> inputOutputStream.printNewLine("Decisive Result : "
                        + player.getName()
                        + PlayOutcome.WIN.name()), () -> inputOutputStream.printNewLine(PlayOutcome.DRAW.name()));
                gameScoreRepository.addScore(firstPlayer.getId(), new Score(0, 0, 1));
            }
            Optional<Score> totalScore = gameScoreRepository.getScore(firstPlayer.getId());
            totalScore.ifPresentOrElse(score -> inputOutputStream.printNewLine(score.toString()),
                    () -> inputOutputStream.printNewLine("Score Card not available"));
            return gameWinner;
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public void shutDown(InputOutputStream inputOutputStream) throws IOException {
        inputOutputStream.printNewLine("Shutting down the RPS application");
        inputOutputStream.closeInputStream();
        inputOutputStream.closeOutputStream();
        System.exit(0);
    }

}
