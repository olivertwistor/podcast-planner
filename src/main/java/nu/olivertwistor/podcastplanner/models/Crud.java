package nu.olivertwistor.podcastplanner.models;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Classes that implement this interface have CRUD capabilities, that is to
 * Create, Read, Update and Delete records in a database. All methods take a
 * {@link Connection} object as a parameter to increase testability.
 *
 * @author Johan Nilsson
 * @since 0.1.0
 */
@SuppressWarnings("RedundantThrows")
public interface Crud
{
    /**
     * Creates a new record in the database based on the state of this object.
     *
     * @param connection an established database {@link Connection}
     *
     * @return the row ID of the newly created record
     *
     * @throws SQLException if the database record couldn't be created
     *
     * @since 0.1.0
     */
    int create(Connection connection) throws SQLException;

    /**
     * Reads from a record in the database and fills this object's class
     * members with the data from that record.
     *
     * @param connection an established database {@link Connection}
     * @param rowId      from which record to read
     *
     * @throws SQLException if the database record couldn't be read
     *
     * @since 0.1.0
     */
    void read(Connection connection, int rowId) throws SQLException;

    /**
     * Updates a record in the database with the current data that's in this
     * object.
     *
     * @param connection an established database {@link Connection}
     * @param rowId      which record to update
     *
     * @throws SQLException if the database record couldn't be updated
     *
     * @since 0.1.0
     */
    void update(Connection connection, int rowId) throws SQLException;

    /**
     * Deletes a record from the database.
     *
     * @param connection an established database {@link Connection}
     * @param rowId      which record to delete
     *
     * @throws SQLException if the database record couldn't be deleted
     *
     * @since 0.1.0
     */
    void delete(Connection connection, int rowId) throws SQLException;
}
