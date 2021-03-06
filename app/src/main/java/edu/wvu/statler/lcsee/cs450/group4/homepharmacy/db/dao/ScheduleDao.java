package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity.Schedule;

/**
 * Data Access Object for Schedule.
 */
@Dao
public interface ScheduleDao {

    /**
     * Inserts a new Schedule into the table.
     *
     * @param schedule The new Schedule.
     * @return The row ID of the newly inserted Schedule.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Schedule schedule);

    /**
     * Updates a Schedule in the table.
     *
     * @param schedule The Schedule to update.
     * @return The number of Schedules updated. This should be {@code 1}.
     */
    @Update
    int update(Schedule schedule);

    /**
     * Deletes a Schedule from the table.
     *
     * @param schedule The Schedule to delete.
     * @return The number of Schedules deleted. This should be {@code 1}.
     */
    @Delete
    int delete(Schedule schedule);

    /**
     * Deletes all Schedules from the table.
     */
    @Query("DELETE FROM " + Schedule.TABLE_NAME)
    void deleteAll();

    /**
     * Selects all Schedules in the table.
     *
     * @return A List of all Schedules in the table.
     */
    @Query("SELECT * FROM " + Schedule.TABLE_NAME)
    LiveData<List<Schedule>> selectAll();

    @Query("SELECT * FROM " + Schedule.TABLE_NAME)
    List<Schedule> selectAllAsList();

    /**
     * Selects a Schedule by UUID.
     *
     * @param uuid The universally unique identifier of the Schedule.
     * @return The selected Schedule.
     */
    @Query("SELECT * FROM " + Schedule.TABLE_NAME + " WHERE uuid = :uuid")
    Schedule selectByUUID(long uuid);

}
