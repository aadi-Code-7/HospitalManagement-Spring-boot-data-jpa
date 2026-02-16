package HospitalManagement.example.HospitalManagement.Repository;

import HospitalManagement.example.HospitalManagement.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}