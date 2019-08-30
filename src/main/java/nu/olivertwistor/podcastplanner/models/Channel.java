package nu.olivertwistor.podcastplanner.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class models a podcast channel with a name, a website and a feed.
 *
 * @author Johan Nilsson
 * @since  0.1.0
 */
public class Channel implements Crud
{
    /**
     * Row ID in the database.
     *
     * @since 0.1.0
     */
    @SuppressWarnings("CanBeFinal")
    private int id;

    /**
     * Name of this channel. Example: "The Daily Source Code".
     *
     * @since 0.1.0
     */
    @SuppressWarnings("CanBeFinal")
    private String name;

    /**
     * The website where listeners can go to listen to this channel, read show
     * notes etc. Example: "http://www.dailysourcecode.com".
     *
     * @since 0.1.0
     */
    @SuppressWarnings("CanBeFinal")
    private String website;

    /**
     * The subscription feed (for example RSS or Atom) for this channel.
     * Example: "https://dailysourcecode.feedburner.com
     *
     * @since 0.1.0
     */
    @SuppressWarnings("CanBeFinal")
    private String feed;

    /**
     * Creates a new Channel object by specifying all class members.
     *
     * @param id      row ID in the database
     * @param name    the name of this channel
     * @param website where listeners can listen to this channel, read show
     *                notes etc
     * @param feed    the subscription feed (for example RSS or Atom)
     *
     * @since 0.1.0
     */
    public Channel(final int id,
                   final String name,
                   final String website,
                   final String feed)
    {
        this.id = id;
        this.name = name;
        this.website = website;
        this.feed = feed;
    }

    /**
     * Creates a new Channel object with all its class members set to default
     * values, except for the required member "name".
     *
     * @param name the name of this channel
     *
     * @since 0.1.0
     */
    public Channel(final String name)
    {
        this(0, name, "", "");
    }

    public int getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public String getWebsite()
    {
        return this.website;
    }

    public String getFeed()
    {
        return this.feed;
    }

    @SuppressWarnings("RedundantThrows")
    @Override
    public int create(final Connection connection) throws SQLException
    {
        int insertedId = 0;

        final String sql = "insert into Channel (name, website, feed) " +
                "values (?, ?, ?)";
        try (final PreparedStatement ps = connection.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS))
        {
            ps.setString(1,this.name);
            ps.setString(2,this.website);
            ps.setString(3,this.feed);

            final int affectedRows = ps.executeUpdate();
            if (affectedRows < 1)
            {
                throw new SQLException("Failed to create a new record.");
            }
            try (final ResultSet rs = ps.getGeneratedKeys())
            {
                if (rs.next())
                {
                    insertedId = (int) rs.getLong(1);
                }
            }
        }

        // Set the class member ID and return it.
        this.id = insertedId;
        return insertedId;
    }

    @SuppressWarnings("RedundantThrows")
    @Override
    public void read(final Connection connection, final int rowId)
            throws SQLException
    {
        final String sql = "select id, name, website, feed from Channel " +
                "where id = ?";
        try (final PreparedStatement ps = connection.prepareStatement(sql))
        {
            ps.setInt(1,rowId);

            try (final ResultSet rs = ps.executeQuery())
            {
                if (rs.next())
                {
                    this.id = rs.getInt("id");
                    this.name = rs.getString("name");
                    this.website = rs.getString("website");
                    this.feed = rs.getString("feed");
                }
            }
        }
    }

    @SuppressWarnings("RedundantThrows")
    @Override
    public void update(final Connection connection, final int rowId)
            throws SQLException
    {
        final String sql = "update Channel set name = ?, website = ?, " +
                "feed = ? where id = ?";
        try (final PreparedStatement ps = connection.prepareStatement(sql))
        {
            ps.setString(1, this.name);
            ps.setString(2, this.website);
            ps.setString(3, this.feed);
            ps.setInt(4, rowId);

            final int affectedRows = ps.executeUpdate(sql);
            if (affectedRows < 1)
            {
                throw new SQLException("Failed to update record.");
            }
        }
    }

    @SuppressWarnings("RedundantThrows")
    @Override
    public void delete(final Connection connection, final int rowId)
            throws SQLException
    {
        final String sql = "delete from Channel where id = ?";
        try (final PreparedStatement ps = connection.prepareStatement(sql))
        {
            ps.setInt(1, rowId);

            final int affectedRows = ps.executeUpdate();
            if (affectedRows < 1)
            {
                throw new SQLException("Failed to delete record.");
            }
        }
    }

    public String toString()
    {
        return "Podcast{" +
                "id=" + this.id +
                ", name='" + this.name + '\'' +
                ", website='" + this.website + '\'' +
                ", feed='" + this.feed + '\'' +
                '}';
    }
}
