package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROGRESS_RECORD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMESLOT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRAINING_GOAL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRAINING_GOAL_BOB;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonTest {
    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same phone, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTrainingGoal(VALID_TRAINING_GOAL_BOB)
                .withTimeslots(VALID_TIMESLOT_BOB)
                .build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different phone, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // same name, different phone -> returns false
        Person editedBob = new PersonBuilder(BOB)
            .withPhone(VALID_PHONE_AMY).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // different name, same phone -> returns true
        editedBob = new PersonBuilder(BOB)
            .withName(VALID_NAME_AMY).build();
        assertTrue(BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different timeslot -> returns false
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_TIMESLOT_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different training goal -> returns false
        editedAlice = new PersonBuilder(ALICE).withTrainingGoal(VALID_TRAINING_GOAL_AMY).build();
        assertFalse(ALICE.equals(editedAlice));

        // different progressRecord -> returns false
        editedAlice = new PersonBuilder(ALICE).withProgressRecord(VALID_PROGRESS_RECORD).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Person.class.getCanonicalName() + "{name=" + ALICE.getName() + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail() + ", address=" + ALICE.getAddress()
                + ", injuryStatus=" + ALICE.getInjuryStatus() + ", trainingGoal=" + ALICE.getTrainingGoal()
                + ", timeslots=" + ALICE.getTimeslots()
                + ", skill=" + ALICE.getSkill()
                + ", progressRecord=" + ALICE.getProgressRecord() + "}";
        assertEquals(expected, ALICE.toString());
    }
}
