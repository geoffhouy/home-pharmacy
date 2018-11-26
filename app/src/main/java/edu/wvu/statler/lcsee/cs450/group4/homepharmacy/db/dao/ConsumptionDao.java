package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity.Consumption;

/**
 * Data Access Object for Consumption.
 */
@Dao
public interface ConsumptionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Consumption consumption);

    @Update
    int update(Consumption consumption);

    @Delete
    int delete(Consumption consumption);

    @Query("DELETE FROM " + Consumption.TABLE_NAME)
    void deleteAll();

    @Query("SELECT * FROM " + Consumption.TABLE_NAME)
    LiveData<List<Consumption>> selectAll();

    @Query("SELECT * FROM " + Consumption.TABLE_NAME + " WHERE uuid = :uuid")
    Consumption selectByUUID(long uuid);

}
