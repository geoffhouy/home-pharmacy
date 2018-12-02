package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.HomePharmacyDatabase;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.dao.UserDao;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity.User;

/**
 * API for User table in HomePharmacyDatabase.
 */
public class UserRepository {

    private UserDao userDao;

    public UserRepository(Application application) {
        HomePharmacyDatabase homePharmacyDatabase = HomePharmacyDatabase.getInstance(application);
        userDao = homePharmacyDatabase.userDao();
    }

    public LiveData<List<User>> selectAll() {
        return userDao.selectAll();
    }

    public User selectByUUID(long uuid) {
        return userDao.selectByUUID(uuid);
    }

    public void insert(User user) {
        new InsertAsyncTask(userDao).execute(user);
    }

    private static class InsertAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao taskUserDao;

        InsertAsyncTask(UserDao userDao) {
            taskUserDao = userDao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            taskUserDao.insert(params[0]);
            return null;
        }

    }

    public void update(User user) {
        new UpdateAsyncTask(userDao).execute(user);
    }

    private static class UpdateAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao taskUserDao;

        UpdateAsyncTask(UserDao userDao) {
            taskUserDao = userDao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            taskUserDao.update(params[0]);
            return null;
        }

    }

    public void delete(User user) {
        new DeleteAsyncTask(userDao).execute(user);
    }

    private static class DeleteAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao taskUserDao;

        DeleteAsyncTask(UserDao userDao) {
            taskUserDao = userDao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            taskUserDao.delete(params[0]);
            return null;
        }

    }

}
