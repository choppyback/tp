package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.InjuryStatus;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.ProgressRecord;
import seedu.address.model.person.Skill;
import seedu.address.model.person.TrainingGoal;
import seedu.address.model.timeslot.Timeslot;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                new InjuryStatus("Healthy"),
                new TrainingGoal("1000 pushups"),
                getTimeslotSet("mon:1", "tue:2", "wed:3", "thu:4", "fri:5", "sat:6", "sun:7"),
                new ProgressRecord("100%"),
                new Skill(Skill.SKILL_EXPERT)),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                new InjuryStatus("Ankle Sprain"),
                new TrainingGoal("10 km run no sweat"),
                getTimeslotSet("mon:2", "tue:1", "wed:5", "thu:4", "fri:3", "sat:6", "sun:7"),
                new ProgressRecord("10%"),
                new Skill(Skill.SKILL_INTERMEDIATE)),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                new InjuryStatus("Recovering"),
                new TrainingGoal("6 packs"),
                getTimeslotSet("mon:1,2,3", "tue:2,3", "wed:3,4", "thu:4,6", "fri:5,12", "sat:6,11", "sun:7,8"),
                new ProgressRecord("5%"),
                new Skill(Skill.SKILL_BEGINNER)),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                new InjuryStatus("Shoulder Injury"),
                new TrainingGoal("50m sprint"),
                getTimeslotSet("mon:1,7", "tue:2,8", "wed:3,9", "thu:4,10", "fri:5,11", "sat:6,12", "sun:7"),
                new ProgressRecord("1%"),
                new Skill(Skill.SKILL_BEGINNER)),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                new InjuryStatus("None"),
                new TrainingGoal("2 min 2.4k"),
                getTimeslotSet("mon:1", "tue:2", "wed:3", "thu:4", "fri:5", "sat:6", "sun:7"),
                new ProgressRecord("100%"),
                new Skill(Skill.SKILL_EXPERT)),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                new InjuryStatus("Resting"),
                new TrainingGoal("fly"),
                getTimeslotSet("mon:1", "tue:2", "wed:3", "thu:4", "fri:5", "sat:6", "sun:7"),
                new ProgressRecord("100%"),
                new Skill(Skill.SKILL_INTERMEDIATE))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a Timeslot set containing the list of timeslots given.
     */
    public static Set<Timeslot> getTimeslotSet(String... timeslots) {
        return Arrays.stream(timeslots)
                .map(Timeslot::new)
                .collect(Collectors.toSet());
    }

}
