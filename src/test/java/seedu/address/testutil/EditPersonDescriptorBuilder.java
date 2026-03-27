package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Skill;
import seedu.address.model.person.TrainingGoal;
import seedu.address.model.timeslot.Timeslot;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setAddress(person.getAddress());
        descriptor.setTrainingGoal(person.getTrainingGoal());
        descriptor.setTimeslots(person.getTimeslots());
        descriptor.setSkill(person.getSkill());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code timeslots} into a {@code Set<Timeslot>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTimeslots(String... timeslots) {
        Set<Timeslot> timeslotSet = Stream.of(timeslots).map(Timeslot::new).collect(Collectors.toSet());
        descriptor.setTimeslots(timeslotSet);
        return this;
    }

    /**
     * Sets the {@code TrainingGoal} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withTrainingGoal(String trainingGoal) {
        descriptor.setTrainingGoal(new TrainingGoal(trainingGoal));
        return this;
    }

    /**
     * Sets the {@code Skill} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withSkill(String skill) {
        descriptor.setSkill(new Skill(skill));
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
