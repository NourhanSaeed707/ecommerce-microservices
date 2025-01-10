package com.example.payment.payment;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class Customer {
    private String id;
    @NotNull(message = "Firstname is required")
    private String firstname;
    @NotNull(message = "Lastname is required")
    private String lastname;
    @NotNull(message = "Email is required")
    @Email(message = "The customer email is not correctly formatted")
    private String email;
}
