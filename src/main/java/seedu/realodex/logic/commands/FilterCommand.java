package seedu.realodex.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.realodex.commons.util.ToStringBuilder;
import seedu.realodex.logic.Messages;
import seedu.realodex.model.Model;
import seedu.realodex.model.person.Person;


/**
 * Filters and lists all persons in realodex whose name contains the argument keyphrase.
 * Keyphrase matching is case-insensitive.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all clients whose names contain "
            + "the specified keyphrase (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYPHRASE (Non-empty String)\n"
            + "Example: " + COMMAND_WORD + " alice tan";

    public static final String MESSAGE_FILTER_HELP = "Filter Command: Filters clients whose names contain "
            + "the specified keyphrase (case-insensitive) and displays them as a list with index numbers.\n"
            + "Format: filter KEYPHRASE\n"
            + "Example: filter Jus\n";

    public static final String MESSAGE_FILTER_CONFLICT = "Filter command can only filter by one field.\n";

    private final Predicate<Person> predicate;

    public FilterCommand(Predicate<Person> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterCommand)) {
            return false;
        }

        FilterCommand otherFilterCommand = (FilterCommand) other;
        return predicate.equals(otherFilterCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
