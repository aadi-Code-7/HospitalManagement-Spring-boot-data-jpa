package HospitalManagement.example.HospitalManagement;

import HospitalManagement.example.HospitalManagement.DTO.BloodGroupCountResponseEntity;
import HospitalManagement.example.HospitalManagement.Entity.Type.BloodGroup;
import HospitalManagement.example.HospitalManagement.Entity.patient;
import HospitalManagement.example.HospitalManagement.Repository.PatientRepository;
import HospitalManagement.example.HospitalManagement.Service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class PatientTests {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @Test
    public void patientTest(){
        List<patient> list = patientRepository.findAll();
        System.out.println(list);
    }

    //We can't perform operation in Test class, so we made Service class separately.
    @Test
    public void testTransactionMethods(){
//        patient p = patientService.getByPatientID(2L);

        //hibernate created this query(where p1_0.name=?) using method findByName.
        //Spring automatically generates the query from the method name.
//        patient p = patientRepository.findByName("Aditya Sharma");
//        System.out.println(p);

        //hibernate created query (where p1_0.birth_date=? or p1_0.email=?) using method findByBirthDateOrEmail
        //Spring automatically generates the query from the method name.
        List<patient> patientsList = patientRepository.findByBirthDateOrEmail(LocalDate.of(1983,2,3),
                "ss.samir@gmail.com");
        for (patient patient : patientsList){
            System.out.println(patient);
        }

        //Custom query
        //As the queries themselves are tied to the Java method that runs them,
        //you can actually bind them directly by using the Spring Data JPA @Query annotation
        //rather than annotating them to the domain class.
        //This frees the domain class from persistence specific information and co-locates the query to the repository interface.
        List<patient> patientList = patientRepository.findByBloodGroup(BloodGroup.A_Positive);
        for (patient p : patientList){
            System.out.println(p);
        }

        //custom query for find patient born after the following date
        List<patient> patientList1 = patientRepository.findByBornAfterDate(LocalDate.of(2000,1,1));
        for (patient p : patientList1){
            System.out.println(p);
        }

        //Query to count frequency of all bloodGroup
        List<Object[]> patientList2 = patientRepository.countByBloodGroup();//Not ideal to use object array instead use projection.
        for (Object[] p : patientList2){
            System.out.println(p[0] + " : " + p[1]);
        }

        //query that gives all patient.
        List<patient> patientList3 = patientRepository.findAllPatient();
        for (patient p : patientList3){
            System.out.println(p);
        }

        //query to update name
        int update = patientRepository.updateNameWithId("Aaditya Sharma", 1L);
        System.out.println(update);

        //query to frequency of bloodGroup using projection
        //projections allow you to retrieve a subset of data from a database query, rather than fetching entire entity objects
        //Reduces the amount of data fetched from the database, leading to faster query execution and reduced network traffic.
        List<BloodGroupCountResponseEntity> bloodGroupList = patientRepository.countEachBloodGroup();
        for (BloodGroupCountResponseEntity bloodGroupCount : bloodGroupList){
            System.out.println(bloodGroupCount);
        }

        //Pagination's
        //Pagination in JPA queries is used to retrieve large datasets in smaller, manageable chunks,
        // rather than fetching all records at once.
        Page<patient> patients = patientRepository.findAllPatient(PageRequest.of(1, 3, Sort.by("name")));
        for (patient p : patients){
            System.out.println(p);
        }
    }
}
