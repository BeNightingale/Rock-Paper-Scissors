package rockpaperscissors;

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

    public String getUserOption() {
        return scanner.nextLine();
    }

    public String getComputerOption() {
        final int random = (int) (Math.floor(Math.random() * 10)) % 3;
        return switch (random) {
            case 0 -> "paper";
            case 1 -> "scissors";
            case 2 -> "rock";
            default -> "error";
        };
    }

    public void presentGameScore(GameResult gameResult, UsersMove usersMove) {
        switch (gameResult) {
            case WIN ->
                    System.out.printf("Well done. The computer chose %s and failed%n", usersMove.computerChoice().getVal());
            case DRAW -> System.out.printf("There is a draw (%s)%n", usersMove.computerChoice().getVal());
            case LOSS -> System.out.printf("Sorry, but the computer chose %s%n", usersMove.computerChoice().getVal());
        }
    }

    public void presentRating(int counter) {
        System.out.printf("Your rating: %d%n", counter);
    }

    public int determineUserRating(List<String> ratingFileContent, String userName) {
        for (String line : ratingFileContent) {
            if (line.startsWith(userName + " ")) {
                return Integer.parseInt(line.substring(userName.length() + 1));
            }
        }
        return 0;
    }

    public int updateCounter(GameResult gameResult, int counter) {
        return switch (gameResult) {
            case LOSS -> counter;
            case DRAW -> counter + 50;
            case WIN -> counter + 100;
        };
    }

    public void endGame() {
        System.out.println("Bye!");
    }
}
