import exceptions.InvalidPlayerNameException;
import exceptions.InvalidRuleException;

import java.io.IOException;

public class AppMain {

    public static void main(String[] args) throws IOException, InvalidPlayerNameException, InvalidRuleException {
        RockPairScissorsGame.beginGame();
    }

}
