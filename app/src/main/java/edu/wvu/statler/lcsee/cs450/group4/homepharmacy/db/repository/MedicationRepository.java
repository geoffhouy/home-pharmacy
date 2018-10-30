package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.repository;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.HomePharmacyDatabase;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.dao.MedicationDao;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity.Medication;

/**
 * API for Medication table in HomePharmacyDatabase.
 */
public class MedicationRepository {

    private MedicationDao medicationDao;

    public MedicationRepository(Application application) {
        HomePharmacyDatabase homePharmacyDatabase = HomePharmacyDatabase.getInstance(application);
        medicationDao = homePharmacyDatabase.medicationDao();
    }

    public List<Medication> selectAll() {
        return medicationDao.selectAll();
    }

    public Medication selectByUUID(long uuid) {
        return medicationDao.selectByUUID(uuid);
    }

    public void insert(Medication medication) {
        new InsertAsyncTask(medicationDao).execute(medication);
    }

    private static class InsertAsyncTask extends AsyncTask<Medication, Void, Void> {

        private MedicationDao taskMedicationDao;

        InsertAsyncTask(MedicationDao medicationDao) {
            taskMedicationDao = medicationDao;
        }

        @Override
        protected Void doInBackground(final Medication... params) {
            taskMedicationDao.insert(params[0]);
            return null;
        }

    }

    public void update(Medication medication) {
        new UpdateAsyncTask(medicationDao).execute(medication);
    }

    private static class UpdateAsyncTask extends AsyncTask<Medication, Void, Void> {

        private MedicationDao taskMedicationDao;

        UpdateAsyncTask(MedicationDao medicationDao) {
            taskMedicationDao = medicationDao;
        }

        @Override
        protected Void doInBackground(final Medication... params) {
            taskMedicationDao.update(params[0]);
            return null;
        }

    }

    public void delete(Medication medication) {
        new DeleteAsyncTask(medicationDao).execute(medication);
    }

    private static class DeleteAsyncTask extends AsyncTask<Medication, Void, Void> {

        private MedicationDao taskMedicationDao;

        DeleteAsyncTask(MedicationDao medicationDao) {
            taskMedicationDao = medicationDao;
        }

        @Override
        protected Void doInBackground(final Medication... params) {
            taskMedicationDao.delete(params[0]);
            return null;
        }

    }

}
