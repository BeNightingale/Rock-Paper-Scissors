package rockpaperscissors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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

    public static void main(String[] args) throws IOException {

        //final Path path = Paths.get("rating.txt");// this path is for Academy tests - file will be created when tests are running -  in this case no file I should create
        final Path path = Paths.get("./Rock-Paper-Scissors (Java)/task/src/rockpaperscissors/scores.txt"); // added my file for my tests, if game is running correctly and scores are processed correctly
        final List<String> content = Files.readAllLines(path);
        System.out.println(content);
        final String userName = gameService.startGame();

        String userOption = gameService.getUserOption();
        int counter = gameService.determineUserRating(content, userName);

        while (!Option.isGameToBeEnded(userOption)) {
            if (Option.isCompetitionOption(userOption)) {
                final String computerOption = gameService.getComputerOption();
                if ("error".equals(computerOption))
                    throw new WrongOptionException("Wrong computer choice, error!");
                final UsersMove usersMove = new UsersMove(
                        Option.valueOf(userOption.toUpperCase()),
                        Option.valueOf(computerOption.toUpperCase())
                );
                final GameResult gameResult = gameMap.get(usersMove.toString());
                gameService.presentGameScore(gameResult, usersMove);
                counter = gameService.updateCounter(gameResult, counter);
            } else if (Option.isRatingToBePresented(userOption)) {
                gameService.presentRating(counter);
            } else {
                System.out.println("Invalid input");
            }
            userOption = gameService.getUserOption();
        }
        gameService.endGame();
    }
}
