package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.HomePharmacyDatabase;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.dao.ScheduleDao;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity.Schedule;

/**
 * API for Schedule table in HomePharmacyDatabase.
 */
public class ScheduleRepository {

    private ScheduleDao scheduleDao;

    public ScheduleRepository(Application application) {
        HomePharmacyDatabase homePharmacyDatabase = HomePharmacyDatabase.getInstance(application);
        scheduleDao = homePharmacyDatabase.scheduleDao();
    }

    public LiveData<List<Schedule>> selectAll() {
        return scheduleDao.selectAll();
    }

    public Schedule selectByUUID(long uuid) {
        return scheduleDao.selectByUUID(uuid);
    }

    public void insert(Schedule schedule) {
        new InsertAsyncTask(scheduleDao).execute(schedule);
    }

    private static class InsertAsyncTask extends AsyncTask<Schedule, Void, Void> {

        private ScheduleDao taskScheduleDao;

        InsertAsyncTask(ScheduleDao scheduleDao) {
            taskScheduleDao = scheduleDao;
        }

        @Override
        protected Void doInBackground(final Schedule... params) {
            taskScheduleDao.insert(params[0]);
            return null;
        }

    }

    public void update(Schedule schedule) {
        new UpdateAsyncTask(scheduleDao).execute(schedule);
    }

    private static class UpdateAsyncTask extends AsyncTask<Schedule, Void, Void> {

        private ScheduleDao taskScheduleDao;

        UpdateAsyncTask(ScheduleDao scheduleDao) {
            taskScheduleDao = scheduleDao;
        }

        @Override
        protected Void doInBackground(final Schedule... params) {
            taskScheduleDao.update(params[0]);
            return null;
        }

    }

    public void delete(Schedule schedule) {
        new DeleteAsyncTask(scheduleDao).execute(schedule);
    }

    private static class DeleteAsyncTask extends AsyncTask<Schedule, Void, Void> {

        private ScheduleDao taskScheduleDao;

        DeleteAsyncTask(ScheduleDao scheduleDao) {
            taskScheduleDao = scheduleDao;
        }

        @Override
        protected Void doInBackground(final Schedule... params) {
            taskScheduleDao.delete(params[0]);
            return null;
        }

    }

}
