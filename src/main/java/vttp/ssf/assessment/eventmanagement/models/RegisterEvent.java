package vttp.ssf.assessment.eventmanagement.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterEvent {

    @NotBlank(message = "Name is a required field")
    @Size(min = 5, max = 25, message = "length between 5 - 25 characters")
    private String fullName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "BirthDate must be a past date")
    private LocalDate birthDate;

    @NotBlank(message = "Email is a required field")
    @Email(message = " Invalid Email Format")
    @Size(max = 50, message = "Email length exceeded 50 characters")
    private String email;

    @Pattern(regexp = "(8|9)[0-9]{7}", message = "Invalid Phone Number")
    private String phoneNumber;

    @Min(value = 1, message = "Minimum tickets requested = 1")
    @Max(value = 3, message = "Maximum salary cannot exceed 3")
    private Integer requestedTickets;

    private String Gender;

    public RegisterEvent(String fullName,LocalDate birthDate, String email, String phoneNumber,Integer requestedTickets,String gender) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.requestedTickets = requestedTickets;
        Gender = gender;
    }

    public RegisterEvent() {
    }

   

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getRequestedTickets() {
        return requestedTickets;
    }

    public void setRequestedTickets(Integer requestedTickets) {
        this.requestedTickets = requestedTickets;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

}
