package rockpaperscissors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GameService {
    final Scanner scanner = new Scanner(System.in);

    public String startGame() {
        System.out.println("Enter your name: ");
        final String userName = scanner.nextLine();
        System.out.printf("Hello, %s%n", userName);
        return userName;
    }

    public List<String> getGameMoves() {
        String userMovesProposition = scanner.nextLine(); // usersMoveProposition must have at least 3 words, number of words must be odd
        if (userMovesProposition == null || userMovesProposition.isEmpty())
            return getGameMovesWhenNoUserProposition();
        List<String> usersMoves = Arrays.asList(userMovesProposition.split(","));
        int movesNumber = usersMoves.size();
        while (movesNumber < 3 || movesNumber % 2 == 0) {
            System.out.println("Wrong moves proposition, try again!");
            userMovesProposition = scanner.nextLine();
            if (userMovesProposition == null || userMovesProposition.isEmpty())
                return getGameMovesWhenNoUserProposition();
            usersMoves = Arrays.asList(userMovesProposition.split(","));
            movesNumber = usersMoves.size();
        }
        System.out.println("Okay, let's start");
        return usersMoves;
    }

    public String getUserOption() {
        return scanner.nextLine();
    }

    public int determineUserRating(List<String> ratingFileContent, String userName) {
        for (String line : ratingFileContent) {
            if (line.startsWith(userName + " ")) {
                return Integer.parseInt(line.substring(userName.length() + 1));
            }
        }
        return 0;
    }

    public void processGame(List<String> gameMoves, String userOption, int counter) {
        while (!Option.isGameToBeEnded(userOption)) {
            if (gameMoves.contains(userOption)) {
                final MoveOptionResults moveOptionResults = specifyUserLoseMoves(gameMoves, userOption);
                final String computerOption = getComputerOption();
                final GameResult gameResult = moveOptionResults.checkGameResultForUser(computerOption);
                presentGameScore(gameResult, computerOption);
                counter = updateCounter(gameResult, counter);
            } else if (Option.isRatingToBePresented(userOption)) {
                presentRating(counter);
            } else {
                System.out.println("Invalid input");
            }
            userOption = getUserOption();
        }
    }

    public void endGame() {
        System.out.println("Bye!");
    }

    private MoveOptionResults specifyUserLoseMoves(List<String> gameMoves, String move) {
        List<String> movesWithoutThisMove = new ArrayList<>();
        List<String> loseMoves = new ArrayList<>();
        List<String> movesEnd = gameMoves.stream().dropWhile(gameMove -> !move.equals(gameMove))
                .filter(gameMove -> !move.equals(gameMove))
                .toList();
        List<String> movesBeginning = gameMoves.stream().takeWhile(gameMove -> !move.equals(gameMove)).toList();
        movesWithoutThisMove.addAll(movesEnd);
        movesWithoutThisMove.addAll(movesBeginning);
        int allNum = movesWithoutThisMove.size();
        int half = allNum / 2;
        for (int i = 0; i < allNum; i++) {
            if (i < half)
                loseMoves.add(movesWithoutThisMove.get(i));
        }
        return new MoveOptionResults(move, loseMoves);
    }

    private String getComputerOption() {
        int movesNumber = Main.gameMoves.size();
        final int random = (int) (Math.floor(Math.random() * 10)) % movesNumber;
        return Main.gameMoves.get(random);
    }

    private void presentGameScore(GameResult gameResult, String computerOption) {
        switch (gameResult) {
            case WIN -> System.out.printf("Well done. The computer chose %s and failed%n", computerOption);
            case DRAW -> System.out.printf("There is a draw (%s)%n", computerOption);
            case LOSS -> System.out.printf("Sorry, but the computer chose %s%n", computerOption);
        }
    }

    private void presentRating(int counter) {
        System.out.printf("Your rating: %d%n", counter);
    }

    private int updateCounter(GameResult gameResult, int counter) {
        return switch (gameResult) {
            case LOSS -> counter;
            case DRAW -> counter + 50;
            case WIN -> counter + 100;
        };
    }

    private List<String> getGameMovesWhenNoUserProposition() {
        System.out.println("Okay, let's start");
        return Arrays.asList("scissors", "rock", "paper");
    }
}
