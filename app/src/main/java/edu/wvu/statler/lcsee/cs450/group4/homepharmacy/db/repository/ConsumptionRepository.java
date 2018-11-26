package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.HomePharmacyDatabase;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.dao.ConsumptionDao;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity.Consumption;

/**
 * API for Consumpion table in HomePharmacyDatabase.
 */
public class ConsumptionRepository {

    private ConsumptionDao consumptionDao;

    public ConsumptionRepository(Application application) {
        HomePharmacyDatabase homePharmacyDatabase = HomePharmacyDatabase.getInstance(application);
        consumptionDao = homePharmacyDatabase.consumptionDao();
    }

    public LiveData<List<Consumption>> selectAll() {
        return consumptionDao.selectAll();
    }

    public Consumption selectByUUID(long uuid) {
        return consumptionDao.selectByUUID(uuid);
    }

    public void insert(Consumption consumption) {
        new InsertAsyncTask(consumptionDao).execute(consumption);
    }

    private static class InsertAsyncTask extends AsyncTask<Consumption, Void, Void> {

        private ConsumptionDao taskConsumptionDao;

        InsertAsyncTask(ConsumptionDao consumptionDao) {
            taskConsumptionDao = consumptionDao;
        }

        @Override
        protected Void doInBackground(final Consumption... params) {
            taskConsumptionDao.insert(params[0]);
            return null;
        }

    }

    public void update(Consumption consumption) {
        new UpdateAsyncTask(consumptionDao).execute(consumption);
    }

    private static class UpdateAsyncTask extends AsyncTask<Consumption, Void, Void> {

        private ConsumptionDao taskConsumptionDao;

        UpdateAsyncTask(ConsumptionDao consumptionDao) {
            taskConsumptionDao = consumptionDao;
        }

        @Override
        protected Void doInBackground(final Consumption... params) {
            taskConsumptionDao.update(params[0]);
            return null;
        }

    }

    public void delete(Consumption consumption) {
        new DeleteAsyncTask(consumptionDao).execute(consumption);
    }

    private static class DeleteAsyncTask extends AsyncTask<Consumption, Void, Void> {

        private ConsumptionDao taskConsumptionDao;

        DeleteAsyncTask(ConsumptionDao consumptionDao) {
            taskConsumptionDao = consumptionDao;
        }

        @Override
        protected Void doInBackground(final Consumption... params) {
            taskConsumptionDao.delete(params[0]);
            return null;
        }

    }

}
