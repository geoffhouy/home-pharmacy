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
    private long uuid;

    /**
     * The name of the medication.
     */
    @ColumnInfo(name = "name")
    private String name;

    /**
     * The description of the medication.
     */
    @ColumnInfo(name = "description")
    private String description;

    /**
     * The remaining amount of medication.
     */
    @ColumnInfo(name = "count")
    private int count;

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

    public long getUUID() {
        return this.uuid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getOwnerUUID() {
        return ownerUuid;
    }

    public void setOwnerUUID(long ownerUuid) {
        this.ownerUuid = ownerUuid;
    }

}
