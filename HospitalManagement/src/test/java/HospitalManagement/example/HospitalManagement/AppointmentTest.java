package HospitalManagement.example.HospitalManagement;

import HospitalManagement.example.HospitalManagement.Entity.Appointment;
import HospitalManagement.example.HospitalManagement.Service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class AppointmentTest {

    @Autowired
    private AppointmentService appointmentService;

    @Test
    public void testCreateAppointment(){
        Appointment appointment = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2026,02,15,14,0))
                .reason("Eye Irritation")
                .build();

//        var newAppointment = appointmentService.createNewAppointment(appointment,1L,2L);
//        System.out.println(newAppointment);
//
//        var reappointment = appointmentService.reassignAppointment(newAppointment.getId(), 3L);
//        System.out.println(reappointment);
    }
}
