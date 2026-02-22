package HospitalManagement.example.HospitalManagement.Error;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiErrors {
    private LocalDateTime timeStamp;
    private String error;
    private HttpStatus httpStatus;

    public ApiErrors(){
        this.timeStamp=LocalDateTime.now();
    }
    public ApiErrors(String error, HttpStatus httpStatus){
        this();
        this.error = error;
        this.httpStatus = httpStatus;
    }
}
