package repository;

import model.Score;
import utils.RPSLogger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GameScoreRepository {

    public Map<String, Score> scoreMap;
    public RPSLogger logger;

    public GameScoreRepository(RPSLogger logger) {
        scoreMap = new HashMap<>();
        this.logger = logger;
    }

    /**
     *
     * @param id      Unique id of the human player
     * @param score   Immutable score object of the current/ongoing game
     */

    public void addScore(String id, Score score) {
        scoreMap.putIfAbsent(id, new Score(0, 0, 0));
        Score prevScore = scoreMap.get(id);
        scoreMap.put(id, new Score(prevScore.getComputerScore() + score.getComputerScore(),
                prevScore.getPlayerScore() + score.getPlayerScore(), prevScore.getDraws() + score.getDraws()));
    }

    /**
     *
     * @param id   Unique id of the human player
     * @return     Returns current score of the game.
     */
    public Optional<Score> getScore(String id) {
        try {
            return Optional.of(scoreMap.get(id));
        } catch (Exception e) {
            logger.logSevereError("Player with id : " + id + " does not exist in database", e);
        }
        return Optional.empty();
    }


}
