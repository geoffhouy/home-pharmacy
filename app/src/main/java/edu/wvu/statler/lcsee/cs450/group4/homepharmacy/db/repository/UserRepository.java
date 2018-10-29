package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.repository;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.HomePharmacyDatabase;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.dao.UserDao;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity.User;

public class UserRepository {

    private UserDao userDao;

    public UserRepository(Application application) {
        HomePharmacyDatabase homePharmacyDatabase = HomePharmacyDatabase.getInstance(application);
        userDao = homePharmacyDatabase.userDao();
    }

    public List<User> getAllUsers() {
        return userDao.selectAll();
    }

    public User getUserByUUID(long uuid) {
        return userDao.selectByUUID(uuid);
    }

    public void insert(User user) {
        new insertAsyncTask(userDao).execute(user);
    }

    private static class insertAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao taskUserDao;

        insertAsyncTask(UserDao userDao) {
            taskUserDao = userDao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            taskUserDao.insert(params[0]);
            return null;
        }

    }

    public void update(User user) {
        new updateAsyncTask(userDao).execute(user);
    }

    private static class updateAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao taskUserDao;

        updateAsyncTask(UserDao userDao) {
            taskUserDao = userDao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            taskUserDao.update(params[0]);
            return null;
        }

    }

    public void delete(User user) {
        new deleteAsyncTask(userDao).execute(user);
    }

    private static class deleteAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao taskUserDao;

        deleteAsyncTask(UserDao userDao) {
            taskUserDao = userDao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            taskUserDao.delete(params[0]);
            return null;
        }

    }

}
