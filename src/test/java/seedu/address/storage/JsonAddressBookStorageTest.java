package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;

public class JsonAddressBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAddressBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyAddressBook> readAddressBook(String filePath) throws Exception {
        Path sourcePath = addToTestDataPathIfNotNull(filePath);
        if (sourcePath == null) {
            return new JsonAddressBookStorage(Paths.get(filePath)).readAddressBook(null);
        }
        JsonAddressBookStorage storage;
        Path fileToRead;
        if (Files.exists(sourcePath)) {
            Path tempFile = testFolder.resolve(sourcePath.getFileName());
            Files.copy(sourcePath, tempFile, StandardCopyOption.REPLACE_EXISTING);
            storage = new JsonAddressBookStorage(tempFile);
            fileToRead = tempFile;
        } else {
            storage = new JsonAddressBookStorage(sourcePath);
            fileToRead = sourcePath;
        }
        return storage.readAddressBook(fileToRead);
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAddressBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readAddressBook("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readAddressBook("invalidPersonAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readAddressBook("invalidAndValidPersonAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwsExceptionAndCreatesBackup() throws Exception {
        Path tempInvalidFile = testFolder.resolve("invalidPersonAddressBook.json");
        Files.copy(addToTestDataPathIfNotNull("invalidPersonAddressBook.json"), tempInvalidFile,
                StandardCopyOption.REPLACE_EXISTING);
        JsonAddressBookStorage storage = new JsonAddressBookStorage(tempInvalidFile);

        DataLoadingException exception = Assertions.assertThrows(DataLoadingException.class,
                () -> storage.readAddressBook(tempInvalidFile));
        assertTrue(exception.getBackupFilePath().isPresent());
        Path backupPath = exception.getBackupFilePath().get();
        assertTrue(Files.exists(backupPath));
        assertEquals(tempInvalidFile.getParent(), backupPath.getParent());
        assertTrue(backupPath.getFileName().toString().startsWith("invalidPersonAddressBook-"));
        assertFalse(backupPath.equals(tempInvalidFile));
    }

    @Test
    public void backupCorruptedAddressBook_missingFile_returnsNull() throws Exception {
        JsonAddressBookStorage storage = new JsonAddressBookStorage(testFolder.resolve("missing.json"));
        assertNull(invokeBackupCorruptedAddressBook(storage, storage.getAddressBookFilePath()));
    }

    @Test
    public void backupCorruptedAddressBook_copyFails_returnsNull() throws Exception {
        Path corruptedFile = Files.createFile(testFolder.resolve("corrupted.json"));
        JsonAddressBookStorage storage = new JsonAddressBookStorage(corruptedFile);
        Path backupPath = computeTimestampedBackupPath(storage, corruptedFile);
        while (!computeTimestampedBackupPath(storage, corruptedFile).equals(backupPath)) {
            backupPath = computeTimestampedBackupPath(storage, corruptedFile);
        }
        // Pre-create the backup file to force Files.copy(...) to fail.
        Files.createFile(backupPath);
        try {
            assertNull(invokeBackupCorruptedAddressBook(storage, corruptedFile));
        } finally {
            Files.deleteIfExists(backupPath);
        }
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        AddressBook original = getTypicalAddressBook();
        JsonAddressBookStorage jsonAddressBookStorage = new JsonAddressBookStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveAddressBook(original, filePath);
        ReadOnlyAddressBook readBack = jsonAddressBookStorage.readAddressBook(filePath).get();
        assertEquals(original, new AddressBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonAddressBookStorage.saveAddressBook(original, filePath);
        readBack = jsonAddressBookStorage.readAddressBook(filePath).get();
        assertEquals(original, new AddressBook(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonAddressBookStorage.saveAddressBook(original); // file path not specified
        readBack = jsonAddressBookStorage.readAddressBook().get(); // file path not specified
        assertEquals(original, new AddressBook(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyAddressBook addressBook, String filePath) {
        try {
            new JsonAddressBookStorage(Paths.get(filePath))
                    .saveAddressBook(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new AddressBook(), null));
    }

    private static Path invokeBackupCorruptedAddressBook(JsonAddressBookStorage storage, Path filePath)
            throws ReflectiveOperationException {
        Method method = JsonAddressBookStorage.class.getDeclaredMethod("backupCorruptedAddressBook", Path.class);
        method.setAccessible(true);
        return (Path) method.invoke(storage, filePath);
    }

    private static Path computeTimestampedBackupPath(JsonAddressBookStorage storage, Path filePath)
            throws ReflectiveOperationException {
        Method method = JsonAddressBookStorage.class.getDeclaredMethod("buildTimestampedBackupPath", Path.class);
        method.setAccessible(true);
        return (Path) method.invoke(storage, filePath);
    }
}
