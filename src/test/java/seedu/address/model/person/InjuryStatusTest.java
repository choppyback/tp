package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class InjuryStatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new InjuryStatus(null));
    }

    @Test
    public void constructor_invalidInjuryStatus_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new InjuryStatus(""));
        assertThrows(IllegalArgumentException.class, () -> new InjuryStatus("   "));
        assertThrows(IllegalArgumentException.class, () -> new InjuryStatus("x/something"));
        assertThrows(IllegalArgumentException.class, () -> new InjuryStatus("ab/something"));
        assertThrows(IllegalArgumentException.class, () -> new InjuryStatus("sprained ankle x/severe"));
    }

    @Test
    public void isValidInjuryStatus() {
        // null
        assertThrows(NullPointerException.class, () -> InjuryStatus.isValidInjuryStatus(null));

        // invalid injury statuses
        assertFalse(InjuryStatus.isValidInjuryStatus("")); // empty string
        assertFalse(InjuryStatus.isValidInjuryStatus(" ")); // spaces only
        assertFalse(InjuryStatus.isValidInjuryStatus("x/something")); // single letter flag-like pattern
        assertFalse(InjuryStatus.isValidInjuryStatus("ab/something")); // two letter flag-like pattern
        assertFalse(InjuryStatus.isValidInjuryStatus("sprained ankle x/severe")); // flag-like pattern in middle

        // valid injury statuses
        assertTrue(InjuryStatus.isValidInjuryStatus("none")); // typical status
        assertTrue(InjuryStatus.isValidInjuryStatus("sprained ankle")); // multiple words
        assertTrue(InjuryStatus.isValidInjuryStatus("sprained ankle, torn ACL")); // with comma
        assertTrue(InjuryStatus.isValidInjuryStatus("stress-fracture")); // with hyphen
        assertTrue(InjuryStatus.isValidInjuryStatus("shoulder dislocation (left)")); // with parentheses
        assertTrue(InjuryStatus.isValidInjuryStatus("runner's knee")); // with apostrophe
        assertTrue(InjuryStatus.isValidInjuryStatus("sprain/strain")); // three+ letters before slash allowed
        assertTrue(InjuryStatus.isValidInjuryStatus("Unknown")); // default value
    }

    @Test
    public void equals() {
        InjuryStatus injury = new InjuryStatus("sprained ankle");

        // same values -> returns true
        assertTrue(injury.equals(new InjuryStatus("sprained ankle")));

        // same object -> returns true
        assertTrue(injury.equals(injury));

        // null -> returns false
        assertFalse(injury.equals(null));

        // different types -> returns false
        assertFalse(injury.equals(5.0f));

        // different values -> returns false
        assertFalse(injury.equals(new InjuryStatus("torn ACL")));
    }

    @Test
    public void hashCode_sameValue_returnsSameHashCode() {
        assertEquals(new InjuryStatus("sprained ankle").hashCode(),
                new InjuryStatus("sprained ankle").hashCode());
    }

    @Test
    public void hashCode_differentValue_returnsDifferentHashCode() {
        assertNotEquals(new InjuryStatus("sprained ankle").hashCode(),
                new InjuryStatus("torn ACL").hashCode());
    }

    @Test
    public void toString_returnsValue() {
        assertEquals("sprained ankle", new InjuryStatus("sprained ankle").toString());
    }
}
