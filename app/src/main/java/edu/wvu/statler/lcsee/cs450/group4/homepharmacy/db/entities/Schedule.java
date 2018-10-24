package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Represents one record of the Schedule table.
 */
@Entity(tableName = Schedule.TABLE_NAME)
public class Schedule {

    /**
     * The name of the Schedule table.
     */
    public static final String TABLE_NAME = "schedules";

    /**
     * The universally unique identifier of the schedule.
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uuid", index = true)
    private long uuid;

    public Schedule() {

    }

    public long getUUID() {
        return this.uuid;
    }

}
