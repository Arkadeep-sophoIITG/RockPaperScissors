package rules;

import model.GameInputObject;

import java.util.function.Predicate;

public interface WinningPredicate extends Predicate<GameInputObject> {

    public default boolean beats(GameInputObject object) {
        return test(object);
    }
}
