package seedu.address.logic.filters;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CARS_OWNED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CARS_PREFERRED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COE_EXPIRY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Helper class to support the filters. This is essentially a factory class which takes in a string and returns the
 * appropriate kind of filter it represents, after parsing.
 */
public class Filters {
    public static final String MESSAGE_MULTIPLE_FILTERS = "Number of filters between two logical operators should be "
        + "exactly 1!";
    public static final String MESSAGE_UNKNOWN_PREFIX = "The given find filter is not valid : ";


    /**
     * This method parses the given string, and checks which filter it corresponds to, and returns that. However, in
     * case there are no filters matching, or more than one token, this throws an exception.
     *
     * @param info the given input string
     * @return the corresponding filter created after parsing the input string
     * @throws IllegalArgumentException If either there are more than one prefixes or its an unknown prefix
     */
    public static Filter getCorrespondingFilter(String info) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(" " + info + " ", PREFIX_NAME,
            PREFIX_EMAIL,
            PREFIX_PHONE,
            PREFIX_ADDRESS, PREFIX_DOB, PREFIX_TAG, PREFIX_CARS_OWNED, PREFIX_CARS_PREFERRED, PREFIX_COE_EXPIRY);


        if (argumentMultimap.getTotalSize() > 2) { // since there is also a dummy position :(
            throw new ParseException(MESSAGE_MULTIPLE_FILTERS);
        }

        if (argumentMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            return new EmailFilter(argumentMultimap.getValue(PREFIX_EMAIL).get());
        }

        if (argumentMultimap.getValue(PREFIX_PHONE).isPresent()) {
            return new PhoneNumberFilter(argumentMultimap.getValue(PREFIX_PHONE).get());
        }

        if (argumentMultimap.getValue(PREFIX_COE_EXPIRY).isPresent()) {
            return new CoeExpiryFilter(argumentMultimap.getValue(PREFIX_COE_EXPIRY).get());
        }

        if (argumentMultimap.getValue(PREFIX_DOB).isPresent()) {
            return new DobFilter(argumentMultimap.getValue(PREFIX_DOB).get());
        }

        if (argumentMultimap.getValue(PREFIX_NAME).isPresent()) {
            return new NameFilter(argumentMultimap.getValue(PREFIX_NAME).get());
        }

        if (argumentMultimap.getValue(PREFIX_CARS_OWNED).isPresent()) {
            return new CarsOwnedFilter(argumentMultimap.getValue(PREFIX_CARS_OWNED).get());
        }

        if (argumentMultimap.getValue(PREFIX_CARS_PREFERRED).isPresent()) {
            return new CarsPreferredFilter(argumentMultimap.getValue(PREFIX_CARS_PREFERRED).get());
        }

        if (argumentMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            return new AddressFilter(argumentMultimap.getValue(PREFIX_ADDRESS).get());
        }

        if (argumentMultimap.getValue(PREFIX_TAG).isPresent()) {
            return new TagFilter(argumentMultimap.getValue(PREFIX_TAG).get());
        }

        throw new ParseException(MESSAGE_UNKNOWN_PREFIX + info);
    }
}
