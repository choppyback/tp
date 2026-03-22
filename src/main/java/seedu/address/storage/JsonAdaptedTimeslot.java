package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.timeslot.Timeslot;

/**
 * Jackson-friendly version of {@link Timeslot}.
 */
public class JsonAdaptedTimeslot {

    private final String timeslot;

    /**
     * Constructs a {@code JsonAdaptedTimeslot} with the given {@code timeslot}.
     */
    @JsonCreator
    public JsonAdaptedTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }

    /**
     * Converts a given {@code Timeslot} into this class for Jackson use.
     */
    public JsonAdaptedTimeslot(Timeslot source) {
        timeslot = source.toStorageString();
    }

    @JsonValue
    public String getTimeslot() {
        return timeslot;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Timeslot} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted timeslot.
     */
    public Timeslot toModelType() throws IllegalValueException {
        if (!Timeslot.isValidTimeslot(timeslot)) {
            throw new IllegalValueException(Timeslot.MESSAGE_CONSTRAINTS);
        }
        return new Timeslot(timeslot);
    }
}
