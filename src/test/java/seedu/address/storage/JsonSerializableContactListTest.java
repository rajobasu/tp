package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.testutil.TypicalCustomers;

public class JsonSerializableContactListTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableContactListTest");
    private static final Path TYPICAL_CUSTOMERS_FILE = TEST_DATA_FOLDER.resolve("typicalCustomersContactList.json");
    private static final Path INVALID_CUSTOMER_FILE = TEST_DATA_FOLDER.resolve("invalidCustomerContactList.json");
    private static final Path DUPLICATE_CUSTOMER_FILE = TEST_DATA_FOLDER.resolve("duplicateCustomerContactList.json");

    @Test
    public void toModelType_typicalCustomersFile_success() throws Exception {
        JsonSerializableContactList dataFromFile = JsonUtil.readJsonFile(TYPICAL_CUSTOMERS_FILE,
                JsonSerializableContactList.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalCustomersAddressBook = TypicalCustomers.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalCustomersAddressBook);
    }

    @Test
    public void toModelType_invalidCustomerFile_throwsIllegalValueException() throws Exception {
        JsonSerializableContactList dataFromFile = JsonUtil.readJsonFile(INVALID_CUSTOMER_FILE,
                JsonSerializableContactList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateCustomers_throwsIllegalValueException() throws Exception {
        JsonSerializableContactList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_CUSTOMER_FILE,
                JsonSerializableContactList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableContactList.MESSAGE_DUPLICATE_CUSTOMER,
                dataFromFile::toModelType);
    }

}
