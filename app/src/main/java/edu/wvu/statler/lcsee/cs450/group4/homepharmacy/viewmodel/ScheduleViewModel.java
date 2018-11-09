package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity.Schedule;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.repository.ScheduleRepository;

/**
 * Encapsulates database functions for the Schedule table into an AndroidViewModel.
 */
public class ScheduleViewModel extends AndroidViewModel {

    private ScheduleRepository scheduleRepository;

    public ScheduleViewModel(Application application) {
        super(application);
        scheduleRepository = new ScheduleRepository(application);
    }

    public LiveData<List<Schedule>> getAllSchedules() {
        return scheduleRepository.selectAll();
    }

    public Schedule getScheduleByUUID(long uuid) {
        return scheduleRepository.selectByUUID(uuid);
    }

    public void createSchedule(String name, long timestamp, int numPillsToTake, String pillName, int dispenserNumber, String userName) {
        Schedule schedule = new Schedule(name, timestamp, numPillsToTake, pillName, dispenserNumber, userName);
        scheduleRepository.insert(schedule);
    }

    public void editSchedule(Schedule schedule, String name, long timestamp, int numPillsToTake, String pillName, int dispenserNumber, String userName) {
        schedule.setName(name);
        schedule.setTimestamp(timestamp);
        schedule.setNumPillsToTake(numPillsToTake);
        schedule.setPillName(pillName);
        schedule.setDispenserNumber(dispenserNumber);
        schedule.setUserName(userName);
        scheduleRepository.update(schedule);
    }

    public void deleteSchedule(Schedule schedule) {
        scheduleRepository.delete(schedule);
    }

}
