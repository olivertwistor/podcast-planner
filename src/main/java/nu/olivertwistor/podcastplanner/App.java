package nu.olivertwistor.podcastplanner;

import nu.olivertwistor.podcastplanner.database.DbUpgrader;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * This class handles the main program flow of this application.
 *
 * @author Johan Nilsson
 * @since 0.1.0
 */
public final class App
{
    /**
     * Creates a H2 database connection and updates the database to the newest
     * version.
     *
     * @throws SQLException    if the database couldn't be opened
     *
     * @since 0.1.0
     */
    @SuppressWarnings("HardcodedFileSeparator")
    private App() throws SQLException
    {
        // Establish a connection to our H2 database.
        final JdbcDataSource jdbcDataSource = new JdbcDataSource();
        jdbcDataSource.setURL("jdbc:h2:./podcastplanner.h2.db");
        jdbcDataSource.setUser("sa");
        jdbcDataSource.setPassword("sa");

        final Connection connection = jdbcDataSource.getConnection();

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
        catch (final SQLException e)
        {
            System.err.println(
                    "Problem occurred when trying to open the database.");
            e.printStackTrace();
        }
    }
}
