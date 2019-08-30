package nu.olivertwistor.podcastplanner.database;

import nu.olivertwistor.podcastplanner.PropertyReader;
import org.h2.jdbcx.JdbcDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DbUtil
{
    public static Connection getConnection() throws IOException, SQLException
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

        return connection;
    }

    private DbUtil()
    {
        // Intentionally empty.
    }
}
