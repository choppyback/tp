package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's training goal in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTrainingGoal(String)}
 */
public class TrainingGoal {

    public static final String MESSAGE_CONSTRAINTS =
            "Training Goal should not be blank and should not contain flag-like patterns (e.g. 'x/', 'ab/').";

    /*
     * The first character of the trainingGoal must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     * Additionally, patterns of 1-2 letters followed by '/' (e.g. 'x/', 'ab/')
     * are not allowed as they resemble flags.
     */
    public static final String VALIDATION_REGEX = "(?!.*\\b[a-zA-Z]{1,2}/)[^\\s].*";

    public static final String DEFAULT_TRAINING_GOAL = "Get a 6 pack";

    public final String value;

    /**
     * Constructs an {@code training goal}.
     *
     * @param trainingGoal A valid trainingGoal.
     */
    public TrainingGoal(String trainingGoal) {
        requireNonNull(trainingGoal);
        checkArgument(isValidTrainingGoal(trainingGoal), MESSAGE_CONSTRAINTS);
        value = trainingGoal;
    }

    /**
     * Returns true if a given string is a valid training goal.
     */
    public static boolean isValidTrainingGoal(String test) {
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
        if (!(other instanceof TrainingGoal)) {
            return false;
        }

        TrainingGoal otherTrainingGoal = (TrainingGoal) other;
        return value.equals(otherTrainingGoal.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
