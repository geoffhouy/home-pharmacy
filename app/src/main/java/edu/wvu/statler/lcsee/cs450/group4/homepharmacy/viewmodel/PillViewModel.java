package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity.Pill;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.repository.PillRepository;

/**
 * Encapsulates database functions for the Pill table into an AndroidViewModel.
 */
public class PillViewModel extends AndroidViewModel {

    private PillRepository pillRepository;

    public PillViewModel(Application application) {
        super(application);
        pillRepository = new PillRepository(application);
    }

    public LiveData<List<Pill>> getAllPills() {
        return pillRepository.selectAll();
    }

    public Pill getPillByUUID(long uuid) {
        return pillRepository.selectByUUID(uuid);
    }

    public void createPill(String name, String description, int count, long ownerUuid, int dispenserNumber) {
        Pill pill = new Pill(name, description, count, ownerUuid, dispenserNumber);
        pillRepository.insert(pill);
    }

    public void editPill(Pill pill, String name, String description, int count, long ownerUuid, int dispenserNumber) {
        pill.setName(name);
        pill.setDescription(description);
        pill.setCount(count);
        pill.setOwnerUuid(ownerUuid);
        pill.setDispenserNumber(dispenserNumber);
        pillRepository.update(pill);
    }

    public void deletePill(Pill pill) {
        pillRepository.delete(pill);
    }

}
