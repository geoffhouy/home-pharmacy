package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity.Pill;

/**
 * Data Access Object for Pill.
 */
@Dao
public interface PillDao {

    /**
     * Inserts a new Pill into the table.
     *
     * @param pill The new Pill.
     * @return The row ID of the newly inserted Pill.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Pill pill);

    /**
     * Updates a Pill in the table.
     *
     * @param pill The Pill to update.
     * @return The number of Pills updated. This should be {@code 1}.
     */
    @Update
    int update(Pill pill);

    /**
     * Deletes a Pill from the table.
     *
     * @param pill The Pill to delete.
     * @return The number of Pills deleted. This should be {@code 1}.
     */
    @Delete
    int delete(Pill pill);

    /**
     * Deletes all Pills from the table.
     */
    @Query("DELETE FROM " + Pill.TABLE_NAME)
    void deleteAll();

    /**
     * Selects all Pills in the table.
     *
     * @return A List of all Pills in the table.
     */
    @Query("SELECT * FROM " + Pill.TABLE_NAME)
    LiveData<List<Pill>> selectAll();

    /**
     * Selects a Pill by UUID.
     *
     * @param uuid The universally unique identifier of the Pill.
     * @return The selected Pill.
     */
    @Query("SELECT * FROM " + Pill.TABLE_NAME + " WHERE uuid = :uuid")
    Pill selectByUUID(long uuid);

}
