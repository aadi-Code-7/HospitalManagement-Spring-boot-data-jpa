package HospitalManagement.example.HospitalManagement.Repository;

import HospitalManagement.example.HospitalManagement.Entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}