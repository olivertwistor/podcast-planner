package nu.olivertwistor.podcastplanner;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader
{
    private Properties properties;

    public PropertyReader(final String file) throws IOException
    {
        this.properties = new Properties();
        try (final InputStream inputStream =
                     this.getClass().getResourceAsStream(file))
        {
            this.properties.load(inputStream);
        }
    }

    public String getProperty(final String key)
    {
        final String value = this.properties.getProperty(key);
        if (value == null)
        {
            throw new IllegalArgumentException(key + "doesn't exist.");
        }

        return value;
    }

    public String getDatabaseDefaultPath()
    {
        return this.getProperty("database.default.path");
    }
}
