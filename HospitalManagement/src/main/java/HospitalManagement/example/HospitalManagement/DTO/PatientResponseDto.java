package HospitalManagement.example.HospitalManagement.DTO;

import HospitalManagement.example.HospitalManagement.Entity.Type.BloodGroup;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientResponseDto {
    private Long id;
    private String name;
    private String gender;
    private LocalDate birthDate;
    private BloodGroup bloodGroup;
}
