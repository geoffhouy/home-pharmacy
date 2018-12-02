package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Represents one record of the Pill table.
 */
@Entity(tableName = Pill.TABLE_NAME)
public class Pill {

    public static final String TABLE_NAME = "pills";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uuid", index = true)
    public long uuid;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "count")
    private int count;

    @ColumnInfo(name = "dispenser")
    private int dispenserNumber;

    @ColumnInfo(name = "owner_uuid")
    private long ownerUuid;

    public Pill(String name, String description, int count, long ownerUuid, int dispenserNumber) {
        this.name = name;
        this.description = description;
        this.count = count;
        this.ownerUuid = ownerUuid;
        this.dispenserNumber = dispenserNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getOwnerUuid() {
        return ownerUuid;
    }

    public void setOwnerUuid(long ownerUuid) {
        this.ownerUuid = ownerUuid;
    }

    public int getDispenserNumber() {
        return dispenserNumber;
    }

    public void setDispenserNumber(int dispenserNumber) {
        this.dispenserNumber = dispenserNumber;
    }

}
