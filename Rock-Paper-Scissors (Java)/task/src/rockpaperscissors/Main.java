package rockpaperscissors;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String userOption = scanner.nextLine();
        final String computerOption = switch (userOption) {
            case "rock" -> "paper";
            case "paper" -> "scissors";
            case "scissors" -> "rock";
            default -> "error";

        };
        System.out.println(
                !"error".equals(computerOption) ?
                        String.format("Sorry, but the computer chose %s", computerOption) :
                        "unacceptable user's option; error"
        );
    }
}
