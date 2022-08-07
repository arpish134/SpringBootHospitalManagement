package hospitalManagement.repository;

import hospitalManagement.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("select p from Patient p where p.status='admitted'")
    List<Patient> getAdmittedPatient();
    @Query("select p from Patient p where p.status='discharged'")
    List<Patient> getDischargedPatient();
}

