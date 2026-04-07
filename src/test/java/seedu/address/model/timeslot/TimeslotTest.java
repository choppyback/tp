package seedu.address.model.timeslot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TimeslotTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Timeslot(null));
    }

    @Test
    public void constructor_invalidTimeslot_throwsIllegalArgumentException() {
        String invalidTimeslot = "mon:0900-1010";
        assertThrows(IllegalArgumentException.class, () -> new Timeslot(invalidTimeslot));
    }

    @Test
    public void isValidTimeslot() {
        // null timeslot
        assertThrows(NullPointerException.class, () -> Timeslot.isValidTimeslot(null));

        // EP: empty String or Slots
        assertFalse(Timeslot.isValidTimeslot(""));
        assertFalse(Timeslot.isValidTimeslot(" "));
        assertFalse(Timeslot.isValidTimeslot("-")); // one character
        assertFalse(Timeslot.isValidTimeslot("sun:")); // no slot chosen
        assertFalse(Timeslot.isValidTimeslot("1800-1900")); // no day chosen

        // EP: spaces in String input
        assertFalse(Timeslot.isValidTimeslot("mon: 1900-2000"));
        assertFalse(Timeslot.isValidTimeslot("mon:1900- 2000"));
        assertFalse(Timeslot.isValidTimeslot("mon: 1900- 2000"));
        assertFalse(Timeslot.isValidTimeslot("mon: 1,2"));
        assertFalse(Timeslot.isValidTimeslot("mon:1, 2"));
        assertFalse(Timeslot.isValidTimeslot("mon: 1, 2"));

        // EP: invalid day input
        assertFalse(Timeslot.isValidTimeslot("monday:1900-2000")); // day spelt in full
        assertFalse(Timeslot.isValidTimeslot("mom:1900-2000")); // misspell
        assertFalse(Timeslot.isValidTimeslot("wef:1900-2000")); // misspell
        assertFalse(Timeslot.isValidTimeslot("sum:1900-2000")); // misspell

        // EP: invalid HHMM times
        assertFalse(Timeslot.isValidTimeslot("mon:2490-1680")); // invalid time
        assertFalse(Timeslot.isValidTimeslot("mon:1900-1500")); // start time later than end time
        assertFalse(Timeslot.isValidTimeslot("mon:1800-1800")); // same start and end time
        assertFalse(Timeslot.isValidTimeslot("mon:1800-2000")); // timings longer than an hour
        assertFalse(Timeslot.isValidTimeslot("mon:1830-1930")); // MM field not 00
        assertFalse(Timeslot.isValidTimeslot("mon:1800-1859")); // MM field not 00
        assertFalse(Timeslot.isValidTimeslot("mon:1800-1900,11")); // duplicate times

        // BVA: Slots from 0800 to 2000
        assertTrue(Timeslot.isValidTimeslot("mon:0800-0900"));
        assertFalse(Timeslot.isValidTimeslot("mon:0700-0800"));
        assertTrue(Timeslot.isValidTimeslot("mon:1900-2000"));
        assertFalse(Timeslot.isValidTimeslot("mon:2000-2100"));

        // BVA: Slots from 1 to 12
        assertFalse(Timeslot.isValidTimeslot("mon:-1")); // invalid slot
        assertFalse(Timeslot.isValidTimeslot("mon:0")); // invalid slot
        assertFalse(Timeslot.isValidTimeslot("mon:13")); // invalid slot

        // EP: Duplicates
        assertFalse(Timeslot.isValidTimeslot("sun:0800-0900,0800-0900")); // duplicate slots
        assertFalse(Timeslot.isValidTimeslot("mon:2,2")); // duplicate slots

        // valid timeslot
        assertTrue(Timeslot.isValidTimeslot("mon:1900-2000")); // day in lower case
        assertTrue(Timeslot.isValidTimeslot("MON:1900-2000")); // day in upper case
        assertTrue(Timeslot.isValidTimeslot("Mon:1900-2000")); // day in snake case
        assertTrue(Timeslot.isValidTimeslot("sun:1")); // single slot
        assertTrue(Timeslot.isValidTimeslot("sun:1,2,3,4,5,6,7,8,9,10,11,12")); // multiple slots
    }

    @Test
    public void equals() {
        Timeslot timeslot1 = new Timeslot("mon:0800-0900");
        Timeslot timeslot2 = new Timeslot("mon:2,4");

        // same values -> returns true
        assertTrue(timeslot1.equals(new Timeslot("mon:0800-0900")));
        assertTrue(timeslot1.equals(new Timeslot("mon:1")));
        assertTrue(timeslot2.equals(new Timeslot("mon:0900-1000,1100-1200")));
        assertTrue(timeslot2.equals(new Timeslot("mon:2,4")));

        // same object -> returns true
        assertTrue(timeslot1.equals(timeslot1));
        assertTrue(timeslot2.equals(timeslot2));

        // null -> returns false
        assertFalse(timeslot1.equals(null));
        assertFalse(timeslot2.equals(null));

        // different types -> returns false
        assertFalse(timeslot1.equals(5.0f));
        assertFalse(timeslot2.equals(5.0f));

        // different values -> returns false
        assertFalse(timeslot1.equals(new Timeslot("tue:0900-1000")));
        assertFalse(timeslot1.equals(new Timeslot("mon:2,4,6")));
    }

    @Test
    public void toStringTest() {
        Timeslot timeslot1 = new Timeslot("mon:1,2");
        Timeslot timeslot2 = new Timeslot("mon:0800-0900,0900-1000");
        String expected = "MON: 0800-0900, 0900-1000";
        assertEquals(expected, timeslot1.toString());
        assertEquals(expected, timeslot2.toString());
    }
}
