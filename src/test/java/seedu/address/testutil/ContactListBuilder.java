package seedu.address.testutil;

import seedu.address.model.ContactList;
import seedu.address.model.customer.Customer;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code ContactList ab = new ContactListBuilder().withCustomer("John", "Doe").build();}
 */
public class ContactListBuilder {

    private ContactList contactList;

    public ContactListBuilder() {
        contactList = new ContactList();
    }

    public ContactListBuilder(ContactList contactList) {
        this.contactList = contactList;
    }

    /**
     * Adds a new {@code Customer} to the {@code ContactList} that we are building.
     */
    public ContactListBuilder withCustomer(Customer customer) {
        contactList.addCustomer(customer);
        return this;
    }

    public ContactList build() {
        return contactList;
    }
}
