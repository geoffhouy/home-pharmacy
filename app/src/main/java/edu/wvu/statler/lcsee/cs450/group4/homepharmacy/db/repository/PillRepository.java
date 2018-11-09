package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.HomePharmacyDatabase;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.dao.PillDao;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity.Pill;

/**
 * API for Pill table in HomePharmacyDatabase.
 */
public class PillRepository {

    private PillDao pillDao;

    public PillRepository(Application application) {
        HomePharmacyDatabase homePharmacyDatabase = HomePharmacyDatabase.getInstance(application);
        pillDao = homePharmacyDatabase.pillDao();
    }

    public LiveData<List<Pill>> selectAll() {
        return pillDao.selectAll();
    }

    public Pill selectByUUID(long uuid) {
        return pillDao.selectByUUID(uuid);
    }

    public void insert(Pill pill) {
        new InsertAsyncTask(pillDao).execute(pill);
    }

    private static class InsertAsyncTask extends AsyncTask<Pill, Void, Void> {

        private PillDao taskPillDao;

        InsertAsyncTask(PillDao pillDao) {
            taskPillDao = pillDao;
        }

        @Override
        protected Void doInBackground(final Pill... params) {
            taskPillDao.insert(params[0]);
            return null;
        }

    }

    public void update(Pill pill) {
        new UpdateAsyncTask(pillDao).execute(pill);
    }

    private static class UpdateAsyncTask extends AsyncTask<Pill, Void, Void> {

        private PillDao taskPillDao;

        UpdateAsyncTask(PillDao pillDao) {
            taskPillDao = pillDao;
        }

        @Override
        protected Void doInBackground(final Pill... params) {
            taskPillDao.update(params[0]);
            return null;
        }

    }

    public void delete(Pill pill) {
        new DeleteAsyncTask(pillDao).execute(pill);
    }

    private static class DeleteAsyncTask extends AsyncTask<Pill, Void, Void> {

        private PillDao taskPillDao;

        DeleteAsyncTask(PillDao pillDao) {
            taskPillDao = pillDao;
        }

        @Override
        protected Void doInBackground(final Pill... params) {
            taskPillDao.delete(params[0]);
            return null;
        }

    }

}
