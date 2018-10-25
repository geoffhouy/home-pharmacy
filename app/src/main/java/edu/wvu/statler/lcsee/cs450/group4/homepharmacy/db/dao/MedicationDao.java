package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.dao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity.Medication;

/**
 * Data Access Object for Medication.
 */
public interface MedicationDao {

    /**
     * Inserts a new Medication into the table.
     *
     * @param medication The new Medication.
     * @return The row ID of the newly inserted Medication.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Medication medication);

    /**
     * Updates a Medication in the table.
     *
     * @param medication The Medication to update.
     * @return The number of Medications updated. This should be {@code 1}.
     */
    @Update
    int update(Medication medication);

    /**
     * Deletes a Medication from the table.
     *
     * @param medication The Medication to delete.
     * @return The number of Medications deleted. This should be {@code 1}.
     */
    @Delete
    int delete(Medication medication);

    /**
     * Selects all Medications in the table.
     *
     * @return A List of all Medications in the table.
     */
    @Query("SELECT * FROM " + Medication.TABLE_NAME)
    List<Medication> selectAll();

    /**
     * Selects a Medication by UUID.
     *
     * @param uuid The universally unique identifier of the Medication.
     * @return The selected Medication.
     */
    @Query("SELECT * FROM " + Medication.TABLE_NAME + " WHERE uuid = :uuid")
    Medication selectByUUID(long uuid);

}
