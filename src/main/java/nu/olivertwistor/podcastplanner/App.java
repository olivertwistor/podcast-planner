package nu.olivertwistor.podcastplanner;

import nu.olivertwistor.podcastplanner.database.DbUpgrader;
import nu.olivertwistor.podcastplanner.database.DbUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * This class handles the main program flow of this application.
 *
 * @author Johan Nilsson
 * @since  0.1.0
 */
public final class App
{
    /**
     * Creates a connection to the H2 database indicated by app properties, and
     * updates the database to the newest version.
     *
     * @throws IOException  if the app properties couldn't be loaded or read
     * @throws SQLException if the database couldn't be opened
     *
     * @since 0.1.0
     */
    private App() throws IOException, SQLException
    {
        final Connection connection = DbUtil.getConnection();

        // Upgrade the database.
        final DbUpgrader dbUpgrader = new DbUpgrader(connection);
        dbUpgrader.upgrade();
    }

    /**
     * Entry point for this application.
     *
     * @param args unused for the time being
     *
     * @since 0.1.0
     */
    public static void main(final String[] args)
    {
        try
        {
            new App();
        }
        catch (final SQLException | IOException e)
        {
            System.err.println(e.getMessage());
        }
    }
}
