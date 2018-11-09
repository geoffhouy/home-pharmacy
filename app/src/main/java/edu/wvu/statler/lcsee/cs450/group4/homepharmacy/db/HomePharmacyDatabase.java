package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.dao.PillDao;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.dao.ScheduleDao;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.dao.UserDao;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity.Pill;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity.Schedule;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity.User;

/**
 * The Room database for Home Pharmacy.
 */
@Database(entities = {User.class, Pill.class, Schedule.class}, exportSchema = false, version = 3)
public abstract class HomePharmacyDatabase extends RoomDatabase {

    /**
     * @return The DAO for the User table.
     */
    public abstract UserDao userDao();

    /**
     * @return The DAO for the Pill table.
     */
    public abstract PillDao pillDao();

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
                            .fallbackToDestructiveMigration()
                            .addCallback(testCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback testCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateAsyncTask(INSTANCE).execute();
        }

    };

    private static class PopulateAsyncTask extends AsyncTask<Void, Void, Void> {

        private final UserDao userDao;
        private final PillDao pillDao;
        private final ScheduleDao scheduleDao;

        PopulateAsyncTask(HomePharmacyDatabase db) {
            userDao = db.userDao();
            pillDao = db.pillDao();
            scheduleDao = db.scheduleDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            userDao.deleteAll();
            userDao.insert(new User("John Doe", "1234"));
            userDao.insert(new User("Jane Doe", "4321"));

            pillDao.deleteAll();
            pillDao.insert(new Pill("Advil", "Use for headaches", 6, 6, 3));
            pillDao.insert(new Pill("Ramen Noodles", "Not a pill", 42, 6, 4));

            scheduleDao.deleteAll();
            scheduleDao.insert(new Schedule("Schedule1", 3842, 3, "Advil", 3, "John Doe"));
            scheduleDao.insert(new Schedule("Schedule2", 3845, 3, "Ramen Noodles", 3, "Jane Doe"));
            return null;
        }
    }

}
