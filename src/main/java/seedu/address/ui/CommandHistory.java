package seedu.address.ui;

import static java.lang.Integer.max;
import static java.lang.Integer.min;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores and manages a history of commands entered by the user during the current session.
 * <p>
 * This class provides navigation functionality (up and down) to allow users to scroll
 * through previous commands, similar to a terminal or command-line interface.
 * It maintains an internal pointer to track the user's current position within
 * the history list.
 */
public class CommandHistory {
    private final List<String> commandHistory = new ArrayList<String>();
    private int current;

    /**
     * Constructs a new {@code CommandHistory} with an empty history list
     * and resets the navigation pointer.
     */
    public CommandHistory() {
        this.current = 0;
    }

    /**
     * Navigates to the previous command in the history.
     * <p>
     * If the history is empty, an empty string is returned. If the pointer is already
     * at the earliest command, that command is returned without further decrementing the pointer.
     *
     * @return The previous command string in the history.
     */
    public String navigateUp() {
        if (commandHistory.isEmpty()) {
            return "";
        }
        if (current <= 0) {
            return commandHistory.get(0);
        }
        current = max(0, current - 1);
        return commandHistory.get(current);
    }

    /**
     * Navigates to the next command in the history.
     * <p>
     * If the history is empty or the pointer is already at the end of the history,
     * an empty string is returned. Otherwise, the pointer moves forward and returns
     * the next command.
     *
     * @return The next command string in the history.
     */
    public String navigateDown() {
        int historySize = commandHistory.size();

        // Increment pointer, but cap it at the "blank line" index (historySize)
        current = min(historySize, current + 1);

        // If we've reached the blank line index, return empty string
        if (current == historySize) {
            return "";
        }

        return commandHistory.get(current);
    }

    /**
     * Adds a command to the history and resets the navigation pointer to the
     * end of the list.
     *
     * @param command The command string to be added to the history.
     */
    public void addCommand(String command) {
        commandHistory.add(command);
        current = commandHistory.size();
    }
}
