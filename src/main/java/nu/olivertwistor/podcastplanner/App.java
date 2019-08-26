package nu.olivertwistor.podcastplanner;

import nu.olivertwistor.podcastplanner.database.DbUpgrader;
import org.h2.jdbcx.JdbcDataSource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * This class handles the main program flow of this application.
 *
 * @author Johan Nilsson
 * @since 0.1.0
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
        // Get the database path from app.properties.
        final PropertyReader propertyReader =
                new PropertyReader("/app.properties");
        final String databasePath = propertyReader.getDatabaseDefaultPath();

        // Establish a connection to our H2 database.
        final JdbcDataSource jdbcDataSource = new JdbcDataSource();
        jdbcDataSource.setURL("jdbc:h2:" + databasePath);
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
        catch (final IOException e)
        {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
