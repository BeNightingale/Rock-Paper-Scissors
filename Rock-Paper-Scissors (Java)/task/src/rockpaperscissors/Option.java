package rockpaperscissors;

import java.util.Objects;

public enum Option {
    SCISSORS("scissors"),
    PAPER("paper"),
    ROCK("rock"),
    EXIT("!exit");
    private final String val;

    Option(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public static boolean isCorrectOption(String choice) {
        if (choice == null || choice.isEmpty())
            return false;
        for (Option option : Option.values()) {
            if (Objects.equals(choice, option.getVal()) && !Objects.equals(choice, Option.EXIT.getVal()))
                return true;
        }
        return false;
    }

    public static boolean isGameToBeEnded(String choice) {
        if (choice == null || choice.isEmpty())
            return false;
        return Objects.equals(choice, Option.EXIT.getVal());
    }
}
