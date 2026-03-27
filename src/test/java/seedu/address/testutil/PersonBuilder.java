package seedu.address.testutil;

import java.util.Set;
import java.util.TreeSet;

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
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_TRAINING_GOAL = "get a 6 pack";
    public static final String DEFAULT_TIMESLOT = "mon:1,2";
    public static final String DEFAULT_SKILL = Skill.SKILL_BEGINNER;
    private static final String DEFAULT_INJURY_STATUS = "Unknown";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private InjuryStatus injuryStatus;
    private Skill skill;
    private TrainingGoal trainingGoal;
    private ProgressRecord progressRecord;
    private Set<Timeslot> timeslots;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        injuryStatus = new InjuryStatus(DEFAULT_INJURY_STATUS);
        trainingGoal = new TrainingGoal(DEFAULT_TRAINING_GOAL);
        skill = new Skill(DEFAULT_SKILL);
        progressRecord = new ProgressRecord(ProgressRecord.DEFAULT_PROGRESS);
        timeslots = new TreeSet<>();
        timeslots.add(new Timeslot(DEFAULT_TIMESLOT));
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        injuryStatus = personToCopy.getInjuryStatus();
        trainingGoal = personToCopy.getTrainingGoal();
        skill = personToCopy.getSkill();
        progressRecord = personToCopy.getProgressRecord();
        timeslots = new TreeSet<>(personToCopy.getTimeslots());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTrainingGoal(String trainingGoal) {
        this.trainingGoal = new TrainingGoal(trainingGoal);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Skill} of the {@code Person} that we are building.
     */
    public PersonBuilder withSkill(String skill) {
        this.skill = new Skill(skill);
        return this;
    }

    /**
     * Sets the {@code ProgressRecord} of the {@code Person} that we are building.
     */
    public PersonBuilder withProgressRecord(String progressRecord) {
        this.progressRecord = new ProgressRecord(progressRecord);
        return this;
    }


    /**
     * Sets the {@code InjuryStatus} of the {@code Person} that we are building.
     */
    public PersonBuilder withInjuryStatus(String injuryStatus) {
        this.injuryStatus = new InjuryStatus(injuryStatus);
        return this;
    }

    /**
     * Parses the {@code timeslots} into a {@code Set<Timeslot>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTimeslots(String... timeslots) {
        this.timeslots = SampleDataUtil.getTimeslotSet(timeslots);
        return this;
    }

    /**
     * Builds a person with all specified attributes.
     *
     * @return Person built with the specified attributes.
     */
    public Person build() {
        return new Person(name, phone, email, address, injuryStatus, trainingGoal, timeslots,
                progressRecord, skill);
    }

}
