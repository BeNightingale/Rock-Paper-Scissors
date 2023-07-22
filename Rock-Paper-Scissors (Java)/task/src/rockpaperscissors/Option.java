package rockpaperscissors;

import java.util.Objects;

public enum Option {
    SCISSORS("scissors"),
    PAPER("paper"),
    ROCK("rock");
    private final String val;
    Option(String val) {
        this.val = val;
    }
    public String getVal() {
        return val;
    }

    public static boolean isOption(String choice) {
        if (choice == null || choice.isEmpty())
            return false;
        for (Option option : Option.values()) {
            if (Objects.equals(choice, option.getVal()))
                return true;
        }
        return false;
    }
}
