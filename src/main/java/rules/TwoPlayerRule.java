package rules;

import exceptions.InvalidRuleException;
import model.GameInputObject;

import java.util.Map;

public class TwoPlayerRule implements RuleEngine {

    private final Map<GameInputObject, WinningPredicate> winningMap;

    private static final TwoPlayerRule ruleEngine = new TwoPlayerRule();

    public static TwoPlayerRule getInstance() {
        return ruleEngine;
    }

    /**
     *  Builds winningMap containing key as game inputs which will beat the  competitor, which is the value of the map.
     */

    private TwoPlayerRule() {
        this.winningMap = Map.ofEntries(Map.entry(GameInputObject.ROCK, competitor -> competitor == GameInputObject.SCISSORS),
                Map.entry(GameInputObject.PAPER, competitor -> competitor == GameInputObject.ROCK),
                Map.entry(GameInputObject.SCISSORS, competitor -> competitor == GameInputObject.PAPER));
    }

    @Override
    public WinningPredicate get(GameInputObject inputObject) throws InvalidRuleException {

        if (!winningMap.containsKey(inputObject)) {
            throw new InvalidRuleException("Invalid rule for input : " + inputObject.name());
        }
        return winningMap.get(inputObject);
    }


}
