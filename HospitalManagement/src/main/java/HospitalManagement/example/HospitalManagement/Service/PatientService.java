package HospitalManagement.example.HospitalManagement.Service;

import HospitalManagement.example.HospitalManagement.DTO.PatientResponseDto;
import HospitalManagement.example.HospitalManagement.Entity.patient;
import HospitalManagement.example.HospitalManagement.Repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

//    @Transactional//It denotes transaction start from here, if no error found data commited to database else entirely get rollback.
//    public patient getByPatientID(Long id){
//        patient p1 = patientRepository.findById(id).orElseThrow(); //save in persistenceContext
//
//        patient p2 = patientRepository.findById(id).orElseThrow(); //first find in persistenceContext, if found, then return result.
//        System.out.println(p1==p2); //True , p1 and p2 become the same object (@Transactional Power)
//
//        //Now, we don't need to save it (patientRepository.save(p1)) to change the below field(name).
//        //Persistent will dirty check before commit if any needs to be changed or .....
//        //(Power of @Transactional)
//        p1.setName("Trump");
//        return p1;
//    }

    @Transactional
    public PatientResponseDto getPatientById(Long patientId) {
        patient patient = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException("Patient Not " +
                "Found with id: " + patientId));
        return modelMapper.map(patient, PatientResponseDto.class);
    }

    public List<PatientResponseDto> getAllPatients(Integer pageNumber, Integer pageSize){
        return patientRepository.findAllPatient(PageRequest.of(pageNumber,pageSize))
                .stream()
                .map(patient -> modelMapper.map(patient, PatientResponseDto.class))
                .collect(Collectors.toList());
    }
}
