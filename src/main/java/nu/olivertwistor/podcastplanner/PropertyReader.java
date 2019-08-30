package nu.olivertwistor.podcastplanner;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class helps you read properties from property files.
 *
 * @author Johan Nilsson
 * @since  0.1.0
 */
public class PropertyReader
{
    /**
     * Properties object loaded from a properties file.
     *
     * @since 0.1.0
     */
    private final Properties properties;

    /**
     * Loads properties from a properties file.
     *
     * @param file path to the properties file to load
     *
     * @throws IOException if the specified file couldn't be loaded
     *
     * @since 0.1.0
     */
    public PropertyReader(final String file) throws IOException
    {
        this.properties = new Properties();
        try (final InputStream inputStream =
                     this.getClass().getResourceAsStream(file))
        {
            this.properties.load(inputStream);
        }
    }

    /**
     * Gets the property value based on a key.
     *
     * @param key property key
     *
     * @return the property value
     *
     * @throws IllegalArgumentException if the key doesn't exist
     *
     * @since 0.1.0
     */
    public String getProperty(final String key)
    {
        final String value = this.properties.getProperty(key);
        if (value == null)
        {
            throw new IllegalArgumentException(key + "doesn't exist.");
        }

        return value;
    }

    /**
     * Gets the database default path.
     *
     * @return the database default path as a string.
     *
     * @throws IllegalArgumentException if the database default path couldn't
     *                                  be found in the loaded properties file.
     *
     * @since 0.1.0
     */
    public String getDatabaseDefaultPath()
    {
        return this.getProperty("database.default.path");
    }

    @Override
    public String toString()
    {
        return "PropertyReader{" +
                "properties=" + this.properties +
                '}';
    }
}
