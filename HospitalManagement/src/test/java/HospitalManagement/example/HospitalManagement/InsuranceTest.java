package HospitalManagement.example.HospitalManagement;

import HospitalManagement.example.HospitalManagement.Entity.Insurance;
import HospitalManagement.example.HospitalManagement.Entity.patient;
import HospitalManagement.example.HospitalManagement.Service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class InsuranceTest {

    @Autowired
    private InsuranceService insuranceService;

    @Test
    public void testInsurance(){
        Insurance insurance = Insurance.builder()
                .policyNumber("NMB_1234")
                .provider("NMB")
                .validUntil(LocalDate.of(2030,12,30))
                .build();

        patient patient = insuranceService.assignInsuranceToPatient(insurance,1L);
        System.out.println(patient);

        var newPatient = insuranceService.diassociateInsurance(patient.getId());
        System.out.println(newPatient);
    }
}
