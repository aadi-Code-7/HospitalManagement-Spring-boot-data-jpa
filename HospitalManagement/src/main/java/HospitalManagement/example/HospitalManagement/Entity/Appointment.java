package HospitalManagement.example.HospitalManagement.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private LocalDateTime appointmentTime;

    @Column(length = 500)
    private String reason;

    @ManyToOne
    @JoinColumn(name = "patient_ID", nullable = false) //Owning side //Have foreign key patient_ID
    private patient patient;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Doctor doctor;
}
