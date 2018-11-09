package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Represents one record of the User table.
 */
@Entity(tableName = User.TABLE_NAME)
public class User {

    public static final String TABLE_NAME = "users";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uuid", index = true)
    public long uuid;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "pin")
    private String pin;

    public User(String name, String pin) {
        this.name = name;
        this.pin = pin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

}
