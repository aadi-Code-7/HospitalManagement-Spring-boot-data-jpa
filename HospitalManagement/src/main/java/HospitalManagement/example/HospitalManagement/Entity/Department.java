package HospitalManagement.example.HospitalManagement.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @ManyToOne
    private Doctor headDoctor;

    @ManyToMany
    //Hibernate does it automatically, but below is a manual way
    @JoinTable(
            name = "myDept_Doctors",
            joinColumns = @JoinColumn(name = "dept-ID"),
            inverseJoinColumns = @JoinColumn(name = "doctor_ID")
    )
    private Set<Doctor> doctors = new HashSet<>(); //Owning side
}
