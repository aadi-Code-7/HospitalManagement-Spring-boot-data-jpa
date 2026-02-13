package HospitalManagement.example.HospitalManagement.Repository;

import HospitalManagement.example.HospitalManagement.DTO.BloodGroupCountResponseEntity;
import HospitalManagement.example.HospitalManagement.Entity.Type.BloodGroup;
import HospitalManagement.example.HospitalManagement.Entity.patient;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PatientRepository extends JpaRepository<patient,Long> {

    //Jpa query Method(findByName) -> followed by camel case.
    patient findByName(String name);

    //jpa query method that find patient by birthDate or email.
    List<patient> findByBirthDateOrEmail(LocalDate birthDate, String email);

    //Custom query
    @Query("SELECT p FROM patient p where p.bloodGroup = ?1")
    List<patient> findByBloodGroup(@Param("bloodGroup") BloodGroup bloodGroup);

    @Query("select p from patient p where p.birthDate > :birthDate") //use ":" before using variable(param) instead of ?2,?3...
    List<patient> findByBornAfterDate(@Param("birthDate") LocalDate birthDate);

    @Query("select p.bloodGroup, count(p) from patient p group by p.bloodGroup")
    List<Object[]> countByBloodGroup();

    @Query(value = "select * from patient", nativeQuery = true)//this query doesn't need hibernate to convert to raw SQL
                                                               //it is nativeQuery ... byDefault nativeQuery is false.
    List<patient> findAllPatient();


    @Transactional
    @Modifying
    @Query("update patient p set p.name = :name where p.id = :id")
    int updateNameWithId(@Param("name") String name, @Param("id") Long id);

    //using project to map the object instead using object array before.
    @Query("select new HospitalManagement.example.HospitalManagement.DTO.BloodGroupCountResponseEntity(p.bloodGroup, count(p))" +
            " from patient p group by p.bloodGroup")
//    List<Object[]> countByBloodGroup();
    List<BloodGroupCountResponseEntity> countEachBloodGroup();

    @Query(value = "select * from patient", nativeQuery = true)
    Page<patient> findAllPatient(Pageable pageable);
}
