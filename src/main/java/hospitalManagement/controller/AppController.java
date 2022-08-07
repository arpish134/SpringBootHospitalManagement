package hospitalManagement.controller;

import java.util.List;
import java.util.Optional;

import hospitalManagement.entity.Patient;
import hospitalManagement.repository.PatientRepository;
import hospitalManagement.repository.UserRepository;
import hospitalManagement.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepo.save(user);

        return "register_success";
    }

    @GetMapping("/admit_patient")
    public String showAdmitPatientPage(Model model) {
        model.addAttribute("patient", new Patient());

        return "admit";
    }

    @GetMapping("/admit_patient_details")
    public String showAdmitPatientDetails(Model model) {
        model.addAttribute("patient", new Patient());
        List<Patient> listPatients = patientRepository.getAdmittedPatient();
        model.addAttribute("listPatients", listPatients);
        return "admit_details";
    }

    @GetMapping("/discharge_patient_details")
    public String showDischargePatientDetails(Model model) {
        model.addAttribute("patient", new Patient());
        List<Patient> listPatients = patientRepository.getDischargedPatient();
        model.addAttribute("listPatients", listPatients);
        return "discharge_details";
    }

    @PostMapping("/process_admit")
    public String processAdmit(Patient patient) {


        patientRepository.save(patient);

        return "success_admit";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        return "home_page";
    }

    @GetMapping("/update_patient/{patientId}")
    public String listUserById(Model model, @PathVariable long patientId) {

        Optional<Patient> patient = patientRepository.findById(patientId);
        if (patient.isPresent()) {
            model.addAttribute("patient", patient.get());
        }
        return "patient";
    }

    @PostMapping("/update_patient/{patientId}")
    public String updatePatient(@PathVariable long patientId, Patient patient) {
        patient.setPatientId(patientId);
        patientRepository.save(patient);

        return "success_update";
    }
}
