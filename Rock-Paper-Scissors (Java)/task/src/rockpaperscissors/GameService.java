package rockpaperscissors;

import java.util.Scanner;

public class GameService {
    final Scanner scanner = new Scanner(System.in);

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

    public void endGame() {
        System.out.println("Bye!");
    }
}
