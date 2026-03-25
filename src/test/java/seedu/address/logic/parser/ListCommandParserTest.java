package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListCommand;
import seedu.address.model.person.FilteredSkill;

public class ListCommandParserTest {

    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_emptyArgs_returnsListCommand() {
        assertParseSuccess(parser, "", new ListCommand());
    }

    @Test
    public void parse_whitespaceArgs_returnsListCommand() {
        assertParseSuccess(parser, "   ", new ListCommand());
    }

    @Test
    public void parse_validSkillFilterBeginner_returnsListCommand() {
        ListCommand expectedCommand = new ListCommand(List.of(new FilteredSkill("beginner")));
        assertParseSuccess(parser, " s/beginner", expectedCommand);
    }

    @Test
    public void parse_validSkillFilterIntermediate_returnsListCommand() {
        ListCommand expectedCommand = new ListCommand(List.of(new FilteredSkill("intermediate")));
        assertParseSuccess(parser, " s/intermediate", expectedCommand);
    }

    @Test
    public void parse_validSkillFilterExpert_returnsListCommand() {
        ListCommand expectedCommand = new ListCommand(List.of(new FilteredSkill("expert")));
        assertParseSuccess(parser, " s/expert", expectedCommand);
    }

    @Test
    public void parse_validSkillFilterWithWhitespace_returnsListCommand() {
        ListCommand expectedCommand = new ListCommand(List.of(new FilteredSkill("intermediate")));
        assertParseSuccess(parser, "   s/intermediate   ", expectedCommand);
    }

    @Test
    public void parse_validSkillFilterMixedCase_returnsListCommand() {
        ListCommand expectedCommand = new ListCommand(List.of(new FilteredSkill("ExPeRt")));
        assertParseSuccess(parser, " s/ExPeRt", expectedCommand);
    }

    @Test
    public void parse_customSkillValue_returnsListCommand() {
        ListCommand expectedCommand = new ListCommand(List.of(new FilteredSkill("advanced")));
        assertParseSuccess(parser, " s/advanced", expectedCommand);
    }

    @Test
    public void parse_multipleValidSkillFilters_returnsListCommand() {
        ListCommand expectedCommand = new ListCommand(List.of(
                new FilteredSkill("beginner"), new FilteredSkill("expert")));
        assertParseSuccess(parser, " s/beginner s/expert", expectedCommand);
    }

    @Test
    public void parse_multipleSkillFiltersMixedCase_returnsListCommand() {
        ListCommand expectedCommand = new ListCommand(List.of(
                new FilteredSkill("BEGINNER"), new FilteredSkill("eXpErT")));
        assertParseSuccess(parser, " s/BEGINNER s/eXpErT", expectedCommand);
    }

    @Test
    public void parse_threeSkillFilters_returnsListCommand() {
        ListCommand expectedCommand = new ListCommand(List.of(
                new FilteredSkill("beginner"),
                new FilteredSkill("intermediate"),
                new FilteredSkill("expert")));
        assertParseSuccess(parser, " s/beginner s/intermediate s/expert", expectedCommand);
    }

    @Test
    public void parse_emptySkillValue_throwsParseException() {
        assertParseFailure(parser, " s/", FilteredSkill.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_whitespaceSkillValue_throwsParseException() {
        assertParseFailure(parser, " s/   ", FilteredSkill.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_multipleSkillFiltersOneEmpty_throwsParseException() {
        assertParseFailure(parser, " s/beginner s/", FilteredSkill.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidPreamble_throwsParseException() {
        assertParseFailure(parser, "invalid s/beginner",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    // ===================== Single Invalid Prefix Tests =====================

    @Test
    public void parse_namePrefix_throwsParseException() {
        assertParseFailure(parser, " n/John",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_phonePrefix_throwsParseException() {
        assertParseFailure(parser, " p/12345678",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emailPrefix_throwsParseException() {
        assertParseFailure(parser, " e/john@example.com",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_addressPrefix_throwsParseException() {
        assertParseFailure(parser, " a/123 Street",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_timeslotPrefix_throwsParseException() {
        assertParseFailure(parser, " ts/mon:0900-1000",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_trainingGoalPrefix_throwsParseException() {
        assertParseFailure(parser, " t/marathon",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_progressRecordPrefix_throwsParseException() {
        assertParseFailure(parser, " pr/20%",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_injuryStatusPrefix_throwsParseException() {
        assertParseFailure(parser, " i/broken bones",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    // ===================== Valid Skill + Single Invalid Prefix Tests =====================

    @Test
    public void parse_skillWithNamePrefix_throwsParseException() {
        assertParseFailure(parser, " s/beginner n/John",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_skillWithPhonePrefix_throwsParseException() {
        assertParseFailure(parser, " s/beginner p/12345678",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_skillWithEmailPrefix_throwsParseException() {
        assertParseFailure(parser, " s/beginner e/john@example.com",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_skillWithAddressPrefix_throwsParseException() {
        assertParseFailure(parser, " s/beginner a/123 Street",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_skillWithTimeslotPrefix_throwsParseException() {
        assertParseFailure(parser, " s/beginner ts/mon:0900-1000",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_skillWithTrainingGoalPrefix_throwsParseException() {
        assertParseFailure(parser, " s/beginner t/marathon",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_skillWithProgressRecordPrefix_throwsParseException() {
        assertParseFailure(parser, " s/beginner pr/50%",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_skillWithInjuryStatusPrefix_throwsParseException() {
        assertParseFailure(parser, " s/beginner i/dead",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    // ===================== Multiple Invalid Prefixes Tests =====================

    @Test
    public void parse_nameAndPhonePrefixes_throwsParseException() {
        assertParseFailure(parser, " n/John p/12345678",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_nameAndEmailPrefixes_throwsParseException() {
        assertParseFailure(parser, " n/John e/john@example.com",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_nameAndAddressPrefixes_throwsParseException() {
        assertParseFailure(parser, " n/John a/123 Street",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_allInvalidPrefixes_throwsParseException() {
        assertParseFailure(parser, " n/John p/12345678 e/john@example.com "
                        + "a/123 Street ts/Mon:1 t/marathon pr/90% i/brain dead",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    // ===================== Preamble Tests =====================

    @Test
    public void parse_preambleOnly_throwsParseException() {
        assertParseFailure(parser, "sometext",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_preambleWithSkillPrefix_throwsParseException() {
        assertParseFailure(parser, "sometext s/beginner",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_preambleWithInvalidPrefix_throwsParseException() {
        assertParseFailure(parser, "sometext n/John",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_preambleWithValidPrefix_throwsParseException() {
        assertParseFailure(parser, "extra text s/expert",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }
}
