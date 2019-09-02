package nu.olivertwistor.podcastplanner.ui;

import ch.rfin.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
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

    public int getChoice(final boolean onlyAccepted) throws IOException
    {
        int choice = -1;

        if (onlyAccepted)
        {
            do
            {
                choice = this.readInput();
            }
            while (choice != -1);
        }
        else
        {
            choice = this.readInput();
        }

        return choice;
    }

    public int getChoice() throws IOException
    {
        return this.getChoice(true);
    }

    public abstract void doAction(final int choice);

    private int readInput() throws IOException
    {
        int integerInput = -1;

        System.out.println("? ");
        try (final BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in, StandardCharsets.UTF_8)))
        {
            final String input = br.readLine().trim();

            try
            {
                integerInput = Integer.parseInt(input);
            }
            catch (final NumberFormatException e)
            {
                System.err.println(input + "is not a number.");
            }
        }

        return integerInput;
    }
}
