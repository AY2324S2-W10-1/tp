package seedu.realodex.logic.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A prefix that marks the beginning of an argument in an arguments string.
 * E.g. 't/' in 'add James t/ friend'.
 */
public class Prefix {
    private final String prefix;

    public Prefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    public static ArrayList<String> returnListOfMissingPrefixes(Map<Prefix, List<String>> argMultimap,
                                                          Prefix[] listOfCompulsoryPrefix) {
        ArrayList<String> listOfMessagesForMissingPrefixes = new ArrayList<>();
        for (Prefix ofCompulsoryPrefix : listOfCompulsoryPrefix) {
            if (!argMultimap.containsKey(ofCompulsoryPrefix)) {
                listOfMessagesForMissingPrefixes.add(ofCompulsoryPrefix.toString());
            }
        }
        return listOfMessagesForMissingPrefixes;
    }

    @Override
    public String toString() {
        return getPrefix();
    }

    @Override
    public int hashCode() {
        return prefix == null ? 0 : prefix.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Prefix)) {
            return false;
        }

        Prefix otherPrefix = (Prefix) other;
        return prefix.equals(otherPrefix.prefix);
    }
}
