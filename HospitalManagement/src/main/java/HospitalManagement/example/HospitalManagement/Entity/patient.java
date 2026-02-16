package HospitalManagement.example.HospitalManagement.Entity;

import HospitalManagement.example.HospitalManagement.Entity.Type.BloodGroup;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    //patient to insurance -> one-to-one relation
    @OneToOne (cascade = {CascadeType.ALL}, orphanRemoval = true) //Cascade functions for updating child(insurance) when parent(patient) is updated or save.
    @JoinColumn(name = "patientInsurance_ID") //Owning Side //Have FK patientInsurance_ID
    private Insurance insurance;

    //Inverse side //don't have any attribute like patientAppointment_ID
    @OneToMany(mappedBy = "patient", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Appointment> appointment = new ArrayList<>();


    /*
    Key Points:
        ->The owning side dictates the foreign key updates
        ->Updates on the mapped field on the inverse side cannot update the foreign key.
        ->Parent control lifecycle of others, here if a patient is deleted, their appointment should also be deleted.
          Hence, patient is a parent.
     */
    /*
    If Cascade is CascadeType.PERSIST or ALL, you have added Appointment object to patient.getAppointments() and
    set appointment.setPatient(patient) then,
        ->Saving the patient automatically saves the appointment
        ->Deleting the patient automatically deletes all the appointment(because of REMOVE and orphanRemoval = true )
        ->No need to explicitly save or delete appointment.
     */
}
