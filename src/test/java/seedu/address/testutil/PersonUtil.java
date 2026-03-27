package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INJURY_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROGRESS_RECORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRAINING_GOAL;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Person;
import seedu.address.model.timeslot.Timeslot;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        sb.append(PREFIX_TRAINING_GOAL + person.getTrainingGoal().value + " ");
        person.getTimeslots().stream().forEach(
                t -> sb.append(PREFIX_TIMESLOT + t.toStorageString() + " ")
        );
        sb.append(PREFIX_SKILL + person.getSkill().value + " ");

        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS)
                                                    .append(address.value).append(" "));
        descriptor.getTrainingGoal().ifPresent(trainingGoal -> sb.append(PREFIX_TRAINING_GOAL)
                                                    .append(trainingGoal.value).append(" "));
        descriptor.getSkill().ifPresent(skill -> sb.append(PREFIX_SKILL).append(skill.value).append(" "));
        descriptor.getProgressRecord().ifPresent(progressRecord -> sb.append(PREFIX_PROGRESS_RECORD)
                .append(progressRecord.value).append(" "));
        descriptor.getInjuryStatus().ifPresent(injury -> sb.append(PREFIX_INJURY_STATUS)
                .append(injury.value).append(" "));
        if (descriptor.getTimeslots().isPresent()) {
            Set<Timeslot> timeslots = descriptor.getTimeslots().get();
            if (timeslots.isEmpty()) {
                sb.append(PREFIX_TIMESLOT);
            } else {
                timeslots.forEach(s -> sb.append(PREFIX_TIMESLOT).append(s.toStorageString()).append(" "));
            }
        }
        return sb.toString();
    }
}
