package HospitalManagement.example.HospitalManagement.Service;

import HospitalManagement.example.HospitalManagement.Entity.Appointment;
import HospitalManagement.example.HospitalManagement.Entity.Doctor;
import HospitalManagement.example.HospitalManagement.Entity.patient;
import HospitalManagement.example.HospitalManagement.Repository.AppointmentRepository;
import HospitalManagement.example.HospitalManagement.Repository.DoctorRepository;
import HospitalManagement.example.HospitalManagement.Repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    @Autowired
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Appointment createNewAppointment(Appointment appointment, Long doctorID , Long patientID){
        Doctor doctor = doctorRepository.findById(doctorID).orElseThrow();
        patient patient = patientRepository.findById(patientID).orElseThrow();

        if (appointment.getId() != null)throw  new IllegalArgumentException("Appointment should not have id");

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        patient.getAppointment().add(appointment); //To maintain bidirectional consistency.

        return appointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment reassignAppointment(Long appointmentID, Long doctorID){
        Appointment appointment = appointmentRepository.findById(appointmentID).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorID).orElseThrow();

        appointment.setDoctor(doctor);

        doctor.getAppointments().add(appointment);

        return appointment;
    }

}
