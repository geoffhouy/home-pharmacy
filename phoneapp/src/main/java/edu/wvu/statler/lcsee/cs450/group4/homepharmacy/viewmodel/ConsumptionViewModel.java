package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity.Consumption;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.repository.ConsumptionRepository;

public class ConsumptionViewModel extends AndroidViewModel {

    private ConsumptionRepository consumptionRepository;

    public ConsumptionViewModel(Application application) {
        super(application);
        consumptionRepository = new ConsumptionRepository(application);
    }

    public LiveData<List<Consumption>> getAllConsumptions() {
        return consumptionRepository.selectAll();
    }

    public Consumption getConsumptionByUUID(long uuid) {
        return consumptionRepository.selectByUUID(uuid);
    }

    public void createConsumption(long timestamp, String pillName, String userName, boolean consumed) {
        Consumption consumption = new Consumption(timestamp, pillName, userName, consumed);
        consumptionRepository.insert(consumption);
    }

    public void editConsumption(Consumption consumption, long timestamp, String pillName, String userName, boolean consumed) {
        consumption.setTimestamp(timestamp);
        consumption.setPillName(pillName);
        consumption.setUserName(userName);
        consumption.setConsumed(consumed);
        consumptionRepository.update(consumption);
    }

    public void deleteConsumption(Consumption consumption) {
        consumptionRepository.delete(consumption);
    }

}
