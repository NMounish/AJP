package com.springRest.service;

import com.springRest.DAO.PatientRepository;
import com.springRest.enitity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient findById(int id) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        return patientOptional.orElse(null);
    }

    public void deleteById(int id) {
        patientRepository.deleteById(id);
    }

    // Additional methods can be added here as needed
}
