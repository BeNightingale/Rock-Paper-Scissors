package rockpaperscissors;

import java.util.List;
import java.util.Objects;

public class MoveOptionResults {
    private final String move;
    private final List<String> loseMoves;

    public MoveOptionResults(String move, List<String> loseMoves) {
        this.move = move;
        this.loseMoves = loseMoves;
    }

    public GameResult checkGameResultForUser(String computerMove) {
        if (computerMove == null || computerMove.isEmpty()) {
            throw new WrongOptionException("Computer move is not in possible move range! Error!");
        }
        if (Objects.equals(move, computerMove))
            return GameResult.DRAW;
        return loseMoves.contains(computerMove) ? GameResult.LOSS : GameResult.WIN;
    }
}
