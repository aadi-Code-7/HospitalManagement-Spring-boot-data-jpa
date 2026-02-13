package HospitalManagement.example.HospitalManagement.DTO;

import HospitalManagement.example.HospitalManagement.Entity.Type.BloodGroup;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BloodGroupCountResponseEntity {

    private BloodGroup bloodGroup;
    private Long count;
}
