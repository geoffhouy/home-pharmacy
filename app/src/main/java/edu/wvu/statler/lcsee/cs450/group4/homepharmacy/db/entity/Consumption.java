package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Represents one record of the Consumption table.
 */
@Entity(tableName = Consumption.TABLE_NAME)
public class Consumption {

    public static final String TABLE_NAME = "consumptions";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uuid", index = true)
    public long uuid;

    @ColumnInfo(name = "timestamp")
    private long timestamp;

    @ColumnInfo(name = "pill_name")
    private String pillName;

    @ColumnInfo(name = "user_name")
    private String userName;

    @ColumnInfo(name = "consumed")
    private boolean consumed;

    public Consumption(long timestamp, String pillName, String userName, boolean consumed) {
        this.timestamp = timestamp;
        this.pillName = pillName;
        this.userName = userName;
        this.consumed = consumed;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getPillName() {
        return pillName;
    }

    public void setPillName(String pillName) {
        this.pillName = pillName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isConsumed() {
        return consumed;
    }

    public void setConsumed(boolean consumed) {
        this.consumed = consumed;
    }

}
