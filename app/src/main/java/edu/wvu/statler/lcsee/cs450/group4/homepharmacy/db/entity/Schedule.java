package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Represents one record of the Schedule table.
 */
@Entity(tableName = Schedule.TABLE_NAME)
public class Schedule {

    public static final String TABLE_NAME = "schedules";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uuid", index = true)
    public long uuid;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "timestamp")
    private long timestamp;

    @ColumnInfo(name = "num_pills")
    private int numPillsToTake;

    @ColumnInfo(name = "pill_name")
    private String pillName;

    @ColumnInfo(name = "dispenser")
    private int dispenserNumber;

    @ColumnInfo(name = "user_name")
    private String userName;

    @ColumnInfo(name = "interval")
    private long interval;

    public Schedule(String name, long timestamp, int numPillsToTake, String pillName, int dispenserNumber, String userName, long interval) {
        this.name = name;
        this.timestamp = timestamp;
        this.numPillsToTake = numPillsToTake;
        this.pillName = pillName;
        this.dispenserNumber = dispenserNumber;
        this.userName = userName;
        this.interval = interval;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getNumPillsToTake() {
        return numPillsToTake;
    }

    public void setNumPillsToTake(int numPillsToTake) {
        this.numPillsToTake = numPillsToTake;
    }

    public String getPillName() {
        return pillName;
    }

    public void setPillName(String pillName) {
        this.pillName = pillName;
    }

    public int getDispenserNumber() {
        return dispenserNumber;
    }

    public void setDispenserNumber(int dispenserNumber) {
        this.dispenserNumber = dispenserNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

}
