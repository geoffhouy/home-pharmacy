package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

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

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.selectAll();
    }

    public Schedule getScheduleByUUID(long uuid) {
        return scheduleRepository.selectByUUID(uuid);
    }

    public void createSchedule(String times, String startDate, String endDate, long medicationUuid) {
        Schedule schedule = new Schedule(times, startDate, endDate, medicationUuid);
        scheduleRepository.insert(schedule);
    }

    public void editSchedule(Schedule schedule, String times, String startDate, String endDate, long medicationUuid) {
        schedule.setTimes(times);
        schedule.setStartDate(startDate);
        schedule.setEndDate(endDate);
        schedule.setMedicationUuid(medicationUuid);
        scheduleRepository.update(schedule);
    }

    public void deleteSchedule(Schedule schedule) {
        scheduleRepository.delete(schedule);
    }

}
