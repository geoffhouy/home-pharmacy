package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;
import java.util.List;

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
    public long uuid;

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    /**
     * The time(s) of the schedule.
     */
    @ColumnInfo(name = "times")
    private String times;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * The start date of the schedule.
     */
    @ColumnInfo(name = "start_date")
    private String startDate;

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public static String getTableName() {
        return TABLE_NAME;
    }

    /**
     * The end date of the schedule.
     */
    @ColumnInfo(name = "end_date")
    private String endDate;

    public long getMedicationUuid() {
        return medicationUuid;
    }

    public void setMedicationUuid(long medicationUuid) {
        this.medicationUuid = medicationUuid;
    }

    /**
     * The universally unique identifier of the Medication to take.
     */
    @ColumnInfo(name = "medication_uuid")
    private long medicationUuid;

    public Schedule(String times, String startDate, String endDate, long medicationUuid) {
        this.times = times;
        this.startDate = startDate;
        this.endDate = endDate;
        this.medicationUuid = medicationUuid;
    }

}
