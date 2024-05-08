package com.springRest.service;

import com.springRest.DAO.DoctorRepository;
import com.springRest.enitity.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService
{
    private DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository)
    {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> getAllDoctors()
    {
        List<Doctor> doctorList = doctorRepository.findAll();
        return doctorList;
    }

    public void save(Doctor doctor)
    {
        doctorRepository.save(doctor);
    }

    public Doctor findById(int id)
    {
        Doctor newDoctor =null;
        Optional<Doctor> patient = doctorRepository.findById(id);
        if(patient.isPresent())
        {
            newDoctor = patient.get();
        }
        return newDoctor;
    }


    public void deleteById(int id)
    {
        Doctor doctor = this.findById(id);
        doctorRepository.delete(doctor);
        //doctorRepository.deleteById(id);
    }

}
