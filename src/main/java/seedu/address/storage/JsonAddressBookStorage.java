package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonAddressBookStorage implements AddressBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAddressBookStorage.class);
    private static final DateTimeFormatter BACKUP_TIMESTAMP_FORMAT =
            DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

    private Path filePath;

    public JsonAddressBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAddressBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataLoadingException {
        return readAddressBook(filePath);
    }

    /**
     * Similar to {@link #readAddressBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableAddressBook> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableAddressBook.class);
        if (jsonAddressBook.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.warning("Illegal values found in " + filePath + ": " + ive.getMessage());
            Path backupPath = backupCorruptedAddressBook(filePath);
            throw new DataLoadingException(ive, backupPath);
        }
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveAddressBook(ReadOnlyAddressBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableAddressBook(addressBook), filePath);
    }

    /**
     * Copies the corrupted JSON file next to the original with a timestamp suffix.
     *
     * @return the path to the backup copy, or {@code null} if the copy failed.
     */
    private Path backupCorruptedAddressBook(Path filePath) {
        if (filePath == null || !Files.exists(filePath)) {
            return null;
        }

        Path backupPath = buildTimestampedBackupPath(filePath);
        try {
            Files.copy(filePath, backupPath);
            logger.info("Corrupted address book backed up to " + backupPath);
        } catch (IOException e) {
            logger.warning("Failed to back up corrupted address book to " + e);
            return null;
        }
        return backupPath;
    }

    /**
     * Builds a sibling path for the backup by inserting a timestamp before the file extension.
     */
    private Path buildTimestampedBackupPath(Path originalPath) {
        String backupFileName = timestampedFileName(originalPath.getFileName().toString());
        return originalPath.resolveSibling(backupFileName);
    }

    /**
     * Returns {@code fileName} with a timestamp inserted before its extension (or at the end if none).
     */
    private String timestampedFileName(String fileName) {
        String timestamp = LocalDateTime.now().format(BACKUP_TIMESTAMP_FORMAT);
        String[] parts = fileName.split("\\.", 2);
        if (parts.length == 2) {
            return parts[0] + "-" + timestamp + "." + parts[1];
        }
        return fileName + "-" + timestamp;
    }

}
