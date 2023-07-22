package rockpaperscissors;

import java.util.Map;
import java.util.Scanner;

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
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String userOption = scanner.nextLine();
        if (!Option.isOption(userOption))
            throw new WrongOptionException("Wrong user choice, error!");
        final int random = (int)(Math.floor(Math.random() * 10)) % 3;
        final String computerOption = switch (random) {
            case 0 -> "paper";
            case 1 -> "scissors";
            case 2 -> "rock";
            default -> "error";
        };
        if ("error".equals(computerOption))
            throw new WrongOptionException("Wrong computer choice, error!");
        final UsersMove usersMove = new UsersMove(
                Option.valueOf(userOption.toUpperCase()),
                Option.valueOf(computerOption.toUpperCase())
        );
        final GameResult gameResult = gameMap.get(usersMove.toString());
        switch (gameResult) {
            case WIN -> System.out.printf("Well done. The computer chose %s and failed", usersMove.computerChoice().getVal());
            case DRAW -> System.out.printf("There is a draw (%s)", usersMove.computerChoice().getVal());
            case LOSS -> System.out.printf("Sorry, but the computer chose %s", usersMove.computerChoice().getVal());
        }
    }
}
