package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.dao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entities.User;

/**
 * Data Access Object for User.
 */
public interface UserDao {

    /**
     * Inserts a new User into the table.
     *
     * @param user The new User.
     * @return The row ID of the newly inserted User.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(User user);

    /**
     * Updates a User in the table.
     *
     * @param user The User to update.
     * @return The number of Users updated. This should be {@code 1}.
     */
    @Update
    int update(User user);

    /**
     * Deletes a User from the table.
     *
     * @param user The User to delete.
     * @return The number of Users deleted. This should be {@code 1}.
     */
    @Delete
    int delete(User user);

    /**
     * Selects all Users in the table.
     *
     * @return A List of all Users in the table.
     */
    @Query("SELECT * FROM " + User.TABLE_NAME)
    List<User> selectAll();

    /**
     * Selects a User by UUID.
     *
     * @param uuid The universally unique identifier of the User.
     * @return The selected User.
     */
    @Query("SELECT * FROM " + User.TABLE_NAME + " WHERE uuid = :uuid")
    User selectByUUID(long uuid);

}
