package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Represents one record of the User table.
 */
@Entity(tableName = User.TABLE_NAME)
public class User {

    /**
     * The name of the User table.
     */
    public static final String TABLE_NAME = "users";

    /**
     * The universally unique identifier of the user.
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uuid", index = true)
    private long uuid;

    /**
     * The name of the user.
     */
    @ColumnInfo(name = "name")
    private String name;

    /**
     * The PIN of the user.
     */
    @ColumnInfo(name = "pin")
    private String pin;

    public User(String name, String pin) {
        this.name = name;
        this.pin = pin;
    }

    public long getUUID() {
        return this.uuid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPIN() {
        return this.pin;
    }

    public void setPIN(String pin) {
        this.pin = pin;
    }

}
