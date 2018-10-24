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
    private long uuid;

    /**
     * The time(s) of the schedule.
     */
    @ColumnInfo(name = "times")
    private List<String> times;

    /**
     * The start date of the schedule.
     */
    @ColumnInfo(name = "start_date")
    private Date startDate;

    /**
     * The end date of the schedule.
     */
    @ColumnInfo(name = "end_date")
    private Date endDate;

    /**
     * The universally unique identifier of the Medication to take.
     */
    @ColumnInfo(name = "medication_uuid")
    private long medicationUuid;

    public Schedule(List<String> times, Date startDate, Date endDate, long medicationUuid) {
        this.times = times;
        this.startDate = startDate;
        this.endDate = endDate;
        this.medicationUuid = medicationUuid;
    }

    public long getUUID() {
        return this.uuid;
    }

    public List<String> getTimes() {
        return this.times;
    }

    public void setTimes(List<String> times) {
        this.times = times;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public long getMedicationUUID() {
        return this.medicationUuid;
    }

    public void setMedicationUUID(long medicationUuid) {
        this.medicationUuid = medicationUuid;
    }

}
