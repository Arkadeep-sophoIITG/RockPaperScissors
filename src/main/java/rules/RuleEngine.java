package rules;

import exceptions.InvalidRuleException;
import model.GameInputObject;

public interface RuleEngine {

    /**
     *
     * @param object                  Input of the player
     * @return                        Winning predicate according to the rules of two player RPS game
     * @throws InvalidRuleException   Throws exception when input is invalid and no rule exists for the input
     */

    public WinningPredicate get(GameInputObject object) throws InvalidRuleException;

}
