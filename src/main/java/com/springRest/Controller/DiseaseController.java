package com.springRest.Controller;

import com.springRest.enitity.Disease;
import com.springRest.enitity.Doctor;
import com.springRest.service.DiseaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Controller
@RequestMapping("/diseases")
public class DiseaseController {

    private DiseaseService diseaseService;

    public DiseaseController(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
    }

    @GetMapping("/list")
    public String listDoctors(Model theModel) {
        theModel.addAttribute("diseaseList", diseaseService.getAllDiseases());
        return "disease/list-disease";
    }

    @GetMapping("/addDisease")
    public String getDoctorForm(Model model) {
        Disease disease = new Disease();
        model.addAttribute("disease", disease);
        return "disease/addDisease";
    }

    @PostMapping("/save")
    public String saveDoctor(@ModelAttribute("disease") Disease theDoctor) {
        diseaseService.save(theDoctor);
        return "redirect:/diseases/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showUpdateForm(@RequestParam("diseaseId") int theID, Model model) {
        model.addAttribute("disease", diseaseService.findById(theID));
        return "disease/addDisease";
    }

    @Autowired
    @PersistenceContext
    private EntityManager em;

    @GetMapping("/delete")
    @Transactional
    public String deleteDoctor(@RequestParam("diseaseId") int theID) {
        Disease a = em.find(Disease.class, theID);
        for (Doctor b : a.getDoctors()) {
            if (b.getDisease() != null) {
                em.remove(a);
            }
        }
        em.remove(a);
        return "redirect:/diseases/list";
    }
}
