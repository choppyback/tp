package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.util.StartupErrorMessage.WARNING_MESSAGE_FORMAT;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Skill;

class StartupErrorMessageTest {

    private static final Path DATA_FILE_PATH = Paths.get("data", "addressbook.json");
    private static final Path BACKUP_PATH = Paths.get("data", "addressbook-20260401-120000.json");
    private static final String FAILED_BACKUP_MESSAGE =
            "\nFailed to backup corrupted data: check the logs for more information";
    private static final String BACKUP_SUCCESS_MESSAGE =
            "\nCorrupted data has been backed up to " + BACKUP_PATH;

    @Test
    void build_withSkillConstraintMessage_includesReason() {
        DataLoadingException exception = new DataLoadingException(
                new IllegalValueException(Skill.MESSAGE_CONSTRAINTS));
        String actual = StartupErrorMessage.build(DATA_FILE_PATH, exception);
        String expected = expectedMessage(Skill.MESSAGE_CONSTRAINTS, FAILED_BACKUP_MESSAGE);
        assertEquals(expected, actual);
    }

    @Test
    void build_withBackupPath_appendsBackupLocation() {
        DataLoadingException exception = new DataLoadingException(
                new IllegalValueException(Skill.MESSAGE_CONSTRAINTS), BACKUP_PATH);
        String actual = StartupErrorMessage.build(DATA_FILE_PATH, exception);
        String expected = expectedMessage(Skill.MESSAGE_CONSTRAINTS, BACKUP_SUCCESS_MESSAGE);
        assertEquals(expected, actual);
    }

    @Test
    void build_withWhitespaceCauseMessage_trimsReason() {
        DataLoadingException exception = new DataLoadingException(
                new IllegalValueException("  " + Skill.MESSAGE_CONSTRAINTS + "  "));
        String actual = StartupErrorMessage.build(DATA_FILE_PATH, exception);
        String expected = expectedMessage(Skill.MESSAGE_CONSTRAINTS, FAILED_BACKUP_MESSAGE);
        assertEquals(expected, actual);
    }

    @Test
    void build_withBlankCauseMessage_usesFallbackReason() {
        DataLoadingException exception = new DataLoadingException(
                new IllegalValueException("   "));
        String actual = StartupErrorMessage.build(DATA_FILE_PATH, exception);
        String expected = expectedMessage(StartupErrorMessage.FALLBACK_REASON, FAILED_BACKUP_MESSAGE);
        assertEquals(expected, actual);
    }

    @Test
    void build_withNullCause_usesFallbackReason() {
        DataLoadingException exception = new DataLoadingException(null);
        String actual = StartupErrorMessage.build(DATA_FILE_PATH, exception);
        String expected = expectedMessage(StartupErrorMessage.FALLBACK_REASON, FAILED_BACKUP_MESSAGE);
        assertEquals(expected, actual);
    }

    @Test
    void build_withNullException_usesFallbackReason() {
        String actual = StartupErrorMessage.build(DATA_FILE_PATH, null);
        String expected = expectedMessage(StartupErrorMessage.FALLBACK_REASON, "");
        assertEquals(expected, actual);
    }

    @Test
    void getUserFacingErrorMessage_withNullException_returnsFallbackReason() {
        assertEquals(StartupErrorMessage.FALLBACK_REASON,
                StartupErrorMessage.getUserFacingErrorMessage(null));
    }

    @Test
    void getUserFacingErrorMessage_withTrimmedCauseMessage_returnsTrimmedMessage() {
        DataLoadingException exception = new DataLoadingException(
                new IllegalValueException("  " + Skill.MESSAGE_CONSTRAINTS + "  "));
        assertEquals(Skill.MESSAGE_CONSTRAINTS,
                StartupErrorMessage.getUserFacingErrorMessage(exception));
    }

    @Test
    void getUserFacingErrorMessage_withBlankCauseMessage_returnsFallbackReason() {
        DataLoadingException exception = new DataLoadingException(
                new IllegalValueException("   "));
        assertEquals(StartupErrorMessage.FALLBACK_REASON,
                StartupErrorMessage.getUserFacingErrorMessage(exception));
    }

    private static String expectedMessage(String reason, String backupInfo) {
        return String.format(WARNING_MESSAGE_FORMAT, DATA_FILE_PATH, reason) + backupInfo;
    }
}