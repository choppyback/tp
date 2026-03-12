package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's progress record in the address book.
 */
public class ProgressRecord {

    public static final String MESSAGE_CONSTRAINTS = "Progress Records should be with 0% to 100%";
    public static final String DEFAULT_PROGRESS = "0%";
    /*
     * Validates that data is a percentage.
     * Example: 5%, 0.001%, 100%
     */
    public static final String VALIDATION_REGEX = "^((100((\\.|,)[0-9]{1,2})?)|([0-9]{1,2}((\\.|,)[0-9]{0,2})?))%$";

    public final String value;
    /**
     * Constructs a {@code ProgressRecord}.
     *
     * @param progress A progress.
     */
    public ProgressRecord(String progress) {
        requireNonNull(progress);
        checkArgument(isValidProgress(progress), MESSAGE_CONSTRAINTS);
        value = progress;
    }

    /**
     * Returns true if a given string is a valid progress.
     */
    public static boolean isValidProgress(String test) {
        return test.matches(VALIDATION_REGEX);
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

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
