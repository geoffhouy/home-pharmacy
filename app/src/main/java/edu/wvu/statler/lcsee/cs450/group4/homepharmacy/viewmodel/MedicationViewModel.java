package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import java.util.List;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity.Medication;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.repository.MedicationRepository;

/**
 * Encapsulates database functions for the Medication table into an AndroidViewModel.
 */
public class MedicationViewModel extends AndroidViewModel {

    private MedicationRepository medicationRepository;

    public MedicationViewModel(Application application) {
        super(application);
        medicationRepository = new MedicationRepository(application);
    }

    public List<Medication> getAllMedications() {
        return medicationRepository.selectAll();
    }

    public Medication getMedicationByUUID(long uuid) {
        return medicationRepository.selectByUUID(uuid);
    }

    public void createMedication(String name, String description, int count, long ownerUuid) {
        Medication medication = new Medication(name, description, count, ownerUuid);
        medicationRepository.insert(medication);
    }

    public void editMedication(Medication medication, String name, String description, int count, long ownerUuid) {
        medication.setName(name);
        medication.setDescription(description);
        medication.setCount(count);
        medication.setOwnerUuid(ownerUuid);
        medicationRepository.update(medication);
    }

    public void deleteMedication(Medication medication) {
        medicationRepository.delete(medication);
    }

}
