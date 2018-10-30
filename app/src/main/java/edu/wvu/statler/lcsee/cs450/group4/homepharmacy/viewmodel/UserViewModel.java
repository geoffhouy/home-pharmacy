package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity.User;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.repository.UserRepository;

/**
 * Encapsulates database functions for the User table into an AndroidViewModel.
 */
public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;

    public UserViewModel(Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public LiveData<List<User>> getAllUsers() {
        return userRepository.selectAll();
    }

    public User getUserByUUID(long uuid) {
        return userRepository.selectByUUID(uuid);
    }

    public void createUser(String name, String pin) {
        User user = new User(name, pin);
        userRepository.insert(user);
    }

    public void editUser(User user, String name, String pin) {
        user.setName(name);
        user.setPin(pin);
        userRepository.update(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

}
