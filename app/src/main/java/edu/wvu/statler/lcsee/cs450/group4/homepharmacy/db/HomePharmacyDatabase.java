package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.dao.MedicationDao;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.dao.ScheduleDao;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.dao.UserDao;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entities.Medication;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entities.Schedule;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entities.User;

/**
 * The Room database for Home Pharmacy.
 */
@Database(entities = {User.class, Medication.class, Schedule.class}, version = 1)
public abstract class HomePharmacyDatabase extends RoomDatabase {

    /**
     * @return The DAO for the User table.
     */
    public abstract UserDao userDao();

    /**
     * @return The DAO for the Medication table.
     */
    public abstract MedicationDao medicationDao();

    /**
     * @return The DAO for the Schedule table.
     */
    public abstract ScheduleDao scheduleDao();

    /**
     * The only instance of this database.
     */
    private static volatile HomePharmacyDatabase INSTANCE;

    /**
     * Gets the singleton instance of HomePharmacyDatabase.
     *
     * @param context The context.
     * @return The singleton instance of HomePharmacyDatabase.
     */
    public static HomePharmacyDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (HomePharmacyDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room
                            .databaseBuilder(context.getApplicationContext(), HomePharmacyDatabase.class, "home-pharmacy")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
