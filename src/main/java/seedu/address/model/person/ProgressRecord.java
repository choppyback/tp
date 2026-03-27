package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's progress record in the address book.
 */
public class ProgressRecord implements Comparable<ProgressRecord> {

    public static final String MESSAGE_CONSTRAINTS = "Progress Records should be with 0% to 100%";
    public static final String DEFAULT_PROGRESS = "0%";
    /**
     * Validates that data is a percentage.
     * Example: 5%, 0%, 100%
     */
    public static final String VALIDATION_REGEX = "^(100|[1-9]?[0-9])%$";

    public final String value;
    /**
     * Constructs a {@code ProgressRecord}.
     *
     * @param progress A progress.
     */
    public ProgressRecord(String progress) {
        requireNonNull(progress);
        checkArgument(isValidProgress(progress), MESSAGE_CONSTRAINTS);
        int numericValue = parseToPercentage(progress);
        assert numericValue >= 0 && numericValue <= 100;
        value = progress;
    }

    /**
     * Returns true if a given string is a valid progress.
     */
    public static boolean isValidProgress(String test) {
        requireNonNull(test);
        // 1. Check if it matches the general format first
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Helper to convert "50%"  into a integer percentage value.
     */
    static int parseToPercentage(String s) {
        return Integer.parseInt(s.replace("%", ""));
    }
    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ProgressRecord)) {
            return false;
        }

        ProgressRecord otherProgress = (ProgressRecord) other;
        return value.equals(otherProgress.value);
    }

    /**
     * Compares this ProgressRecord to another ProgressRecord to determine their relative order.
     * The comparison is based on the numeric value of the progress percentage, with higher values considered greater.
     *
     * @param other The other ProgressRecord to compare with.
     * @return A negative integer, zero, or a positive integer if this ProgressRecord is less than,
     *         equal to, or greater than the specified ProgressRecord, respectively.
     */
    @Override
    public int compareTo(ProgressRecord other) {
        requireNonNull(other);
        if (other == this) {
            return 0;
        }
        ProgressRecord otherProgress = (ProgressRecord) other;
        int thisValue = parseToPercentage(value);
        int otherValue = parseToPercentage(otherProgress.value);
        if (thisValue < otherValue) {
            return -1;
        } else if (thisValue > otherValue) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
