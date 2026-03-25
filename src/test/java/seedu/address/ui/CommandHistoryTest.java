package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandHistoryTest {



    @Test
    public void addToHistory() {
        CommandHistory commandHistory = new CommandHistory();
        commandHistory.addCommand("edit 1 n/name");
        assertTrue(commandHistory.navigateUp().equals("edit 1 n/name"));
    }

    @Test
    public void navigateUpHistory() {
        CommandHistory commandHistory = new CommandHistory();
        commandHistory.addCommand("edit 1 n/name");
        commandHistory.addCommand("edit 2 n/name");
        assertTrue(commandHistory.navigateUp().equals("edit 2 n/name"));
        assertTrue(commandHistory.navigateUp().equals("edit 1 n/name"));
    }

    @Test
    public void navigateDownHistory() {
        CommandHistory commandHistory = new CommandHistory();
        commandHistory.addCommand("edit 1 n/name");
        commandHistory.addCommand("edit 2 n/name");
        assertTrue(commandHistory.navigateDown().equals(""));
        assertTrue(commandHistory.navigateUp().equals("edit 2 n/name"));
        assertTrue(commandHistory.navigateUp().equals("edit 1 n/name"));
    }


}
