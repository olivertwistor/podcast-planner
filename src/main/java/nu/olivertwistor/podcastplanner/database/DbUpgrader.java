package nu.olivertwistor.podcastplanner.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Handles upgrades and alterations to the database schema between versions of
 * the application.
 *
 * At the current iteration of this class, the SQL doing the actual upgrades is
 * hardcoded within this class. My goal is to have SQL files that are loaded in
 * and read dynamically depending on the desired upgrade version and the SQL
 * filenames.
 *
 * @author Johan Nilsson
 * @since 0.1.0
 */
public class DbUpgrader
{
    /**
     * A connection to the database that is to be upgraded.
     *
     * @since 0.1.0
     */
    @SuppressWarnings("CanBeFinal")
    private Connection connection;

    /**
     * Creates a new object of this class with a certain database connection to
     * be used with this object.
     *
     * @param connection the database connection object to use
     *
     * @since 0.1.0
     */
    public DbUpgrader(final Connection connection)
    {
        this.connection = connection;
    }

    /**
     * Upgrades the database to the latest version.
     *
     * All updates are hardcoded in this class, so there's no way to adjust its
     * behaviour from the perspective of the caller.
     *
     * @throws SQLException if the database couldn't be updated
     *
     * @since 0.1.0
     */
    public void upgrade() throws SQLException
    {
        // First, save the auto-commit state on the connection. We want to
        // restore it later. Then disable auto-commit.
        final boolean originalAutoCommit = this.connection.getAutoCommit();
        this.connection.setAutoCommit(false);

        // Flag to make sure that all upgrades goes well.
        boolean allOk = true;

        // First upgrade.
        try (final Statement statement = this.connection.createStatement())
        {
            final String createTableChannel = "create table if not exists " +
                    "Channel (id identity not null, name varchar(255) " +
                    "not null, website varchar(1023), feed varchar(1023), " +
                    "primary key (id))";

            final boolean thisOk = statement.execute(createTableChannel);

            allOk = allOk && thisOk;
        }

        // If all upgrades went well, we can commit the transaction. Otherwise
        // we have to roll back.
        if (allOk)
        {
            this.connection.commit();
        }
        else
        {
            this.connection.rollback();
        }

        // Restore the previous auto-commit state on the connection.
        this.connection.setAutoCommit(originalAutoCommit);
    }

    @Override
    public String toString()
    {
        return "DbUpgrader{" +
                "connection=" + this.connection +
                '}';
    }
}
