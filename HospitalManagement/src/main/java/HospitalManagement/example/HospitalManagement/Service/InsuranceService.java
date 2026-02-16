package HospitalManagement.example.HospitalManagement.Service;

import HospitalManagement.example.HospitalManagement.Entity.Insurance;
import HospitalManagement.example.HospitalManagement.Entity.patient;
import HospitalManagement.example.HospitalManagement.Repository.InsuranceRepository;
import HospitalManagement.example.HospitalManagement.Repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public patient assignInsuranceToPatient(Insurance insurance, Long patientID){
        patient patient = patientRepository.findById(patientID)
                .orElseThrow(()->new EntityNotFoundException("Patient not found with id : " + patientID));

        patient.setInsurance(insurance);
        insurance.setPatient(patient); //maintaining bidirectional consistency

        return patient;
    }

    @Transactional
    public patient diassociateInsurance(Long patientID){
        patient patient = patientRepository.findById(patientID)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id : " + patientID));

        patient.setInsurance(null);

        return patient;
    }
}
