package rockpaperscissors;

import java.util.Map;

import static rockpaperscissors.GameResult.*;
import static rockpaperscissors.Option.*;

public class Main {
    static final Map<String, GameResult> gameMap = Map.of(
            new UsersMove(PAPER, PAPER).toString(), DRAW,
            new UsersMove(SCISSORS, SCISSORS).toString(), DRAW,
            new UsersMove(ROCK, ROCK).toString(), DRAW,
            new UsersMove(PAPER, ROCK).toString(), WIN,
            new UsersMove(ROCK, SCISSORS).toString(), WIN,
            new UsersMove(SCISSORS, PAPER).toString(), WIN,
            new UsersMove(ROCK, PAPER).toString(), LOSS,
            new UsersMove(PAPER, SCISSORS).toString(), LOSS,
            new UsersMove(SCISSORS, ROCK).toString(), LOSS
    );
    private static final GameService gameService = new GameService();

    public static void main(String[] args) {

        String userOption = gameService.getUserOption();

        while (!Option.isGameToBeEnded(userOption)) {
            if (Option.isCorrectOption(userOption)) {
                final String computerOption = gameService.getComputerOption();
                if ("error".equals(computerOption))
                    throw new WrongOptionException("Wrong computer choice, error!");
                final UsersMove usersMove = new UsersMove(
                        Option.valueOf(userOption.toUpperCase()),
                        Option.valueOf(computerOption.toUpperCase())
                );
                final GameResult gameResult = gameMap.get(usersMove.toString());
                gameService.presentGameScore(gameResult, usersMove);
            } else {
                System.out.println("Invalid input");
            }
            userOption = gameService.getUserOption();
        }
        gameService.endGame();
    }
}
