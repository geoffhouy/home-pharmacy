package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Represents one record of the Medication table.
 */
@Entity(tableName = Medication.TABLE_NAME)
public class Medication {

    /**
     * The name of the Medication table.
     */
    public static final String TABLE_NAME = "medications";

    /**
     * The universally unique identifier of the medication.
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uuid", index = true)
    public long uuid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * The name of the medication.
     */
    @ColumnInfo(name = "name")
    private String name;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * The description of the medication.
     */
    @ColumnInfo(name = "description")
    private String description;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /**
     * The remaining amount of medication.
     */
    @ColumnInfo(name = "count")
    private int count;

    public long getOwnerUuid() {
        return ownerUuid;
    }

    public void setOwnerUuid(long ownerUuid) {
        this.ownerUuid = ownerUuid;
    }

    /**
     * The universally unique identifier of the User who created the medication.
     */
    @ColumnInfo(name = "owner_uuid")
    private long ownerUuid;

    public Medication(String name, String description, int count, long ownerUuid) {
        this.name = name;
        this.description = description;
        this.count = count;
        this.ownerUuid = ownerUuid;
    }

}
