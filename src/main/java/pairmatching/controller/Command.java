package pairmatching.controller;


public enum Command {
    MATCHING("1"),
    SEARCH("2"),
    RESET("3"),
    QUIT("Q");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static boolean validThreeSelect(int commandNumber) {
        int reset = Integer.parseInt(RESET.value);
        return reset == commandNumber;
    }

    public static boolean commandCompare(String inputCommand) {
        return inputCommand.equalsIgnoreCase("Q");

    }
}
