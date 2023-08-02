package rockpaperscissors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    // Game works only if there are at least 3 move options and their number is odd.
    static List<String> gameMoves; // gameMoves.size() >= 3 && gameMoves.size() % 2 != 0
    private static final GameService gameService = new GameService();

    public static void main(String[] args) throws IOException {

        final Path path = Paths.get("rating.txt");// this path is for Academy tests - file will be created when tests are running -  in this case no file I should create
        //   final Path path = Paths.get("./Rock-Paper-Scissors (Java)/task/src/rockpaperscissors/scores.txt"); // added my file for my tests, if game is running correctly and scores are processed correctly
        final List<String> content = Files.readAllLines(path);
        final String userName = gameService.startGame();
        gameMoves = gameService.getGameMoves();
        String userOption = gameService.getUserOption();// reading user move
        int counter = gameService.determineUserRating(content, userName);// reading user rating from file
        gameService.processGame(gameMoves, userOption, counter);
        gameService.endGame();
    }
}
