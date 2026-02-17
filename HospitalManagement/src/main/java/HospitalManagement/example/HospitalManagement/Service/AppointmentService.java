package HospitalManagement.example.HospitalManagement.Service;

import HospitalManagement.example.HospitalManagement.DTO.AppointmentResponseDto;
import HospitalManagement.example.HospitalManagement.DTO.CreateAppointmentRequestDto;
import HospitalManagement.example.HospitalManagement.Entity.Appointment;
import HospitalManagement.example.HospitalManagement.Entity.Doctor;
import HospitalManagement.example.HospitalManagement.Entity.patient;
import HospitalManagement.example.HospitalManagement.Repository.AppointmentRepository;
import HospitalManagement.example.HospitalManagement.Repository.DoctorRepository;
import HospitalManagement.example.HospitalManagement.Repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.sampled.Line;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    @Autowired
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public AppointmentResponseDto createNewAppointment(CreateAppointmentRequestDto createAppointmentRequestDto){
        Long doctorID = createAppointmentRequestDto.getDoctorId();
        Long patientID = createAppointmentRequestDto.getPatientId();

        Doctor doctor = doctorRepository.findById(doctorID)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found with ID: " + doctorID));
        patient patient = patientRepository.findById(patientID)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with ID: " + patientID));

        Appointment appointment = Appointment.builder()
                .reason(createAppointmentRequestDto.getReason())
                .appointmentTime(createAppointmentRequestDto.getAppointmentTime())
                .build();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        patient.getAppointment().add(appointment); //To maintain bidirectional consistency.

        return modelMapper.map(appointment, AppointmentResponseDto.class);
    }

    @Transactional
    public Appointment reassignAppointment(Long appointmentID, Long doctorID){
        Appointment appointment = appointmentRepository.findById(appointmentID).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorID).orElseThrow();

        appointment.setDoctor(doctor); // this will automatically call the update, because it is dirty

        doctor.getAppointments().add(appointment);

        return appointment;
    }

    public List<AppointmentResponseDto> getAllAppointmentsOfDoctor(Long doctorId){
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        return doctor.getAppointments()
                .stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentResponseDto.class))
                .collect(Collectors.toList());
    }

}
