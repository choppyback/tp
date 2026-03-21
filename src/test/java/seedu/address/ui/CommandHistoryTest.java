package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CommandHistoryTest {



    @Test
    public void add_to_command_history() {
        CommandHistory commandHistory = new CommandHistory();
        commandHistory.addCommand("edit 1 n/name");
        assertTrue(commandHistory.navigateUp().equals("edit 1 n/name"));
    }

    @Test
    public void navigate_up_command_history() {
        CommandHistory commandHistory = new CommandHistory();
        commandHistory.addCommand("edit 1 n/name");
        commandHistory.addCommand("edit 2 n/name");
        assertTrue(commandHistory.navigateUp().equals("edit 2 n/name"));
        assertTrue(commandHistory.navigateUp().equals("edit 1 n/name"));
    }

    @Test
    public void navigate_down_command_history() {
        CommandHistory commandHistory = new CommandHistory();
        commandHistory.addCommand("edit 1 n/name");
        commandHistory.addCommand("edit 2 n/name");
        assertTrue(commandHistory.navigateDown().equals(""));
        assertTrue(commandHistory.navigateUp().equals("edit 2 n/name"));
        assertTrue(commandHistory.navigateUp().equals("edit 1 n/name"));
    }


}
