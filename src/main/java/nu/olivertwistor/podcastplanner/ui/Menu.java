package nu.olivertwistor.podcastplanner.ui;

import ch.rfin.util.Pair;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class Menu
{
    protected Map<Integer, String> menuOptions = new HashMap<>();
    protected Pair<Integer, String> cancel;

    protected Menu(final String cancelText)
    {
        this.cancel = Pair.of(0, cancelText);
    }

    protected Menu()
    {
        this("Go back");
    }

    public void showMenu()
    {
        final String lineFormat = "%d. %s";

        // Print out the standard menu options first.
        for (final Map.Entry<Integer, String> option :
                this.menuOptions.entrySet())
        {
            final String menuLine = String.format(
                    lineFormat, option.getKey(), option.getValue());
            System.out.println(menuLine);
        }

        // Then print out the cancel menu option. This should be preceded (and
        // succeeded) with a blank line.
        System.out.println();
        final String cancelLine = String.format(
                lineFormat, this.cancel.get_1(), this.cancel.get_2());
        System.out.println(cancelLine);
        System.out.println();
    }

    public abstract void doAction() throws IOException;
}
