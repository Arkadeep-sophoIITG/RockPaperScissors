package model;

public class Score {

    private final int computerScore;
    private final int playerScore;
    private final int draws;

    /**
     *
     * @param computerScore   Running score of computer
     * @param playerScore     Running score of humanPlayer
     * @param draws           Running number of draws
     */

    public Score(int computerScore, int playerScore, int draws) {
        this.computerScore = computerScore;
        this.playerScore = playerScore;
        this.draws = draws;
    }

    public int getComputerScore() {
        return computerScore;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public int getDraws() {
        return draws;
    }

    @Override
    public String toString() {
        return "Score{" +
                "computerScore=" + computerScore +
                ", playerScore=" + playerScore +
                ", draws=" + draws +
                '}';
    }
}
