package rule;

import exceptions.InvalidRuleException;
import model.GameInputObject;
import org.junit.Assert;
import org.junit.Test;
import rules.RuleEngine;
import rules.TwoPlayerRule;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TwoPlayerRuleTest {

    @Test
    public void testValidateAllRulesExists() throws InvalidRuleException {
        RuleEngine ruleEngine = TwoPlayerRule.getInstance();
        for (GameInputObject obj : Arrays.stream(GameInputObject.values()).collect(Collectors.toList()).subList(0, 3)) {
            Assert.assertNotNull(ruleEngine.get(obj));
        }
    }

    @Test
    public void testShouldValidateCorrectRpsRules() throws InvalidRuleException {
        RuleEngine ruleEngine = TwoPlayerRule.getInstance();

        Assert.assertTrue(ruleEngine.get(GameInputObject.PAPER).beats(GameInputObject.ROCK));
        Assert.assertFalse(ruleEngine.get(GameInputObject.PAPER).beats(GameInputObject.SCISSORS));
        Assert.assertFalse(ruleEngine.get(GameInputObject.PAPER).beats(GameInputObject.PAPER));

        Assert.assertTrue(ruleEngine.get(GameInputObject.ROCK).beats(GameInputObject.SCISSORS));
        Assert.assertFalse(ruleEngine.get(GameInputObject.ROCK).beats(GameInputObject.PAPER));
        Assert.assertFalse(ruleEngine.get(GameInputObject.ROCK).beats(GameInputObject.ROCK));


        Assert.assertTrue(ruleEngine.get(GameInputObject.SCISSORS).beats(GameInputObject.PAPER));
        Assert.assertFalse(ruleEngine.get(GameInputObject.SCISSORS).beats(GameInputObject.ROCK));
        Assert.assertFalse(ruleEngine.get(GameInputObject.SCISSORS).beats(GameInputObject.SCISSORS));
    }

}
