package HospitalManagement.example.HospitalManagement.Entity;

import HospitalManagement.example.HospitalManagement.Entity.Type.BloodGroup;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@ToString //for getting data...
@Setter
@Getter

//Whenever we rename table it doesn't actually rename but create a new table instead.
//UniqueConstraint applied for uniqueness.
//Indexes make data retrieval faster but slow down inserting.
//for actual renaming uses Migration Tool
@Table(
        name = "patient", //renaming / created new table Patient_tlb
        uniqueConstraints = {
//                @UniqueConstraint(name = "unique_patient_email", columnNames = {"email"}),
                @UniqueConstraint(name = "unique_patient_name_email", columnNames = {"name", "email"})
        },
        indexes = {
                @Index(name = "unique_patient_name_birthDate", columnList = "birthDate")
        }
)
public class patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "Patient_name", nullable = false, length = 50) //It creates new column Patient_name instead
    @Column(nullable = false, length = 50)
    private String name;

    //@UniqueConstraint(name = "unique_patient_email", columnNames = {"email"})
    //Instead use @Column for single UniqueConstraint...
    @Column(unique = true, nullable = false)
    private String email;

    @Column(updatable = false)
    private LocalDate birthDate;

    private String gender;

    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;
}
