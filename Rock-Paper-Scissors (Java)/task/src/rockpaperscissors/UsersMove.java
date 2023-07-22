package rockpaperscissors;

public record UsersMove(Option userChoice, Option computerChoice) {

    @Override
    public String toString() {
        return "UsersMove{" +
                "userChoice=" + userChoice +
                ", computerChoice=" + computerChoice +
                '}';
    }
}
