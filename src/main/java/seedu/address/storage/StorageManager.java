package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyContactList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of ContactList data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ContactListStorage contactListStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ContactListStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ContactListStorage contactListStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.contactListStorage = contactListStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ ContactList methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return contactListStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyContactList> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(contactListStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyContactList> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return contactListStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyContactList addressBook) throws IOException {
        saveAddressBook(addressBook, contactListStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyContactList addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        contactListStorage.saveAddressBook(addressBook, filePath);
    }

}
